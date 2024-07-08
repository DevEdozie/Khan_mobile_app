package com.example.khan.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.khan.adapter.ProductAdapter
import com.example.khan.databinding.FragmentProductsScreenBinding
import com.example.khan.model.BaseResponse
import com.example.khan.viewmodel.MainActivityViewmodel

class ProductsScreenFragment : Fragment() {

    // Obtain ViewModel from the parent activity
    private val viewModel: MainActivityViewmodel by activityViewModels()

    // Declare the ProductAdapter and binding object
    private lateinit var productAdapter: ProductAdapter
    private lateinit var binding: FragmentProductsScreenBinding

    // Inflate the fragment layout and initialize necessary components
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProductsScreenBinding.inflate(layoutInflater, container, false)

        // Trigger the network call to fetch products
        viewModel.fetchProducts()

        // Observe the response data from the ViewModel
        observeResultData()

        // Set up the RecyclerView with the adapter
        setUpRecyclerView()

        // Set up the back button behavior
        setUpBackButton()

        // Set up swipe to refresh functionality
        setUpSwipeToRefresh()

        return binding.root
    }

    // Function to observe response data from the ViewModel
    private fun observeResultData() {
        viewModel.timbuResult.observe(viewLifecycleOwner) { timbuResponse ->
            when (timbuResponse) {
                is BaseResponse.Loading -> {
                    // Show loading toast
                    Toast.makeText(
                        requireContext(),
                        "Loading....",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is BaseResponse.Success -> {
                    // On success, make the RecyclerView visible and hide the no signal text
                    binding.productsRecyclerview.visibility = View.VISIBLE
                    binding.noSignalTv.visibility = View.GONE
                }

                is BaseResponse.Error -> {
                    // On error, show error message and update UI
                    val errorMessage = timbuResponse.message
                    Toast.makeText(
                        requireContext(),
                        "${errorMessage}",
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.productsRecyclerview.visibility = View.GONE
                    binding.noSignalTv.visibility = View.VISIBLE
                }
            }
        }
    }

    // Function to set up the RecyclerView with the ProductAdapter
    private fun setUpRecyclerView() {
        productAdapter = ProductAdapter(viewModel)

        binding.productsRecyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = productAdapter
        }

        // Observe items data from the ViewModel and submit to the adapter
        viewModel.items.observe(viewLifecycleOwner) { items ->
            productAdapter.differ.submitList(items)
        }
    }

    // Function to override the back button behavior
    private fun setUpBackButton() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    // Close the app when the back button is pressed
                    requireActivity().finish()
                }
            })
    }

    // Function to set up swipe to refresh functionality
    private fun setUpSwipeToRefresh() {
        binding.swipeToRefresh.setOnRefreshListener {
            // Trigger the network call to refresh products
            viewModel.fetchProducts()
            // Explicitly stop the refreshing animation
            binding.swipeToRefresh.isRefreshing = false
        }
    }
}
