package com.example.project_moviesearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.project_moviesearch.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initMenuButtons()
    }

}

private fun initMenuButtons() {
    binding.menuButton.setOnClickListener {
        Toast.makeText(it.context, "Меню", Toast.LENGTH_SHORT).show()
    }
    binding.accountButton.setOnClickListener {
        Toast.makeText(it.context, "Аккаунт", Toast.LENGTH_SHORT).show()
    }
    binding.collectionsButton.setOnClickListener {
        Toast.makeText(it.context, "Коллекция", Toast.LENGTH_SHORT).show()
    }
    binding.favouritesButton.setOnClickListener {
        Toast.makeText(it.context, "Избранное", Toast.LENGTH_SHORT).show()
    }
    binding.watchLaterButton.setOnClickListener {
        Toast.makeText(it.context, "Посмотреть позже", Toast.LENGTH_SHORT).show()
    }


}

