package com.example.ibacocktailbook.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ibacocktailbook.databinding.FragmentFavoritesBinding
import com.example.ibacocktailbook.db.CocktailDatabase
import com.example.ibacocktailbook.ui.home.CocktailAdapter
import kotlinx.coroutines.launch

class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private lateinit var cocktailAdapter: CocktailAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dao = CocktailDatabase.getInstance(requireContext()).cocktailDao()

        cocktailAdapter = CocktailAdapter(
            onFavoriteClick = { cocktail ->
                lifecycleScope.launch {
                    dao.updateFavoriteStatus(cocktail.cocktail.id, !cocktail.cocktail.isFavorite)
                }
            },
            onItemClick = { cocktail ->
                val action = FavoritesFragmentDirections
                    .actionFavoritesFragmentToCocktailDetailFragment(cocktail.cocktail.id)
                findNavController().navigate(action)
            }
        )

        binding.favoritesRecyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = cocktailAdapter
        }

        dao.getFavoritesCocktailsLiveData().observe(viewLifecycleOwner) { list ->
            cocktailAdapter.submitList(list)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
