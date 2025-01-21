package com.example.explora2025

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class CatBreedAdapter(
    private val onItemClick: (CatBreed) -> Unit,
    private val onLikeClick: (CatBreed) -> Unit,
    private val onShareClick: (CatBreed) -> Unit
) : ListAdapter<CatBreed, CatBreedAdapter.CatBreedViewHolder>(CatBreedDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatBreedViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cat_breed, parent, false)
        return CatBreedViewHolder(view)
    }

    override fun onBindViewHolder(holder: CatBreedViewHolder, position: Int) {
        val catBreed = getItem(position)
        holder.bind(catBreed)
    }

    inner class CatBreedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val catImage: ImageView = itemView.findViewById(R.id.cat_image)
        private val catName: TextView = itemView.findViewById(R.id.cat_name)
        private val catDescription: TextView = itemView.findViewById(R.id.cat_description)
        private val likeButton: Button = itemView.findViewById(R.id.like_button)
        private val shareButton: Button = itemView.findViewById(R.id.share_button)

        fun bind(catBreed: CatBreed) {
            catName.text = catBreed.name
            catDescription.text = catBreed.description
            catImage.setImageResource(catBreed.imageResId)

            itemView.setOnClickListener {
                onItemClick(catBreed)
            }
            likeButton.setOnClickListener {
                onLikeClick(catBreed)
            }
            shareButton.setOnClickListener {
                onShareClick(catBreed)
            }
        }
    }

    class CatBreedDiffCallback : DiffUtil.ItemCallback<CatBreed>() {
        override fun areItemsTheSame(oldItem: CatBreed, newItem: CatBreed): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CatBreed, newItem: CatBreed): Boolean {
            return oldItem == newItem
        }
    }
}
