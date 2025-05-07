package com.example.ibacocktailbook.ui.auth

import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.ibacocktailbook.R
import com.example.ibacocktailbook.databinding.FragmentVerifyEmailBinding
import com.google.firebase.auth.FirebaseAuth
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class VerifyEmailFragment : Fragment() {

    private lateinit var binding: FragmentVerifyEmailBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVerifyEmailBinding.inflate(inflater, container, false)
        auth = FirebaseAuth.getInstance()

        val animOptions = NavOptions.Builder()
            .setEnterAnim(R.anim.slide_in_right)
            .setExitAnim(R.anim.slide_out_left)
            .setPopEnterAnim(R.anim.slide_in_left)
            .setPopExitAnim(R.anim.slide_out_right)
            .build()

        val email = auth.currentUser?.email ?: ""
        val fullText =
            "We sent a verification link to $email. Please check your inbox and click the link to activate your account."

        val spannableString = SpannableString(fullText)
        val startIndex = fullText.indexOf(email)
        val endIndex = startIndex + email.length

        spannableString.setSpan(
            ForegroundColorSpan(Color.parseColor("#3c82d0")),
            startIndex,
            endIndex,
            0
        )
        binding.verificationMessage.text = spannableString

        binding.continueButton.setOnClickListener {
            auth.currentUser?.reload()?.addOnCompleteListener {
                if (auth.currentUser?.isEmailVerified == true) {
                    Toast.makeText(context, "Email verified!", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(
                        R.id.action_emailVerificationFragment_to_homeFragment,
                        null,
                        animOptions
                    )
                } else {
                    Toast.makeText(context, "Email was not verified yet.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.resendEmailButton.setOnClickListener {
            auth.currentUser?.sendEmailVerification()?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(context, "New message was sent", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(
                        context,
                        "Error: ${task.exception?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        binding.backToLogin.setOnClickListener {
            findNavController().navigate(
                R.id.action_emailVerificationFragment_to_loginFragment,
                null,
                animOptions
            )
        }

        return binding.root
    }
}
