package kg.kunduznbkva.oshguide.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kg.kunduznbkva.oshguide.R
import kg.kunduznbkva.oshguide.adapters.MainPagerAdapter
import kg.kunduznbkva.oshguide.databinding.FragmentPlaceListBinding
import kg.kunduznbkva.oshguide.utils.ZoomOutPageTransformer


class PlaceListFragment : Fragment() {
   private lateinit var binding: FragmentPlaceListBinding
    private lateinit var adapter: MainPagerAdapter
    private lateinit var tabLayout: TabLayout
    private lateinit var tabNames : Array<String>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlaceListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        initViews()
    }


    private fun initViews() {
        adapter = MainPagerAdapter(requireActivity() as MainActivity)
        binding.pager.adapter = adapter
        binding.pager.setPageTransformer(ZoomOutPageTransformer())

        tabLayout = binding.tabLayout
        TabLayoutMediator(tabLayout, binding.pager) { tab, position ->
            tab.text = tabNames[position]
        }.attach()
    }

    fun initList(){
        tabNames = arrayOf(
            getString(R.string.restaurants),
            getString(R.string.fitness),
            getString(R.string.hostels),
        )
    }

}