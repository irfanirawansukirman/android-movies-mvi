package com.example.learningmviarchitecture.feature.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.learningmviarchitecture.data.remote.model.Result
import com.example.learningmviarchitecture.databinding.ItemMovieBinding

class MoviesAdapter(private val onClickMovie: (Result) -> Unit) :
    RecyclerView.Adapter<MoviesAdapter.ItemHolder>() {

    private val movies = mutableListOf<Result>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bindItem(movies[holder.adapterPosition], onClickMovie)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class ItemHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItem(movie: Result, onClickMovie: (Result) -> Unit) {
            binding.apply {
                tvTitle.text = movie.originalTitle
                root.setOnClickListener { onClickMovie(movie) }
            }
        }
    }

    fun addAllMovies(movies: List<Result>) {
        this.movies.apply {
            clear()
            addAll(movies)
        }
        notifyDataSetChanged()
    }
}