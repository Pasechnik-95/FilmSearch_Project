package com.example.project_moviesearch

import androidx.recyclerview.widget.RecyclerView
import com.example.project_moviesearch.databinding.FilmItemBinding


class FilmViewHolder(private var filmItemBinding: FilmItemBinding) :
    RecyclerView.ViewHolder(filmItemBinding.root) {

    fun bind(film: Film) {
        //Устанавливаем заголовок
        filmItemBinding.title.text = film.title
        //Устанавливаем постер
        filmItemBinding.poster.setImageResource(film.poster)
        //Устанавливаем описание
        filmItemBinding.description.text = film.description
    }
}