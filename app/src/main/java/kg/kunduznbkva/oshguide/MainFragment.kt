package kg.kunduznbkva.oshguide

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kg.kunduznbkva.oshguide.databinding.FragmentMainBinding



class MainFragment : Fragment() {
   private lateinit var binding: FragmentMainBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey(ARG_OBJECT)}?.apply {
            binding.txt.text = getInt(ARG_OBJECT).toString()

        }

    }

    companion object{
         const val ARG_OBJECT = "object"

    }



}