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
    private val onFavoriteClick: (CocktailWithIngredients) -> Unit = {}
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

                // Загрузка изображения
                val imageResId = context.resources.getIdentifier(imageName, "drawable", context.packageName)

                Glide.with(context)
                    .load(if (imageResId != 0) imageResId else R.drawable.placeholder)
                    .placeholder(R.drawable.placeholder)
                    .into(cocktailImageView)

                cocktailNameTextView.text = cocktailWithIngredients.cocktail.name
                cocktailTypeTextView.text = cocktailWithIngredients.cocktail.type

                // Устанавливаем визуальное состояние кнопки
                saveButton.isSelected = cocktailWithIngredients.cocktail.isFavorite

                // Обработка клика — не меняем состояние здесь!
                saveButton.setOnClickListener {
                    onFavoriteClick(cocktailWithIngredients)
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
