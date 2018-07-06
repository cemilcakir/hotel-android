package cemilcakir.com.hotel

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import cemilcakir.com.hotel.Adapters.TabAdapter
import cemilcakir.com.hotel.Fragments.GalleryFragment
import cemilcakir.com.hotel.Fragments.HotelsFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.appbar_main.*
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    var pagerAdapter: TabAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar_main)

        pagerAdapter = TabAdapter(supportFragmentManager)
        pagerAdapter!!.addFragments(HotelsFragment(), "Hotels")
        pagerAdapter!!.addFragments(GalleryFragment(), "Gallery")

        viewPager.adapter = pagerAdapter
        tabLayout.setupWithViewPager(viewPager)

    }
}
