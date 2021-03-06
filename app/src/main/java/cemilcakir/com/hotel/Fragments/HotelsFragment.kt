package cemilcakir.com.hotel.Fragments


import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import cemilcakir.com.hotel.Adapters.HotelAdapter
import cemilcakir.com.hotel.Models.HotelModel

import cemilcakir.com.hotel.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_hotel_details.*
import kotlinx.android.synthetic.main.hotels_cell.*
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL


class HotelsFragment : android.support.v4.app.Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    var hotelImg:String? = null

    inner class AsyncTaskHandleJSonImage: AsyncTask<String, String, String>(){
        override fun doInBackground(vararg url: String?): String {

            var text:String
            val connection = URL(url[0]).openConnection() as HttpURLConnection
            try {
                connection.connect()
                text = connection.inputStream.use { it.reader().use{reader -> reader.readText()} }
            }
            finally {
                connection.disconnect()
            }

            return text
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            handleJsonImage(result)
        }
    }

    fun handleJsonImage(JsonString:String?):String{
        val jsonArr = JSONArray(JsonString)
        val jsonObj = jsonArr.getJSONObject(0)
        val tv:String = "http://206.189.239.139:8000/"+jsonObj.getString("url")
        return tv
    }

    inner class AsyncTaskHandleJSon: AsyncTask<String, String, String>(){
        override fun doInBackground(vararg url: String?): String {

            var text:String
            val connection = URL(url[0]).openConnection() as HttpURLConnection
            try {
                connection.connect()
                text = connection.inputStream.use { it.reader().use{reader -> reader.readText()} }
            }
            finally {
                connection.disconnect()
            }

            return text
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            handleJson(result)
        }
    }

    fun handleJson(JsonString:String?){
        val jsonArr = JSONArray(JsonString)
        val hotels = ArrayList<HotelModel>()
        var x = 0

        while(x < jsonArr.length()){
            val jsonObj = jsonArr.getJSONObject(x)
            val link = "http://206.189.239.139:8000/hotels/"+jsonObj.getInt("id")+"/images"
            val test = AsyncTaskHandleJSonImage().execute(link).get()
            val hi:String = "http://206.189.239.139:8000"+test.substring(test.indexOf("\\/images"),test.indexOf("\"}")).replace("\\/","/")
            println(hi)
            Handler().postDelayed({
                hotels.add(HotelModel(
                        jsonObj.getInt("id"),
                        jsonObj.getString("name"),
                        jsonObj.getString("link"),
                        jsonObj.getInt("star"),
                        jsonObj.getString("phone"),
                        jsonObj.getString("mail"),
                        jsonObj.getString("county"),
                        jsonObj.getString("province"),
                        jsonObj.getString("address"),
                        jsonObj.getString("detail"),
                        hi
                ))
            },100)
            x++
        }

        viewAdapter = HotelAdapter(hotels)
        activity.run {  recyclerView.adapter = viewAdapter }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view: View = inflater.inflate(R.layout.fragment_hotels, container, false)

        viewManager = LinearLayoutManager(activity)
        recyclerView = view.findViewById(R.id.listHotels)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = viewManager

        Handler().postDelayed({
            AsyncTaskHandleJSon().execute("http://206.189.239.139:8000/hotels")
        },100)

        return view
    }

}
