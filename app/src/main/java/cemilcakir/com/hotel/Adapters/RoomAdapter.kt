package cemilcakir.com.hotel.Adapters

import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import cemilcakir.com.hotel.Models.RoomModel
import cemilcakir.com.hotel.R
import cemilcakir.com.hotel.RoomDetailsActivity
import com.bumptech.glide.Glide

class RoomAdapter(private val myDataset: ArrayList<RoomModel>) :
        RecyclerView.Adapter<RoomAdapter.ViewHolder>() {

    class ViewHolder(val  cardView:CardView) : RecyclerView.ViewHolder(cardView)

    lateinit var imgR : ImageView

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): RoomAdapter.ViewHolder {
        val cardView = LayoutInflater.from(parent.context)
                .inflate(R.layout.rooms_cell, parent, false) as CardView

        return ViewHolder(cardView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val roomModel:RoomModel = myDataset.get(position)

        val txtRoomType: TextView = holder.cardView.findViewById(R.id.txtRoomType)
        txtRoomType.text = roomModel.roomType

        val txtRoomDetail: TextView = holder.cardView.findViewById(R.id.txtRoomDetail)
        txtRoomDetail.text = roomModel.roomDetail!!.substring(0,25)+"..."

        val imgRoom:ImageView = holder.cardView.findViewById(R.id.imgRoom)
        Glide.with(imgRoom).load(roomModel.roomImage).into(imgRoom)

        holder.cardView.setOnClickListener {
            val i = Intent(it.context, RoomDetailsActivity::class.java)
            RoomDetailsActivity.roomModel = roomModel
            startActivity(it.context, i ,null)
        }
    }

    override fun getItemCount() = myDataset.size

}