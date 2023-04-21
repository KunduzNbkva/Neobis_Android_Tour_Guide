package kg.kunduznbkva.oshguide.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import kg.kunduznbkva.oshguide.data.Place
import kg.kunduznbkva.oshguide.R
import kg.kunduznbkva.oshguide.ui.MainActivity
import kg.kunduznbkva.oshguide.ui.PagerFragment
import kg.kunduznbkva.oshguide.ui.PagerFragment.Companion.ARG_OBJECT

class MainPagerAdapter(fragment: MainActivity) : FragmentStateAdapter(fragment) {
    private val restaurants = arrayListOf(
        Place("Dolce Vita","ул.Шакирова 108a",
            "10:00 - 00:0","1.5km", R.drawable.dolce,
            40.5131449,72.8162043),
        Place("Borsok","ул.Курманжан-Датка 209",
            "24/7","1.5km", R.drawable.borsok,
            40.526221,72.79302),
        Place("Brio","ул.Курманжан-Датка 211",
            "08:00 - 22:00","1.5km", R.drawable.brio,
            40.5268003,72.7920624),
        Place("Grenki","ул.Курманжан-Датка 256",
            "08:00 - 24:00","1.5km", R.drawable.grenki,
            40.5286369,2.792614),
        Place("Navat","ул.Ленина 288",
            "10:00 - 24:00","1.5km", R.drawable.navat,
            40.5227857,72.7981829)
    )

    private val fitness = arrayListOf(
        Place("Erem", "ул.С.Супаналиева 234",
            "09:00 - 22:00", "1.5km", R.drawable.erem,
            40.5191943,72.8020771),
        Place("Pulse","ул.Ленина 232а",
            "07:00 - 24:00","1.5km", R.drawable.pulse,
            40.5176971,72.803063),
        Place("Classic","ул.Алиева 143",
            "07:00 - 22:00","1.5km", R.drawable.classic_bassein,
            40.5165085,72.7971481),
        Place("YogaMammy","ул.Ленина 190",
            "09:00 - 20:00","1.5km", R.drawable.yogamam,
            40.5131764,72.8009),
        Place("Life fitness","ул.Монуева 83",
            "08:00 - 23:00","1.5km", R.drawable.lifefitness,
            40.535011,72.8048829),
    )
    private val hostels = arrayListOf(
        Place("Rayan Hotel","ул.Ленина 189/2",
            "24/7","1.5km", R.drawable.rayan,
            0.5133318,72.8061787),
        Place("Ош-Нуру","ул.Баялинова 1",
            "24/7","1.5km", R.drawable.oshnuru,
            40.521837,72.7979744),
        Place("Classic","ул.Алиева 143",
            "24/7","1.5km", R.drawable.classic_hotel,
            40.5165085,72.7971481),
        Place("Tes","ул.Сай Бою 5",
            "24/7","1.5km", R.drawable.tes,
            40.5223433,72.8005153),
        Place("Sunrise","ул.А.Масалиева 105Б",
            "24/7","1.5km", R.drawable.sunrise,
            40.5408666,72.8005322),
    )


    override fun createFragment(position: Int): Fragment {
        val fragment = PagerFragment()

        when(position){
            0-> passDataFragment(fragment,restaurants)
            1-> passDataFragment(fragment,fitness)
            2-> passDataFragment(fragment,hostels)
        }
        return fragment
    }

    override fun getItemCount(): Int = 3

    private fun passDataFragment( fragment: PagerFragment,list:ArrayList<Place>){
        fragment.arguments = Bundle().apply {
            putParcelableArrayList(ARG_OBJECT,list)}
    }

}