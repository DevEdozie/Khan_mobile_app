package com.example.khan.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.khan.model.BaseResponse
import com.example.khan.model.Item
import com.example.khan.model.ProductsResponse
import com.example.khan.repository.TimbuRepository
import kotlinx.coroutines.launch

class MainActivityViewmodel : ViewModel() {

    // Lazy initialization of the repository
    val timbuRepo by lazy {
        TimbuRepository()
    }

    // LiveData for products response
    private val _timbuResult: MutableLiveData<BaseResponse<ProductsResponse>> = MutableLiveData()
    val timbuResult: LiveData<BaseResponse<ProductsResponse>> = _timbuResult

    // LiveData for single product response
    private val _timbuProduct: MutableLiveData<BaseResponse<Item>> = MutableLiveData()
    val timbuProduct: LiveData<BaseResponse<Item>> = _timbuProduct

    // LiveData for list of items
    private val _items: MutableLiveData<List<Item>> = MutableLiveData()
    val items: LiveData<List<Item>> = _items

    // Initialize ViewModel by fetching products
    init {
        fetchProducts()
    }

    // Function to fetch products
    fun fetchProducts() {
        // Set initial state to loading
        _timbuResult.postValue(BaseResponse.Loading())

        // Launch coroutine to perform network request
        viewModelScope.launch {
            val response = timbuRepo.getProducts()
            if (response is BaseResponse.Success) {
                // Post success response and update items list
                _timbuResult.postValue(response)
                _items.postValue(response.data?.items)
            } else if (response is BaseResponse.Error) {
                // Post error response and clear items list
                _timbuResult.postValue(response)
                _items.postValue(emptyList())
            }
        }
    }

    // Function to fetch a single product by its ID
    fun fetchProduct(productId: String) {
        // Set initial state to loading
        _timbuProduct.postValue(BaseResponse.Loading())

        // Launch coroutine to perform network request
        viewModelScope.launch {
            val response = timbuRepo.getProduct(productId)
            if (response is BaseResponse.Success) {
                // Post success response
                _timbuProduct.postValue(response)
            } else if (response is BaseResponse.Error) {
                // Post error response
                _timbuProduct.postValue(response)
            }
        }
    }
}
