package kg.kunduznbkva.oshguide.data


import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize

@Parcelize
data class Place(
    val name: String,
    val address: String,
    val work_time: String,
    val distance: String,
    val img: Int,
    val longitude: Double,
    val latitude: Double
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readDouble()
    )

    companion object : Parceler<Place> {

        override fun Place.write(parcel: Parcel, flags: Int) {
            parcel.writeString(name)
            parcel.writeString(address)
            parcel.writeString(work_time)
            parcel.writeInt(img)
        }

        override fun create(parcel: Parcel): Place {
            return Place(parcel)
        }
    }
}