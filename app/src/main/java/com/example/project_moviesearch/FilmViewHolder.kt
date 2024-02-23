package com.example.project_moviesearch

import androidx.recyclerview.widget.RecyclerView
import com.example.project_moviesearch.databinding.FilmItemBinding


class FilmViewHolder(private var binding: FilmItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(film: Film) {
        //Устанавливаем заголовок
        binding.title.text = film.title
        //Устанавливаем постер
        binding.poster.setImageResource(film.poster)
        //Устанавливаем описание
        binding.description.text = film.description
    }
}