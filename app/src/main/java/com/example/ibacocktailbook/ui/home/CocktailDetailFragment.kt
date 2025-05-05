package com.example.ibacocktailbook.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.ibacocktailbook.App
import com.example.ibacocktailbook.databinding.FragmentCocktailDetailBinding
import com.example.ibacocktailbook.db.CocktailEntity
import com.example.ibacocktailbook.db.CocktailRepository
import com.example.ibacocktailbook.db.CocktailWithIngredients

class CocktailDetailFragment : Fragment() {

    private var _binding: FragmentCocktailDetailBinding? = null
    private val binding get() = _binding!!

    private var currentCocktail: CocktailWithIngredients? = null

    private val viewModel: HomeViewModel by viewModels {
        val app = requireActivity().application as App
        val repo = CocktailRepository(app.database.cocktailDao())
        HomeViewModelFactory(repo)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCocktailDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cocktailId = arguments?.getInt("cocktailId") ?: return

        viewModel.loadCocktailById(cocktailId)

        viewModel.cocktailById.observe(viewLifecycleOwner) { cocktail ->
            if (cocktail != null) {
                bindCocktail(cocktail)
            } else {
                binding.cocktailNameTextView.text = "Коктейль не найден"
            }
        }

        // Назад
        binding.backButton.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.saveButton.setOnClickListener {
            currentCocktail?.let {
                viewModel.toggleFavorite(it)
                val newState = !it.cocktail.isFavorite
                binding.saveButton.isSelected = newState
                currentCocktail = it.copy(cocktail = it.cocktail.copy(isFavorite = newState))
            }
        }

    }

    private fun bindCocktail(cocktailWithIngredients: CocktailWithIngredients) {
        currentCocktail = cocktailWithIngredients
        val cocktail = cocktailWithIngredients.cocktail
        val context = requireContext()

        val imageResId = context.resources.getIdentifier(
            cocktail.imageUrl, "drawable", context.packageName
        )

        Glide.with(context)
            .load(if (imageResId != 0) imageResId else com.example.ibacocktailbook.R.drawable.placeholder)
            .into(binding.cocktailImageView)

        binding.cocktailNameTextView.text = cocktail.name
        binding.cocktailTypeTextView.text = cocktail.type
        binding.ingredientsTextView.text = cocktailWithIngredients.ingredients.joinToString("\n") { "- ${it.name}: ${it.amount}" }
        binding.instructionsTextView.text = cocktail.instructions
        binding.saveButton.isSelected = cocktail.isFavorite
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
