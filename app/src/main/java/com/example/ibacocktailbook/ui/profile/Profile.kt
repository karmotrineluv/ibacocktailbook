package com.example.ibacocktailbook.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.ibacocktailbook.R
import com.google.firebase.auth.FirebaseAuth
import androidx.navigation.fragment.findNavController


class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var logoutButton: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Находим кнопку "Выйти"
        logoutButton = view.findViewById(R.id.logoutButton)

        // Устанавливаем обработчик нажатия на кнопку "Выйти"
        logoutButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            Toast.makeText(context, "Вы успешно разлогинились", Toast.LENGTH_SHORT).show()

            // После выхода — возвращаем на экран входа
            // Можно настроить возвращение на loginFragment или сделать переход на стартовый экран
            // Например:
            findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
        }
    }
}
