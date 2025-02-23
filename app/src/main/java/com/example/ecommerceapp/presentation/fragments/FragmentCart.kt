package com.example.ecommerceapp.presentation.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerceapp.databinding.FragmentCartBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

import android.util.Log
import com.example.ecommerceapp.presentation.adapters.CartAdapter
import com.example.ecommerceapp.presentation.viewModels.CartViewModel

class FragmentCart : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<CartViewModel>()
    private val cartAdapter = CartAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        Log.d("FragmentCart", "onCreateView: FragmentCart created")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("FragmentCart", "onViewCreated: View created and initializing components")

        showAllAddedProducts()
        prepareRecyclerView()
        setListeners()
        setCollectors()
    }

    private fun prepareRecyclerView() {
        Log.d("FragmentCart", "prepareRecyclerView: Setting up RecyclerView")
        binding.recyclerViewCart.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
           adapter = cartAdapter
        }
    }


    private fun showAllAddedProducts() {
        viewModel.showAllAddedProduct()
    }

    private fun setListeners() {
        cartAdapter.onItemDeleteClick = { product ->
            Log.d("FragmentCart", "setListeners: Deleting product ${product.title}")

            AlertDialog.Builder(requireContext())
                .setTitle("Delete Product")
                .setMessage("Are you sure you want to delete ${product.title} from your cart?")
                .setPositiveButton("Yes") { _, _ ->
                    Log.d("FragmentCart", "setListeners: Product ${product.title} deleted")
                    viewModel.deleteAddedProduct(product)
                }
                .setNegativeButton("No") { _, _ ->
                    Log.d("FragmentCart", "setListeners: Deletion cancelled for ${product.title}")
                }
                .show()
        }
    }

    private fun setCollectors() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.allAddedProducts.collect { allProducts ->
                    Log.d("FragmentCart", "setCollectors: Received ${allProducts.size} products in cart")
                    cartAdapter.submitList(allProducts)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("FragmentCart", "onDestroyView: FragmentCart destroyed")
        _binding = null
    }
}


