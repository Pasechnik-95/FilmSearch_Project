package com.example.project_moviesearch

import TopSpacingItemDecoration
import android.os.Bundle
import android.transition.Scene
import android.transition.Slide
import android.transition.TransitionManager
import android.transition.TransitionSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project_moviesearch.databinding.FragmentHomeBinding
import com.example.project_moviesearch.databinding.MergeHomeScreenContentBinding
import java.util.Locale

val filmsDataBase = mutableListOf(
    Film(
        "Зеленая миля",
        R.drawable.the_green_mile,
        "Пол Эджкомб — начальник блока смертников в тюрьме «Холодная гора», каждый из узников которого однажды проходит «зеленую милю» по пути к месту казни. Пол повидал много заключённых и надзирателей за время работы. Однако гигант Джон Коффи, обвинённый в страшном преступлении, стал одним из самых необычных обитателей блока."
    ),
    Film(
        "1+1",
        R.drawable.intouchables,
        "Аристократ на коляске нанимает в сиделки бывшего заключенного. Искрометная французская комедия с Омаром Си."
    ),
    Film(
        "Побег из Шоушенка",
        R.drawable.the_shawshank_redemption,
        "Бухгалтер Энди Дюфрейн обвинён в убийстве собственной жены и её любовника. Оказавшись в тюрьме под названием Шоушенк, он сталкивается с жестокостью и беззаконием, царящими по обе стороны решётки. Каждый, кто попадает в эти стены, становится их рабом до конца жизни. Но Энди, обладающий живым умом и доброй душой, находит подход как к заключённым, так и к охранникам, добиваясь их особого к себе расположения."
    ),
    Film(
        "Форрест Гамп",
        R.drawable.forrest_gump,
        "Сидя на автобусной остановке, Форрест Гамп — не очень умный, но добрый и открытый парень — рассказывает случайным встречным историю своей необыкновенной жизни."
    ),
    Film(
        "Интерстеллар",
        R.drawable.interstellar,
        "Когда засуха, пыльные бури и вымирание растений приводят человечество к продовольственному кризису, коллектив исследователей и учёных отправляется сквозь червоточину (которая предположительно соединяет области пространства-времени через большое расстояние) в путешествие, чтобы превзойти прежние ограничения для космических путешествий человека и найти планету с подходящими для человечества условиями."
    ),
    Film(
        "Унесённые призраками",
        R.drawable.sen_to_chihiro_no_kamikakushi,
        "Тихиро с мамой и папой переезжает в новый дом. Заблудившись по дороге, они оказываются в странном пустынном городе, где их ждет великолепный пир. Родители с жадностью набрасываются на еду и к ужасу девочки превращаются в свиней, став пленниками злой колдуньи Юбабы. Теперь, оказавшись одна среди волшебных существ и загадочных видений, Тихиро должна придумать, как избавить своих родителей от чар коварной старухи."
    ),
    Film(
        "Властелин колец: Возвращение короля",
        R.drawable.the_lord_of_the_rings_the_return_of_the_king,
        "Повелитель сил тьмы Саурон направляет свою бесчисленную армию под стены Минас-Тирита, крепости Последней Надежды. Он предвкушает близкую победу, но именно это мешает ему заметить две крохотные фигурки — хоббитов, приближающихся к Роковой Горе, где им предстоит уничтожить Кольцо Всевластья."
    ),
    Film(
        "Бойцовский клуб",
        R.drawable.fight_club,
        "Страховой работник разрушает рутину своей благополучной жизни. Культовая драма по книге Чака Паланика."
    ),
    Film(
        "Список Шиндлера",
        R.drawable.schindlers_list,
        "Фильм рассказывает реальную историю загадочного Оскара Шиндлера, члена нацистской партии, преуспевающего фабриканта, спасшего во время Второй мировой войны почти 1200 евреев."
    ),
)

class HomeFragment : Fragment() {
    private lateinit var filmsAdapter: FilmListRecyclerAdapter
    private var _bindingHome: FragmentHomeBinding? = null
    private val bindingHome: FragmentHomeBinding get() = _bindingHome!!
    private lateinit var  bindingMerge: MergeHomeScreenContentBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _bindingHome = FragmentHomeBinding.inflate(inflater, container, false)
        return bindingHome.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindingMerge = MergeHomeScreenContentBinding.inflate(layoutInflater, bindingHome.homeFragmentRoot, false)

        val scene = Scene(
            bindingHome.homeFragmentRoot,
            bindingMerge.root
        )
        val searchSlide = Slide(Gravity.TOP).addTarget(R.id.search_view)
        //Создаем анимацию выезда RV снизу
        val recyclerSlide = Slide(Gravity.BOTTOM).addTarget(R.id.main_recycler)
        //Создаем экземпляр TransitionSet, который объединит все наши анимации
        val customTransition = TransitionSet().apply {
            //Устанавливаем время, за которое будет проходить анимация
            duration = 500
            //Добавляем сами анимации
            addTransition(recyclerSlide)
            addTransition(searchSlide)
        }
        //Также запускаем через TransitionManager, но вторым параметром передаем нашу кастомную анимацию
        TransitionManager.go(scene, customTransition)

        initRecyclerView()
        filmsAdapter.addItems(filmsDataBase)

        //Поиск по нажатию на все поле
        bindingMerge.searchView.setOnClickListener {
            bindingMerge.searchView.isIconified = false
        }

        //Подключение слушателя изменений текста в поле поиска
        bindingMerge.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            //Этот метод отрабатывает при нажатии кнопки "поиск" на софт клавиатуре
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            //Этот метод отрабатывает на каждое изменения текста
            override fun onQueryTextChange(newText: String): Boolean {
                //Если ввод пуст то вставляем в адаптер всю БД
                if (newText.isEmpty()) {
                    filmsAdapter.addItems(filmsDataBase)
                    return true
                }
                //Фильтруем список на поиск подходящих сочетаний
                val result = filmsDataBase.filter {
                    //Чтобы все работало правильно, нужно и запрос, и имя фильма приводить к нижнему регистру
                    it.title.lowercase(Locale.getDefault())
                        .contains(newText.lowercase(Locale.getDefault()))
                }
                //Добавляем в адаптер
                filmsAdapter.addItems(result)
                return true
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _bindingHome = null
    }

    private fun initRecyclerView() {

        bindingMerge.mainRecycler.apply {
            //Инициализируем наш адаптер
            filmsAdapter =
                FilmListRecyclerAdapter(object : FilmListRecyclerAdapter.OnItemClickListener {
                    override fun click(film: Film) {
                        (requireActivity() as MainActivity).launchDetailsFragment(film)
                    }
                })
            layoutManager = LinearLayoutManager(requireContext())
            //Декоратор для отступов между элементами
            val decorator = TopSpacingItemDecoration(8)
            addItemDecoration(decorator)
            //Присваиваем адаптер
            adapter = filmsAdapter

        }


    }
}