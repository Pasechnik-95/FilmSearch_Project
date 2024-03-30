package com.example.project_moviesearch.fragments

import AnimationHelper
import TopSpacingItemDecoration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project_moviesearch.Film
import com.example.project_moviesearch.FilmListRecyclerAdapter
import com.example.project_moviesearch.MainActivity
import com.example.project_moviesearch.R
import com.example.project_moviesearch.databinding.FragmentFavouritesBinding

val favoritesList: List<Film> = mutableListOf(
    Film(
        "Зеленая миля",
        R.drawable.the_green_mile,
        "Пол Эджкомб — начальник блока смертников в тюрьме «Холодная гора», каждый из узников которого однажды проходит «зеленую милю» по пути к месту казни. Пол повидал много заключённых и надзирателей за время работы. Однако гигант Джон Коффи, обвинённый в страшном преступлении, стал одним из самых необычных обитателей блока."
    )
)

class FavouritesFragment : Fragment() {

    private lateinit var filmsAdapter: FilmListRecyclerAdapter
    private var _binding: FragmentFavouritesBinding? = null
    private val binding: FragmentFavouritesBinding get() = _binding!!

        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouritesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        AnimationHelper.performFragmentCircularRevealAnimation(binding.homeFragmentRoot, requireActivity(), 2)

        binding.favoritesRecycler.apply {
            filmsAdapter =
                FilmListRecyclerAdapter(object : FilmListRecyclerAdapter.OnItemClickListener {
                    override fun click(film: Film) {
                        (requireActivity() as MainActivity).launchDetailsFragment(film)
                    }
                })
            //Присваиваем адаптер
            adapter = filmsAdapter
            //Присвои layoutmanager
            layoutManager = LinearLayoutManager(requireContext())
            //Применяем декоратор для отступов
            val decorator = TopSpacingItemDecoration(8)
            addItemDecoration(decorator)
        }

        filmsAdapter.addItems(favoritesList)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}