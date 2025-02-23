package com.example.ecommerceapp.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.ecommerceapp.databinding.FragmentHomeBinding
import com.example.ecommerceapp.presentation.adapters.ProductsAdapter
import com.example.ecommerceapp.presentation.viewModels.ProductViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentHome : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<ProductViewModel>()
    private val productsAdapter = ProductsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(
            "FragmentHome",
            "onCreateView: Initializing binding"
        )  // Log when onCreateView is called
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)


        Log.d(
            "FragmentHome",
            "onViewCreated: Setting up the recycler view"
        )  // Log when the view is created
        prepareGridRecyclerView()
        setCollectors()
        setListeners()
    }



    private fun prepareGridRecyclerView() {
        Log.d(
            "FragmentHome",
            "prepareGridRecyclerView: Setting up GridLayoutManager"
        )  // Log when preparing the RecyclerView
        binding.gridRecyclerView.adapter = productsAdapter
    }


    private fun setCollectors() {
        Log.d("FragmentHome", "setCollectors: Collecting product list updates")
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.allProductsList.collect { products ->
                    Log.d("FragmentHome", "setCollectors: Received product list, size: ${products}")

                        productsAdapter.submitList(products.toList())



                }
            }

        }
    }

    private fun setListeners() {
        productsAdapter.onAddToCartClick = {item ->
            viewModel.saveToCart(item)
        }
    }
}
