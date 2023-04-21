package kg.kunduznbkva.oshguide.ui

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kg.kunduznbkva.oshguide.R
import kg.kunduznbkva.oshguide.adapters.OnItemClickListener
import kg.kunduznbkva.oshguide.adapters.RecyclerAdapter
import kg.kunduznbkva.oshguide.data.Place
import kg.kunduznbkva.oshguide.databinding.FragmentPagerBinding
import kg.kunduznbkva.oshguide.ui.DetailFragment.Companion.PLACE_OBJECT

class PagerFragment : Fragment(), OnItemClickListener {
   private lateinit var binding: FragmentPagerBinding
   private lateinit var adapter: RecyclerAdapter
   private var list = ArrayList<Place>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPagerBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecyclerView()
        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                list = getParcelableArrayList(ARG_OBJECT, Place::class.java) as ArrayList<Place>
                adapter.setData(list)
            }else{
                list = getParcelableArrayList<Place>(ARG_OBJECT) as ArrayList<Place>

            }
        }
    }

    private fun initRecyclerView() {
        adapter = RecyclerAdapter(this)
        binding.recyclerView.adapter = adapter
    }

    override fun onItemClick(pos: Int, item: Place) {
        val bundle = Bundle()
        bundle.putParcelable(PLACE_OBJECT,item)

        val fragment = DetailFragment()
        fragment.arguments = bundle

        parentFragmentManager.beginTransaction()
            .replace(R.id.my_nav_host_fragment,fragment)
            .commit()
    }

    companion object{
        const val ARG_OBJECT = "object"
    }
}


