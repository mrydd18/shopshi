package com.example.ecommerceapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ecommerceapp.databinding.FragmentForgotPasswordBinding
import com.google.firebase.auth.FirebaseAuth

class FragmentForgotPassword : Fragment() {

    private var _binding: FragmentForgotPasswordBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        resetPassword()
        backTologinPR()
    }

    private fun resetPassword() {
        binding.btnResetPassword.setOnClickListener {
            val email = binding.etEmailRst.text.toString().trim()

            if (email.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    "Please enter your email address",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            auth.sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            requireContext(),
                            "Password reset email sent",
                            Toast.LENGTH_SHORT
                        ).show()

                        findNavController().navigate(FragmentForgotPasswordDirections.actionFragmentForgotPasswordToFragmentSignIn())
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Failed to send reset email: ${task.exception?.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }

    private fun backTologinPR() {
        binding.backToLogin.setOnClickListener {
            findNavController().navigate(FragmentForgotPasswordDirections.actionFragmentForgotPasswordToFragmentSignIn())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}