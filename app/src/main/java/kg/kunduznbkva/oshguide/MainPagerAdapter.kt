package kg.kunduznbkva.oshguide

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class MainPagerAdapter(fragment: MainActivity): FragmentStateAdapter(fragment) {
    override fun getItemCount():Int = 3

    override fun createFragment(position: Int): Fragment {
        val fragment = MainFragment()
        fragment.arguments= Bundle().apply {
            putInt(MainFragment.ARG_OBJECT,position+1)
        }
        return fragment
    }

}