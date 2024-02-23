package com.example.project_moviesearch

import TopSpacingItemDecoration
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project_moviesearch.databinding.ActivityMainBinding

lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var filmsAdapter: FilmListRecyclerAdapter
    private val filmsDataBase = listOf(
        Film("Зеленая миля", R.drawable.the_green_mile, getString(R.string.the_green_mile_desc)),
        Film("1+1", R.drawable.intouchables, getString(R.string.intouchables_desc)),
        Film(
            "Побег из Шоушенка",
            R.drawable.the_shawshank_redemption,
            getString(R.string.the_shawshank_redemption_desc)
        ),
        Film("Форрест Гамп", R.drawable.forrest_gump, getString(R.string.forrest_gump_desc)),
        Film("Интерстеллар", R.drawable.interstellar, getString(R.string.interstellar_desc)),
        Film(
            "Унесённые призраками",
            R.drawable.sen_to_chihiro_no_kamikakushi,
            getString(R.string.sen_to_chihiro_no_kamikakushi_desc)
        ),
        Film(
            "Властелин колец: Возвращение короля",
            R.drawable.the_lord_of_the_rings_the_return_of_the_king,
            getString(R.string.the_lord_of_the_rings_the_return_of_the_king_desc)
        ),
        Film("Бойцовский клуб", R.drawable.fight_club, getString(R.string.fight_club_desc)),
        Film(
            "Список Шиндлера",
            R.drawable.schindlers_list,
            getString(R.string.schindlers_list_desc)
        ),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.topAppBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.settings -> {
                    Toast.makeText(this, "Настройки", Toast.LENGTH_SHORT).show()
                    true
                }

                else -> false
            }
        }

        binding.bottomNavigation.setOnItemSelectedListener {

            when (it.itemId) {
                R.id.account -> {
                    Toast.makeText(this, "Аккаунт", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.favourites -> {
                    Toast.makeText(this, "Избранное", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.collections -> {
                    Toast.makeText(this, "Подборки", Toast.LENGTH_SHORT).show()
                    true
                }

                else -> false
            }
        }

        binding.mainRecycler.apply {
            //Инициализируем наш адаптер. D конструктор передаем анонимно инициализированный интерфейс,
            //оставим его пока пустым, он нам понадобится во второй части задания
            filmsAdapter =
                FilmListRecyclerAdapter(object : FilmListRecyclerAdapter.OnItemClickListener {
                    override fun click(film: Film) {}
                })
            //Присваиваем адаптер
            adapter = filmsAdapter
            //Присвои layoutmanager
            layoutManager = LinearLayoutManager(this@MainActivity)
            //Применяем декоратор для отступов
            val decorator = TopSpacingItemDecoration(8)
            addItemDecoration(decorator)
        }
//Кладем нашу БД в RV
        filmsAdapter.addItems(filmsDataBase)
    }
}


