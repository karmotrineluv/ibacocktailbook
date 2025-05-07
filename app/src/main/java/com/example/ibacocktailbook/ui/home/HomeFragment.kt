package com.example.ibacocktailbook.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ibacocktailbook.App
import com.example.ibacocktailbook.databinding.FragmentHomeBinding
import com.example.ibacocktailbook.db.CocktailRepository
import com.google.firebase.auth.FirebaseAuth
import java.util.*

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels {
        val app = requireActivity().application as App
        val repo = CocktailRepository(app.database.cocktailDao())
        HomeViewModelFactory(repo)
    }

    private lateinit var cocktailAdapter: CocktailAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Приветствие пользователя
        val userName = FirebaseAuth.getInstance().currentUser?.displayName
        val greeting = getGreetingMessage()
        binding.greetingText.text = "$greeting, ${userName ?: "Гость"}!"

        // Инициализация адаптера с обработкой кликов
        cocktailAdapter = CocktailAdapter(
            onFavoriteClick = { cocktail -> viewModel.toggleFavorite(cocktail) },
            onItemClick = { cocktail ->
                val action = HomeFragmentDirections
                    .actionHomeFragmentToCocktailDetailFragment(cocktail.cocktail.id)
                findNavController().navigate(action)
            }
        )

        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = cocktailAdapter
        }

        // Наблюдение за всеми коктейлями
        viewModel.allCocktails.observe(viewLifecycleOwner) { cocktails ->
            Log.d("HomeFragment", "Received ${cocktails.size} cocktails from LiveData")
            cocktailAdapter.submitList(cocktails)
        }

        // Наблюдение за результатами поиска
        viewModel.searchResults.observe(viewLifecycleOwner) { results ->
            if (results.isNotEmpty()) {
                cocktailAdapter.submitList(results)
            } else {
                viewModel.allCocktails.value?.let {
                    cocktailAdapter.submitList(it)
                }
            }
        }

        // Слушатель для поиска с TextWatcher
        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {
                // Можно оставить пустым или использовать для других целей
            }

            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
                // Можно оставить пустым или использовать для других целей
            }

            override fun afterTextChanged(editable: Editable?) {
                val query = editable.toString().trim()
                if (query.isNotEmpty()) {
                    viewModel.searchCocktails(query)
                } else {
                    viewModel.clearSearchResults()
                }
            }
        })

        // Случайный коктейль
        viewModel.randomCocktail.observe(viewLifecycleOwner) { random ->
            random?.let {
                val action = HomeFragmentDirections
                    .actionHomeFragmentToCocktailDetailFragment(it.cocktail.id)
                findNavController().navigate(action)
                viewModel.clearRandomCocktail()  // Сброс значения
            }
        }

        binding.randomCocktailButton.setOnClickListener {
            viewModel.loadRandomCocktail()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getGreetingMessage(): String {
        val hourOfDay = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        return when (hourOfDay) {
            in 5..11 -> "Good morning"
            in 12..17 -> "Good afternoon"
            in 18..21 -> "Good evening"
            else -> "Good night"
        }
    }
}
