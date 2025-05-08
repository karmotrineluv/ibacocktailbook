package com.example.ibacocktailbook.ui.auth

import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.ibacocktailbook.R
import com.example.ibacocktailbook.databinding.FragmentLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    private val RC_SIGN_IN = 9001

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        auth = FirebaseAuth.getInstance()

        FirebaseAuth.getInstance().signOut()

        //Google Sign-In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(requireContext(), gso)

        setupNavOptions()
        setupListeners()

        return binding.root
    }

    private fun setupNavOptions() {
        val animOptions = NavOptions.Builder()
            .setEnterAnim(R.anim.slide_in_right)
            .setExitAnim(R.anim.slide_out_left)
            .setPopEnterAnim(R.anim.slide_in_left)
            .setPopExitAnim(R.anim.slide_out_right)
            .build()

        binding.registerLabel.setOnClickListener {
            findNavController().navigate(
                R.id.action_loginFragment_to_registerFragment,
                null,
                animOptions
            )
        }

        binding.forgotPasswordText.setOnClickListener {
            findNavController().navigate(
                R.id.action_loginFragment_to_resetPasswordFragment,
                null,
                animOptions
            )
        }
    }

    private fun setupListeners() {
        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            if (email.isBlank() || password.isBlank()) {
                Toast.makeText(context, "Fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            setLoadingState(true)
            animateButton(binding.loginButton)
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    setLoadingState(false)
                    if (task.isSuccessful) navigateToHome()
                    else Toast.makeText(
                        context,
                        "Login failed: ${task.exception?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }

        binding.anonymousLoginButton.setOnClickListener {
            setLoadingState(true)
            animateButton(binding.anonymousLoginButton)
            auth.signInAnonymously().addOnCompleteListener { task ->
                setLoadingState(false)
                if (task.isSuccessful) navigateToHome()
                else Toast.makeText(
                    context,
                    "Anonymous login error: ${task.exception?.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.googleLoginButton.setOnClickListener {
            setLoadingState(true)
            animateButton(binding.googleLoginButton)
            googleSignInClient.signOut().addOnCompleteListener {
                googleSignInClient.revokeAccess().addOnCompleteListener {
                    startActivityForResult(googleSignInClient.signInIntent, RC_SIGN_IN)
                }
            }
        }
    }

    private fun animateButton(button: View) {
        ValueAnimator.ofFloat(1f, 1.1f, 1f).apply {
            duration = 300
            interpolator = AccelerateDecelerateInterpolator()
            addUpdateListener { anim ->
                val scale = anim.animatedValue as Float
                button.scaleX = scale
                button.scaleY = scale
            }
        }.start()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                task.getResult(ApiException::class.java)?.idToken?.let {
                    firebaseAuthWithGoogle(it)
                } ?: setLoadingState(false)
            } catch (e: ApiException) {
                setLoadingState(false)
                Toast.makeText(context, "Google login failed: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener(requireActivity()) { task ->
            setLoadingState(false)
            if (task.isSuccessful) navigateToHome()
            else Toast.makeText(
                context,
                "Firebase authentication failed: ${task.exception?.message}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun navigateToHome() {
        val options = NavOptions.Builder()
            .setPopUpTo(R.id.loginFragment, true)
            .setEnterAnim(R.anim.slide_in_right)
            .setExitAnim(R.anim.slide_out_left)
            .setPopEnterAnim(R.anim.slide_in_left)
            .setPopExitAnim(R.anim.slide_out_right)
            .build()
        findNavController().navigate(R.id.action_loginFragment_to_homeFragment, null, options)
    }

    private fun setLoadingState(isLoading: Boolean) {
        if (isLoading) {
            binding.loadingProgressBar.visibility = View.VISIBLE
            binding.loadingProgressBar.progress = 0
            ValueAnimator.ofInt(0, 100).apply {
                duration = 1500
                interpolator = AccelerateDecelerateInterpolator()
                addUpdateListener { binding.loadingProgressBar.progress = it.animatedValue as Int }
            }.start()
        } else {
            binding.loadingProgressBar.visibility = View.GONE
        }

        binding.loginButton.isEnabled = !isLoading
        binding.anonymousLoginButton.isEnabled = !isLoading
        binding.googleLoginButton.isEnabled = !isLoading
        binding.emailEditText.isEnabled = !isLoading
        binding.passwordEditText.isEnabled = !isLoading
        binding.registerLabel.isEnabled = !isLoading
        binding.forgotPasswordText.isEnabled = !isLoading
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth.currentUser?.let { navigateToHome() }
    }
}
