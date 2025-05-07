package com.example.ibacocktailbook.ui.profile

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.ibacocktailbook.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import android.content.Intent
import com.example.ibacocktailbook.MainActivity


class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var logoutButton: Button
    private lateinit var usernameTextView: TextView
    private lateinit var userAvatar: ImageView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Находим компоненты
        logoutButton = view.findViewById(R.id.logoutButton)
        usernameTextView = view.findViewById(R.id.usernameTextView)
        userAvatar = view.findViewById(R.id.userAvatar)

        // Загружаем и отображаем актуальные данные профиля
        loadUserProfile()

        // Выход из аккаунта
        logoutButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            Toast.makeText(context, "Вы успешно разлогинились", Toast.LENGTH_SHORT).show()

            // Перезапуск MainActivity с очисткой back stack
            val intent = Intent(requireContext(), MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

    }



    private fun loadUserProfile() {
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            // Перезагрузим данные профиля с сервера Firebase
            user.reload().addOnCompleteListener { reloadTask ->
                val current = if (reloadTask.isSuccessful) FirebaseAuth.getInstance().currentUser else user
                updateProfileUI(current)
            }
        } else {
            updateProfileUI(null)
        }
    }

    private fun updateProfileUI(user: FirebaseUser?) {
        // Имя пользователя
        val userName = user?.displayName ?: "Guest"
        usernameTextView.text = "Hello, $userName!"

        // Аватарка
        val photoUri = user?.photoUrl
        if (photoUri != null) {
            Glide.with(this)
                .load(photoUri.toString() + "?t=${System.currentTimeMillis()}") // меняем URL → кэш думает, что это новый файл
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .centerCrop()
                .into(userAvatar)

        } else {
            userAvatar.setImageResource(R.drawable.placeholder)
        }
    }
}
