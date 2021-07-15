package com.irfanirawansukirman.movies.presentation.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.irfanirawansukirman.cache.entity.MoviesPopularEnt
import com.irfanirawansukirman.movies.databinding.MoviesItemBinding
import com.irfanirawansukirman.remote.data.response.MoviesPopularData

class MoviesAdapter(
    private val moviesListener: MoviesListener
) : RecyclerView.Adapter<MoviesAdapter.ItemHolder>() {

    private val movies = mutableListOf<MoviesPopularData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            MoviesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bindItem(movies[holder.adapterPosition], moviesListener)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class ItemHolder(private val binding: MoviesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItem(movie: MoviesPopularData, moviesListener: MoviesListener) {
            binding.apply {
                tvTitle.text = movie.originalTitle

                val entity = MoviesPopularEnt(
                    movie.id,
                    movie.originalTitle,
                    movie.posterPath,
                    movie.releaseDate,
                    movie.overview
                )
                root.setOnClickListener { moviesListener.onClickMovie(entity) }
            }
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