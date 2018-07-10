package cemilcakir.com.hotel.Adapters

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import cemilcakir.com.hotel.Models.GalleryModel
import com.bumptech.glide.Glide
import com.github.chrisbanes.photoview.PhotoView
import cemilcakir.com.hotel.R

class ImageSliderAdapter:PagerAdapter{

    private var context:Context
    private var images:ArrayList<GalleryModel>
    private lateinit var inflater:LayoutInflater

    constructor(context:Context, images:ArrayList<GalleryModel>):super(){
        this.context = context
        this.images = images
    }

    override fun isViewFromObject(p0: View, p1: Any): Boolean {
        return p0 == p1 as RelativeLayout
    }

    override fun getCount(): Int {
        return images.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val galleryModel:GalleryModel= images.get(position)

        inflater= context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var view = inflater.inflate(R.layout.slider_image_item, container, false)

        var image : PhotoView = view.findViewById(R.id.sliderImage)
        Glide.with(image).load("http://206.189.239.139:8000/"+galleryModel.url).into(image)

        container.addView(view,0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as RelativeLayout)
    }
}