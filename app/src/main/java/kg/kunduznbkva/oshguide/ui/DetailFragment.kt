package kg.kunduznbkva.oshguide.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import kg.kunduznbkva.oshguide.R
import kg.kunduznbkva.oshguide.data.Place
import kg.kunduznbkva.oshguide.databinding.FragmentDetailBinding
import kg.kunduznbkva.oshguide.utils.loadImage

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setPlaceData()
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setPlaceData() {
        val place = arguments?.getParcelable<Place>(PLACE_OBJECT)
        binding.apply {
            detailCollapsingToolbar.title = place?.name
            detailDistanceTxt.text = place?.distance
            detailWorkTxt.text = place?.work_time
            detailAppbarImage.loadImage(place?.img!!)
            detailCollapsingToolbar.title = place.name
            detailToolbarDesc.text = place.address
            detailAverageBillTxt.text = "Средний чек ${place.averageBill}"
            detailToolbar.navigationIcon = requireActivity().getDrawable(R.drawable.arrow_back)
            toolbarBackPress(detailToolbar)
            openMap(detailToolbarDesc,place.latitude,place.longitude)
        }
    }

    private fun toolbarBackPress(toolbar: Toolbar) {
        toolbar.setNavigationOnClickListener {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.my_nav_host_fragment, PlaceListFragment())
                .commit()
        }
    }

    @SuppressLint("QueryPermissionsNeeded")
    private fun openMap(textView: TextView, latitude: Double, longitude: Double) {
        textView.setOnClickListener {
            val location = Uri.parse("geo:0,0?q=$latitude,$longitude")
            val mapIntent = Intent(Intent.ACTION_VIEW, location)
            val activities = requireContext().packageManager.queryIntentActivities(mapIntent, 0)

            if (activities.isNotEmpty()) {
                // At least one app can handle the intent
                startActivity(Intent.createChooser(mapIntent, "Choose an app"))
            } else {
                // No app can handle the intent
                val builder = AlertDialog.Builder(requireContext())
                builder.setMessage("Please install an app that can handle maps")
                    .setCancelable(false)
                    .setPositiveButton("Install") { dialog, id ->
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/search?q=map&c=apps&hl=en&gl=US"))
                        startActivity(intent)
                    }
                    .setNegativeButton("Cancel") { dialog, id ->
                        dialog.cancel()
                    }
                val alert = builder.create()
                alert.show()
            }

        }
    }

    companion object {
        const val PLACE_OBJECT = "place"
    }

}