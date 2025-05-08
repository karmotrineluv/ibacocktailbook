package com.example.ibacocktailbook.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ibacocktailbook.R
import com.example.ibacocktailbook.databinding.ItemCocktailBinding
import com.example.ibacocktailbook.db.CocktailWithIngredients

class CocktailAdapter(
    private val onFavoriteClick: (CocktailWithIngredients) -> Unit = {},
    private val onItemClick: (CocktailWithIngredients) -> Unit = {}
) : ListAdapter<CocktailWithIngredients, CocktailAdapter.CocktailViewHolder>(CocktailDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailViewHolder {
        val binding = ItemCocktailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CocktailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CocktailViewHolder, position: Int) {
        val cocktail = getItem(position)
        holder.bind(cocktail)
    }

    inner class CocktailViewHolder(private val binding: ItemCocktailBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(cocktailWithIngredients: CocktailWithIngredients) {
            binding.apply {
                val context = itemView.context
                val imageName = cocktailWithIngredients.cocktail.imageUrl

                val imageResId = context.resources.getIdentifier(imageName, "drawable", context.packageName)

                Glide.with(context)
                    .load(if (imageResId != 0) imageResId else R.drawable.placeholder)
                    .placeholder(R.drawable.placeholder)
                    .into(cocktailImageView)

                cocktailNameTextView.text = cocktailWithIngredients.cocktail.name
                cocktailTypeTextView.text = cocktailWithIngredients.cocktail.type

                saveButton.isSelected = cocktailWithIngredients.cocktail.isFavorite

                saveButton.setOnClickListener {
                    onFavoriteClick(cocktailWithIngredients)
                }

                root.setOnClickListener {
                    onItemClick(cocktailWithIngredients)
                }
            }
        }
    }

    class CocktailDiffCallback : DiffUtil.ItemCallback<CocktailWithIngredients>() {
        override fun areItemsTheSame(oldItem: CocktailWithIngredients, newItem: CocktailWithIngredients): Boolean {
            return oldItem.cocktail.id == newItem.cocktail.id
        }

        override fun areContentsTheSame(oldItem: CocktailWithIngredients, newItem: CocktailWithIngredients): Boolean {
            return oldItem == newItem
        }
    }
}
