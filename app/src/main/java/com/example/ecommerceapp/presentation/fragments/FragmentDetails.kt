package com.example.ecommerceapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.ecommerceapp.databinding.FragmentDetailsBinding
import com.example.ecommerceapp.domain.model.Product
import com.example.ecommerceapp.presentation.viewModels.DetailsViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentDetails : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val viewModel by viewModel<DetailsViewModel>()
    private val binding get() = _binding!!
    private val args : FragmentDetailsArgs by navArgs()
    private lateinit var product : Product



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDetailsBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getItemDetails()
        setCollectors()
    }

    private fun getItemDetails() {
        viewModel.getItemById(productId = args.id)

    }

    private fun setCollectors() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.itemDetails.collect {
                    if (it != null) {
                        product = it
                        binding.detailsTitle.text = product.title
                        binding.detailsDesc.text = product.description
                        binding.detailsPrice.text = product.price.toString()
                        binding.detailsRating.text = product.rating.toString()

                        Glide.with(binding.detailsImage)
                            .load(product.image)
                            .into(binding.detailsImage)
                    }
                }
            }
        }
    }





}