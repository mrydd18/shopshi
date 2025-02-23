package com.example.ecommerceapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ecommerceapp.databinding.FragmentWelcomeBinding
import com.google.firebase.auth.FirebaseAuth
import org.koin.android.ext.android.inject

class FragmentWelcome : Fragment() {

    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!
    private val auth by inject<FirebaseAuth>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if (auth.currentUser != null) {
            findNavController().navigate(FragmentWelcomeDirections.actionFragmentWelcomeToFragmentContainer())
        }

        goToSecondSlide()

    }

    private fun goToSecondSlide() {
        binding.StartShoppingBtn.setOnClickListener{
            findNavController().navigate(FragmentWelcomeDirections.actionFragmentWelcomeToFragmentSignIn())
        }
    }
}