package com.irfanirawansukirman.movies.presentation.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.irfanirawansukirman.movies.data.model.MoviesDataModel
import com.irfanirawansukirman.movies.databinding.MoviesItemBinding
import com.irfanirawansukirman.movies.presentation.movies.model.MoviesUiModel

class MoviesAdapter(
    private val moviesListener: MoviesListener
) : RecyclerView.Adapter<MoviesAdapter.ItemHolder>() {

    private val movies = mutableListOf<MoviesUiModel>()

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

        fun bindItem(movie: MoviesUiModel, moviesListener: MoviesListener) {
            binding.apply {
                tvTitle.text = movie.name

                val entity = MoviesDataModel(
                    movie.id,
                    movie.name,
                    movie.posterPath,
                    movie.release,
                    movie.overview
                )
                root.setOnClickListener { moviesListener.onClickMovie(entity) }
            }
        }
    }

    fun addAllMovies(movies: List<MoviesUiModel>) {
        this.movies.apply {
            clear()
            addAll(movies)
        }
        notifyDataSetChanged()
    }
}