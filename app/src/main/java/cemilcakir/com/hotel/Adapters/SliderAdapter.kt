package cemilcakir.com.hotel.Adapters

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import cemilcakir.com.hotel.R
import com.squareup.picasso.Picasso

class SliderAdapter:PagerAdapter{

    var context:Context
    var images:Array<String>
    lateinit var layotInflater:LayoutInflater
    constructor(context:Context, images:Array<String>):super(){
        this.context = context
        this.images = images
    }

    override fun isViewFromObject(p0: View, p1: Any): Boolean = p0== p1 as LinearLayout

    override fun getCount(): Int {
        return images.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var image : ImageView
        layotInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var view:View = layotInflater.inflate(R.layout.slider_image_item,container,false)
        image= view.findViewById(R.id.sliderImage)
        //image.setBackgroundResource(images[position])
        Picasso.get()
                .load(images[position])
                .fit()
                .centerCrop()
                .into(image)
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as RelativeLayout)
    }

}