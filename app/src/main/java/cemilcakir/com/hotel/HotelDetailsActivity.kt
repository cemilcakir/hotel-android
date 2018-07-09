package cemilcakir.com.hotel

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.view.PagerAdapter
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import cemilcakir.com.hotel.Adapters.ImageSliderAdapter
import cemilcakir.com.hotel.Adapters.RoomAdapter
import cemilcakir.com.hotel.Models.GalleryModel
import cemilcakir.com.hotel.Models.HotelModel
import cemilcakir.com.hotel.Models.RoomModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_hotel_details.*
import kotlinx.android.synthetic.main.activity_image_view.*
import kotlinx.android.synthetic.main.activity_room_details.*
import kotlinx.android.synthetic.main.rooms_cell.*
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class HotelDetailsActivity : AppCompatActivity() {

    companion object {
        lateinit var hotelModel: HotelModel
    }


    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

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
        val rooms = ArrayList<RoomModel>()
        val jsonArr = JSONArray(JsonString)

        try {
            var x = 0
            while(x < jsonArr.length()){
                val jsonObj = jsonArr.getJSONObject(x)
                val link = "http://206.189.239.139:8000/rooms/"+jsonObj.getInt("id")+"/images"
                val test = AsyncTaskHandleJSonImage().execute(link).get()
                val hi:String = "http://206.189.239.139:8000"+test.substring(test.indexOf("\\/images"),test.indexOf("\"}")).replace("\\/","/")
                println(hi)

                rooms.add(RoomModel(
                        jsonObj.getInt("id"),
                        jsonObj.getString("type"),
                        jsonObj.getString("detail"),
                        jsonObj.getInt("floor"),
                        jsonObj.getInt("size"),
                        jsonObj.getInt("capacity"),
                        jsonObj.getDouble("price"),
                        hi
                ))
                x++
            }
        }
        catch (e:Exception){
            val jsonArr = JSONObject(JsonString)
            val link = "http://206.189.239.139:8000/rooms/"+jsonArr.getInt("id")+"/images"
            val test = AsyncTaskHandleJSonImage().execute(link).get()
            val hi:String = "http://206.189.239.139:8000"+test.substring(test.indexOf("\\/images"),test.indexOf("\"}")).replace("\\/","/")
            println(hi)

                rooms.add(RoomModel(
                        jsonArr.getInt("id"),
                        jsonArr.getString("type"),
                        jsonArr.getString("detail"),
                        jsonArr.getInt("floor"),
                        jsonArr.getInt("size"),
                        jsonArr.getInt("capacity"),
                        jsonArr.getDouble("price"),
                        hi
                ))
        }
        finally {
            viewAdapter = RoomAdapter(rooms)
            recyclerView.adapter = viewAdapter
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel_details)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setTitle(hotelModel.hotelName+" Detayları")

        viewManager = LinearLayoutManager(this)
        recyclerView = findViewById(R.id.listRooms)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = viewManager

        val hotelImage = "http://206.189.239.139:8000/hotels/"+ hotelModel.id.toString()+"/images"
        val roomInfo = "http://206.189.239.139:8000/hotels/"+hotelModel.id+"/rooms"

        Handler().postDelayed({
            AsyncTaskHandleJSonHotelImage().execute(hotelImage)
            AsyncTaskHandleJSon().execute(roomInfo)
            //AsyncTaskHandleJSonImage().execute("http://206.189.239.139:8000/rooms/2/images")
        }, 100)

        txtHotelDetail.text = hotelModel.hotelDetail

    }

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
        //Glide.with(imgRoom).load("http://206.189.239.139:8000/"+jsonObj.getString("url")).into(imgRoom)
        val tv:String = "http://206.189.239.139:8000/"+jsonObj.getString("url")
        return tv
    }

    inner class AsyncTaskHandleJSonHotelImage: AsyncTask<String, String, String>(){
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
            handleJsonHotelImage(result)
        }
    }

    fun handleJsonHotelImage(JsonString:String?){
        val jsonArr = JSONArray(JsonString)

        val images = ArrayList<GalleryModel>()
        println(jsonArr.length())


        var x = 0
        while(x < jsonArr.length()){
            val jsonObj = jsonArr.getJSONObject(x)
            images.add(GalleryModel(
                    jsonObj.getInt("id"),
                    jsonObj.getString("description"),
                    jsonObj.getString("url"),
                    jsonObj.getInt("hotel_id")
            ))
            x++
        }

        var adapter : PagerAdapter = ImageSliderAdapter(this,images)
        viewPagerHotelDetails.adapter=adapter

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
