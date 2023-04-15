package kg.kunduznbkva.oshguide.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.kunduznbkva.oshguide.data.Place
import kg.kunduznbkva.oshguide.databinding.ItemLayoutBinding
import kg.kunduznbkva.oshguide.utils.loadImage

class RecyclerAdapter(val itemClickListener: OnItemClickListener): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    private var places:ArrayList<Place>? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: ArrayList<Place>){
        this.places = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
       return places?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(places!![position])
    }


    inner class ViewHolder(private val binding: ItemLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(place: Place) {
            binding.rvItemName.text = place.name
            binding.rvItemAdress.text = place.address
            binding.rvItemWorkTime.text = place.work_time
            binding.rvItemDistance.text = place.distance
            binding.rvItemImg.loadImage(place.img)

            binding.root.setOnClickListener{
                itemClickListener.onItemClick(adapterPosition,place)
            }
        }





    }
}


interface OnItemClickListener {
    fun onItemClick(pos:Int,item: Place)
}