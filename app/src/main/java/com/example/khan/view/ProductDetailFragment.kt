package com.example.khan.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.khan.R
import com.example.khan.databinding.FragmentProductDetailBinding
import com.example.khan.model.BaseResponse
import com.example.khan.model.Item
import com.example.khan.viewmodel.MainActivityViewmodel

class ProductDetailFragment : Fragment() {

    // Declare the binding object for this fragment
    private lateinit var binding: FragmentProductDetailBinding

    // Obtain the ViewModel from the parent activity
    private val viewModel: MainActivityViewmodel by activityViewModels()

    // Inflate the fragment layout and initialize necessary components
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProductDetailBinding.inflate(layoutInflater, container, false)

        // Set up back arrow navigation
        setUpBackArrowNavigation()

        // Observe product data from the ViewModel
        observeProductData()

        return binding.root
    }

    // Function to set up back arrow navigation
    private fun setUpBackArrowNavigation() {
        // Set listener for back arrow icon
        binding.backArrow.setOnClickListener {
            // Pop back stack to ProductsScreenFragment
            findNavController().popBackStack(R.id.productsScreenFragment, false)
        }
    }

    // Function to set up product details in the UI
    private fun setUpProductDetail(product: Item?) {
        // Set product title text
        binding.productTitle.text = product?.name

        // Set product description text
        binding.productDesc.text = "${product?.description}"

        // Check if photos list is not empty and load the first image
        val imageUrl = "https://api.timbu.cloud/images/${product?.photos?.get(0)?.url}"
        if (imageUrl.isNotEmpty()) {
            // Display image from URL
            Glide.with(requireContext()).load(imageUrl).into(binding.productImage)
        } else {
            // Set a placeholder image or handle the case where there is no image
            binding.productImage.setImageResource(R.drawable.terrex_free_hiker)
        }
    }

    // Function to observe product data from the ViewModel
    private fun observeProductData() {
        viewModel.timbuProduct.observe(viewLifecycleOwner) { timbuProduct ->
            when (timbuProduct) {
                is BaseResponse.Loading -> {
                    // Show loading toast
                    Toast.makeText(
                        requireContext(),
                        "Loading....",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is BaseResponse.Success -> {
                    // On success, set up product details
                    val product = timbuProduct.data
                    setUpProductDetail(product)
                }

                is BaseResponse.Error -> {
                    // On error, show error message
                    val errorMessage = timbuProduct.message
                    Toast.makeText(
                        requireContext(),
                        "${errorMessage}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}
