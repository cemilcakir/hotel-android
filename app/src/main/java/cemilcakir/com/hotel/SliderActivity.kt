package cemilcakir.com.hotel

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import cemilcakir.com.hotel.Adapters.SliderAdapter
import kotlinx.android.synthetic.main.activity_slider.*

class SliderActivity : AppCompatActivity() {

    var images:Array<String> = arrayOf("https://cdn.pixabay.com/photo/2016/11/11/23/34/cat-1817970_960_720.jpg",
            "https://cdn.pixabay.com/photo/2017/12/21/12/26/glowworm-3031704_960_720.jpg",
            "https://cdn.pixabay.com/photo/2017/12/24/09/09/road-3036620_960_720.jpg",
            "https://cdn.pixabay.com/photo/2017/11/07/00/07/fantasy-2925250_960_720.jpg",
            "https://cdn.pixabay.com/photo/2017/10/10/15/28/butterfly-2837589_960_720.jpg")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slider)

        var adapter : PagerAdapter = SliderAdapter(this,images)
        viewPagerSlider.adapter=adapter
    }

}
