package cemilcakir.com.hotel.Adapters

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import cemilcakir.com.hotel.Models.GalleryModel
import cemilcakir.com.hotel.R
import com.bumptech.glide.Glide

class GalleryAdapter(private val myDataset: ArrayList<GalleryModel>) :
        RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {

    class ViewHolder(val  cardView:CardView) : RecyclerView.ViewHolder(cardView)

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): GalleryAdapter.ViewHolder {
        val cardView = LayoutInflater.from(parent.context)
                .inflate(R.layout.gallery_cell, parent, false) as CardView

        return ViewHolder(cardView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val galleryModel:GalleryModel = myDataset.get(position)

        val img:ImageView = holder.cardView.findViewById(R.id.img)
        Glide.with(img).load("http://206.189.239.139:8000/"+galleryModel.url).into(img)

    }

    override fun getItemCount() = myDataset.size

}