package com.example.ibacocktailbook.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ibacocktailbook.App
import com.example.ibacocktailbook.databinding.FragmentHomeBinding
import com.example.ibacocktailbook.db.CocktailRepository

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

        // Инициализация адаптера с обработкой клика
        cocktailAdapter = CocktailAdapter { cocktail ->
            // Обработка клика на кнопку "сохранить"
            viewModel.toggleFavorite(cocktail)
        }

        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = cocktailAdapter
        }

        // Подписываемся на LiveData всех коктейлей
        viewModel.allCocktails.observe(viewLifecycleOwner) { cocktails ->
            cocktailAdapter.submitList(cocktails)
        }

        // Подписываемся на LiveData случайного коктейля
        viewModel.randomCocktail.observe(viewLifecycleOwner) { random ->
            random?.let {
                Toast.makeText(
                    requireContext(),
                    "Random Cocktail: ${it.cocktail.name}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.randomCocktailCard.setOnClickListener {
            viewModel.loadRandomCocktail()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}



