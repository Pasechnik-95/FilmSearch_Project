package com.example.project_moviesearch

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project_moviesearch.databinding.ItemFilmBinding


class FilmViewHolder(private var filmItemBinding: ItemFilmBinding) :
    RecyclerView.ViewHolder(filmItemBinding.root) {

    fun bind(film: Film) {
        //Устанавливаем заголовок
        filmItemBinding.title.text = film.title
        //Устанавливаем постер
        //Указываем контейнер, в котором будет "жить" наша картинка
        Glide.with(itemView)
            //Загружаем сам ресурс
            .load(film.poster)
            //Центруем изображение
            .centerCrop()
            //Указываем ImageView, куда будем загружать изображение
            .into(filmItemBinding.poster)
        //Устанавливаем описание
        filmItemBinding.description.text = film.description
//        Устанавливаем рейтинг
        filmItemBinding.ratingDonut.setProgress((film.rating * 10).toInt())
    }
}