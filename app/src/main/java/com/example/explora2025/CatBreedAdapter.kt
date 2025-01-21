package com.example.explora2025
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
class CatBreedAdapter(
    private val catBreeds: List<CatBreed>,
    private val onItemClick: (CatBreed) -> Unit,
    private val onLikeClick: (CatBreed) -> Unit,
    private val onShareClick: (CatBreed) -> Unit
) : RecyclerView.Adapter<CatBreedAdapter.CatBreedViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatBreedViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cat_breed, parent, false)
        return CatBreedViewHolder(view)
    }
    override fun onBindViewHolder(holder: CatBreedViewHolder, position: Int) {
        val catBreed = catBreeds[position]
        holder.bind(catBreed)
    }
    override fun getItemCount(): Int = catBreeds.size
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
                // Trigger the onItemClick action
                onItemClick(catBreed)
            }
            likeButton.setOnClickListener { onLikeClick(catBreed) }
            shareButton.setOnClickListener { onShareClick(catBreed) }
        }
    }
}
