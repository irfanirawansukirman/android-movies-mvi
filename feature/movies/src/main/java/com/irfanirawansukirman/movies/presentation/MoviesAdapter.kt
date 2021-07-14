package com.irfanirawansukirman.movies.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.irfanirawansukirman.movies.databinding.MoviesItemBinding
import com.irfanirawansukirman.remote.data.response.MoviesPopularData

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.ItemHolder>() {

    private val movies = mutableListOf<MoviesPopularData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            MoviesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bindItem(movies[holder.adapterPosition])
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class ItemHolder(private val binding: MoviesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItem(movie: MoviesPopularData) {
            binding.tvTitle.text = movie.originalTitle
        }
    }

    fun addAllMovies(movies: List<MoviesPopularData>) {
        this.movies.apply {
            clear()
            addAll(movies)
        }
        notifyDataSetChanged()
    }
}