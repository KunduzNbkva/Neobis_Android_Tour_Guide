package kg.kunduznbkva.oshguide.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import kg.kunduznbkva.oshguide.data.Place
import kg.kunduznbkva.oshguide.R
import kg.kunduznbkva.oshguide.ui.MainActivity
import kg.kunduznbkva.oshguide.ui.MainFragment
import kg.kunduznbkva.oshguide.ui.MainFragment.Companion.ARG_OBJECT

class MainPagerAdapter(fragment: MainActivity) : FragmentStateAdapter(fragment) {
    private val restaurants = arrayListOf(
        Place("Dolce Vita","ул.Шакирова 108a",
            "10:00 - 00:0","1.5km", R.drawable.dolce),
        Place("Borsok","ул.Курманжан-Датка 209",
            "24/7","1.5km", R.drawable.borsok),
        Place("Brio","ул.Курманжан-Датка 211",
            "08:00 - 22:00","1.5km", R.drawable.brio),
        Place("Grenki","ул.Курманжан-Датка 256",
            "08:00 - 24:00","1.5km", R.drawable.grenki),
        Place("Navat","ул.Ленина 288",
            "10:00 - 24:00","1.5km", R.drawable.navat)
    )

    private val fitness = arrayListOf(
        Place("Erem", "ул.С.Супаналиева 234",
            "09:00 - 22:00", "1.5km", R.drawable.erem),
        Place("Pulse","ул.Ленина 232а",
            "07:00 - 24:00","1.5km", R.drawable.pulse),
        Place("Classic","ул.Алиева 143",
            "07:00 - 22:00","1.5km", R.drawable.classic_bassein),
        Place("YogaMammy","ул.Ленина 190",
            "09:00 - 20:00","1.5km", R.drawable.yogamam),
        Place("Life fitness","ул.Монуева 83",
            "08:00 - 23:00","1.5km", R.drawable.lifefitness),
    )
    private val hostels = arrayListOf(
        Place("Rayan Hotel","ул.Ленина 189/2",
            "24/7","1.5km", R.drawable.rayan),
        Place("Ош-Нуру","ул.Баялинова 1",
            "24/7","1.5km", R.drawable.oshnuru
        ),
        Place("Classic","ул.Алиева 143",
            "24/7","1.5km", R.drawable.classic_hotel),
        Place("Tes","ул.Сай Бою 5",
            "24/7","1.5km", R.drawable.tes),
        Place("Sunrise","ул.А.Масалиева 105Б",
            "24/7","1.5km", R.drawable.sunrise),
    )


    override fun createFragment(position: Int): Fragment {
        val fragment = MainFragment()

        when(position){
            0-> passDataFragment(fragment,restaurants)
            1-> passDataFragment(fragment,fitness)
            2-> passDataFragment(fragment,hostels)
        }
        return fragment
    }

    override fun getItemCount(): Int = 3

    private fun passDataFragment( fragment: MainFragment,list:ArrayList<Place>){
        fragment.arguments = Bundle().apply {
            putParcelableArrayList(ARG_OBJECT,list)}
    }

}