package com.example.ibacocktailbook.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ibacocktailbook.R
import com.example.ibacocktailbook.databinding.FragmentHomeBinding
import java.util.*

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val searchEditText = binding.searchEditText
        val searchIcon = resources.getDrawable(R.drawable.ic_search, null)
        searchIcon.setBounds(0, 0, 60, 60) // Размер иконки в px (можно использовать dp с конвертацией)
        searchEditText.setCompoundDrawables(searchIcon, null, null, null)


        // Приветствие в зависимости от времени суток
        val greeting = when (Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) {
            in 5..11 -> "Доброе утро"
            in 12..17 -> "Добрый день"
            in 18..22 -> "Добрый вечер"
            else -> "Доброй ночи"
        }

        binding.greetingText.text = "$greeting, приятного миксования!"

        // TODO: Обработка кликов и отображение данных для карточек

        return binding.root
    }
}
