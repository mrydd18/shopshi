package com.example.ecommerceapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.FragmentSigninBinding
import com.google.firebase.auth.FirebaseAuth


class FragmentSignIn : Fragment() {

    private var _binding: FragmentSigninBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth  // Firebase Auth instance

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSigninBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()  // Initialize Firebase Auth



        goToRegistration()
        resetPassword()
        signInUser()
    }

    private fun goToRegistration() {
        binding.register.setOnClickListener {
            findNavController().navigate(FragmentSignInDirections.actionFragmentSignInToFragmentRegistration())
        }
    }

    private fun resetPassword() {
        binding.forgotPassword.setOnClickListener {
            findNavController().navigate(FragmentSignInDirections.actionFragmentSignInToFragmentForgotPassword())
        }
    }

    private fun signInUser() {
        binding.signIn.setOnClickListener {
            val email = binding.email.text.toString().trim()
            val password = binding.password.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Email and password required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(requireContext(), "Login successful", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(FragmentSignInDirections.actionFragmentSignInToFragmentContainer())
                    } else {
                        Toast.makeText(requireContext(), "Login failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}