package kg.kunduznbkva.oshguide.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
            detailToolbar.navigationIcon = requireActivity().getDrawable(R.drawable.arrow_back)
            toolbarBackPress(detailToolbar)
            openMap(detailToolbarDesc, place.name,place.latitude,place.longitude)
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

    private fun openMap(textView: TextView, name: String, latitude: Double, longitude: Double) {
        textView.setOnClickListener {
            val url = "waze://?ll=$latitude, $longitude&navigate=yes"
            val intentWaze = Intent(Intent.ACTION_VIEW, Uri.parse(url)).setPackage("com.waze")

            val uriGoogle = "google.navigation:q=$latitude,$longitude"
            val intentGoogleNav = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(uriGoogle)
            ).setPackage(getString(R.string.google_maps_package))

            val chooserIntent = Intent.createChooser(intentGoogleNav, name)
            val arr = arrayOfNulls<Intent>(1)
            arr[0] = intentWaze
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, arr)
            requireContext().startActivity(chooserIntent)
        }
    }

    companion object {
        const val PLACE_OBJECT = "place"
    }

}