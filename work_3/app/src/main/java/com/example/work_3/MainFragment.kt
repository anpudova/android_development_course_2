package com.example.work_3

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.work_3.databinding.FragmentMainBinding

class MainFragment: Fragment(R.layout.fragment_main) {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private var rvColorsAdapter: ColorsAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainBinding.bind(view)
        initColorsAdapter()
    }

    override fun onDestroyView() {
        binding.rvMain.adapter = null
        super.onDestroyView()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initColorsAdapter() {
        if (rvColorsAdapter == null) {
            rvColorsAdapter = ColorsAdapter().apply {
                items = mutableListOf(
                    ItemModel("Красный", R.drawable.red,
                        "Область цветов в длинноволновой части видимого спектра, соответствует минимальным частотам электромагнитного излучения, воспринимаемого человеческим глазом. Диапазон красных цветов в спектре часто определяют длинами волн 630-760 нанометров, что соответствует частотам 476-394 терагерц. Длинноволновая граница восприятия зависит от возраста человека.",
                        R.color.lilac_100),
                    ItemModel("Оранжевый", R.drawable.orange,
                        "Цвет, промежуточный в спектре электромагнитного излучения оптического диапазона между красным и жёлтым. Электромагнитное излучение, вызывающее ощущение оранжевого цвета, имеет длины волн, лежащие в диапазоне 590-630 нм. Оранжевому соответствует оттенок 20 в цветовой системе MS Windows. В RGB системе оранжевый - цвет 3-го порядка, его можно охарактеризовать как жёлто-красный.",
                        R.color.lilac_100),
                    ItemModel("Желтый", R.drawable.yellow,
                        "Цвета электромагнитного излучения с длинами волн от 550 до 590 нм. Является дополнительным цветом к синему в RGB или дополнительным к фиолетовому в художественной практике и системе RYB. Однако в древности, из-за несовершенства имевшихся пигментов, его рассматривали как дополнительный к пурпурному.",
                        R.color.lilac_100),
                    ItemModel("Зеленый", R.drawable.green,
                        "Один из трёх наряду с красным и синим основных цветов, зелёным считают цвет видимого электромагнитного излучения с длинами волн, лежащими в диапазоне приблизительно 496-555 нм. В системе CMYK в полиграфии зелёный получается при смешении жёлтого и сине-зелёного.",
                        R.color.lilac_100),
                    ItemModel("Голубой", R.drawable.blue,
                        "Это группа оттенков синего с небольшим смещением в сторону зелёного, таким, что ещё совсем нет ощущения зелёного оттенка, либо просто светлые оттенки тонов синего диапазона. Спектральному голубому приблизительно соответствует оттенок 130 в цветовой системе Windows. В более широком смысле голубой можно определить как синеватый или слабо-синий, в таком случае голубыми часто называют вещи, которые по контрасту с окружающими выделяются небольшим синим оттенком.",
                        R.color.lilac_100),
                    ItemModel("Синий", R.drawable.dark_blue,
                        "Наименование группы цветов. Спектральный синий цвет ощущается человеком под действием электромагнитного излучения с длинами волн в диапазоне 440-485 нм. Один из основных цветов в системе КЗС.",
                        R.color.lilac_100),
                    ItemModel("Фиолетовый", R.drawable.violet,
                        "Цвет, соответствующий наиболее коротковолновому монохроматическому излучению, которое способен воспринимать человеческий глаз (диапазон длин волн 380—440 нм). В русской разговорной речи фиолетовым также называют пурпурный цвет и разнообразные красно-фиолетовые оттенки (маджента). Фиолетовому соответствует оттенок 180 в цветовой системе MS Windows.",
                        R.color.lilac_100),
                    ItemModel("Розовый", R.drawable.pink,
                        "Цвет, образующийся при смешивании красного и белого. Хотя иногда его описывают как светло-красный, точнее будет сказать, что это ненасыщенный красный цвет, причём чаще всего с примесью пурпурного. Несмотря на широкое употребление слова, точный оттенок установить трудно.",
                        R.color.lilac_100)
                )
                onItemClickListener = { itemData ->
                    itemData.color = R.color.grey_100
                    val fragment = SecondFragment()
                    val bundle = Bundle()
                    bundle.putString("argHead", itemData.buttonText)
                    bundle.putInt("argImage", itemData.image)
                    bundle.putString("argContent", itemData.contentText)
                    fragment.arguments = bundle
                    val fm: FragmentManager = parentFragmentManager
                    fm.beginTransaction().replace(R.id.fragments_container, fragment).addToBackStack(null).commit()
                }
            }
        }
            with(binding) {
                rvMain.adapter = rvColorsAdapter
                rvMain.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            }
    }

    override fun onResume() {
        super.onResume()
        println("TEST TAG - MainFragment onResume")
    }

    override fun onPause() {
        super.onPause()
        println("TEST TAG - MainFragment onPause")
    }

    companion object {
        const val MAIN_FRAGMENT_TAG = "MAIN_FRAGMENT_TAG"
        fun getInstance() = MainFragment()
    }
}