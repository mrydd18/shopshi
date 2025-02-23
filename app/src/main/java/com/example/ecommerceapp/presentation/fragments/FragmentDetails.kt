package com.example.ecommerceapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.ecommerceapp.databinding.FragmentDetailsBinding
import com.example.ecommerceapp.presentation.viewModels.DetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentDetails : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val viewModel by viewModel<DetailsViewModel>()
    private val binding get() = _binding!!
    private val args : FragmentDetailsArgs by navArgs()



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
    }

    private fun getItemDetails() {
        viewModel
    }




}