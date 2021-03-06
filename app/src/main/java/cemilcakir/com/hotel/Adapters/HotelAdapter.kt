package cemilcakir.com.hotel.Adapters

import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import cemilcakir.com.hotel.HotelDetailsActivity
import cemilcakir.com.hotel.Models.HotelModel
import cemilcakir.com.hotel.R
import com.bumptech.glide.Glide

class HotelAdapter(private val myDataset: ArrayList<HotelModel>) :
        RecyclerView.Adapter<HotelAdapter.ViewHolder>() {

    class ViewHolder(val  cardView:CardView) : RecyclerView.ViewHolder(cardView)

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): HotelAdapter.ViewHolder {
        val cardView = LayoutInflater.from(parent.context)
                .inflate(R.layout.hotels_cell, parent, false) as CardView

        return ViewHolder(cardView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val hotelModel:HotelModel = myDataset.get(position)

        val txtHotelName: TextView = holder.cardView.findViewById(R.id.txtHotelName)
        txtHotelName.text = hotelModel.hotelName

        val txtHotelDetail: TextView = holder.cardView.findViewById(R.id.txtHotelDesc)
        txtHotelDetail.text = hotelModel.hotelDetail!!.substring(0,25)+"..."

        val imgHotel:ImageView = holder.cardView.findViewById(R.id.imgHotel)
        Glide.with(imgHotel).load(hotelModel.hotelImage).into(imgHotel)

        holder.cardView.setOnClickListener {
            val i = Intent(it.context, HotelDetailsActivity::class.java)
            HotelDetailsActivity.hotelModel = hotelModel
            startActivity(it.context, i ,null)
        }
    }

    override fun getItemCount() = myDataset.size

}