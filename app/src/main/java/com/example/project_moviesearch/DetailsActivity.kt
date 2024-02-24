package com.example.project_moviesearch

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView

class DetailsActivity() : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)


        //Получаем наш фильм из переданного бандла
        val film = intent.extras?.get("film") as Film

        //Устанавливаем заголовок
        findViewById<androidx.appcompat.widget.Toolbar>(R.id.detailsToolbar).title = film.title
        //Устанавливаем картинку
        findViewById<AppCompatImageView>(R.id.detailsPoster).setImageResource(film.poster)
        //Устанавливаем описание
        findViewById<TextView>(R.id.detailsDescription).text = film.description
    }
}



