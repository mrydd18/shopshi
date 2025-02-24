package com.example.ecommerceapp.presentation.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ecommerceapp.MainActivity
import com.example.ecommerceapp.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import org.koin.android.ext.android.inject

class FragmentProfile : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val firebaseAuth: FirebaseAuth by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)


        binding.buttonSignOut.setOnClickListener {
            signOutUser()
        }

        return binding.root

    }


    private fun signOutUser() {
        firebaseAuth.signOut() // Sign out from Firebase
        val intent = Intent(requireContext(), MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}