package com.example.project_moviesearch

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.project_moviesearch.databinding.ActivityMainBinding
import com.example.project_moviesearch.fragments.CollectionsFragment
import com.example.project_moviesearch.fragments.DetailsFragment
import com.example.project_moviesearch.fragments.FavouritesFragment
import com.example.project_moviesearch.fragments.HomeFragment
import com.example.project_moviesearch.fragments.WatchLaterFragment


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var backPressed = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initNavigationBars()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_placeholder, HomeFragment())
            .addToBackStack(null)
            .commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {

            if (backPressed + TIME_INTERVAL > System.currentTimeMillis()) {
                super.onBackPressed()
                finish()
            } else {
                Toast.makeText(this, "Нажмите еще раз, чтобы выйти", Toast.LENGTH_SHORT).show()
            }

            backPressed = System.currentTimeMillis()
        } else
            super.onBackPressed()

    }

    private fun initNavigationBars() {
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

                R.id.home -> {
                    val tag = "home"
                    val fragment = checkFragmentExistence(tag)
                    changeFragment(fragment?: HomeFragment(), tag)
                    true
                }

                R.id.watch_later -> {
                    val tag = "watch_later"
                    val fragment = checkFragmentExistence(tag)
                    changeFragment( fragment?: WatchLaterFragment(), tag)
                    true
                }

                R.id.favourites -> {
                    val tag = "favorites"
                    val fragment = checkFragmentExistence(tag)
                    changeFragment( fragment?: FavouritesFragment(), tag)
                    true
                }

                R.id.collections -> {
                    val tag = "collections"
                    val fragment = checkFragmentExistence(tag)
                    changeFragment( fragment?: CollectionsFragment(), tag)
                    true

                }

                else -> false
            }
        }

    }

    fun launchDetailsFragment(film: Film) {
        //Создаем "посылку"
        val bundle = Bundle()
        //Кладем наш фильм в "посылку"
        bundle.putParcelable("film", film)
        //Кладем фрагмент с деталями в перменную
        val detailsFragment = DetailsFragment()
        //Прикрепляем нашу "посылку" к фрагменту
        detailsFragment.arguments = bundle

        //Запускаем фрагмент
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_placeholder, detailsFragment)
            .addToBackStack(null)
            .commit()
    }

    //Ищем фрагмент по тегу, если он есть то возвращаем его, если нет, то null
    private fun checkFragmentExistence(tag: String): Fragment? = supportFragmentManager.findFragmentByTag(tag)

    private fun changeFragment(fragment: Fragment, tag: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_placeholder, fragment, tag)
            .addToBackStack(null)
            .commit()
    }

    companion object {
        const val TIME_INTERVAL = 2000
    }
}


