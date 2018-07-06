package cemilcakir.com.hotel

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import cemilcakir.com.hotel.Adapters.GalleryAdapter
import cemilcakir.com.hotel.Adapters.HotelAdapter
import cemilcakir.com.hotel.Adapters.RoomAdapter
import cemilcakir.com.hotel.Models.GalleryModel
import cemilcakir.com.hotel.Models.HotelModel
import cemilcakir.com.hotel.Models.RoomModel
import com.bumptech.glide.Glide
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpGet
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.android.synthetic.main.activity_hotel_details.*
import kotlinx.android.synthetic.main.activity_room_details.*
import kotlinx.android.synthetic.main.appbar_hotel_details.*
import kotlinx.android.synthetic.main.hotels_cell.*
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
        try {
            val jsonArr = JSONArray(JsonString)

            println(jsonArr.length())


            var x = 0
            while(x < jsonArr.length()){
                val jsonObj = jsonArr.getJSONObject(x)
                rooms.add(RoomModel(
                        jsonObj.getInt("id"),
                        jsonObj.getString("type"),
                        jsonObj.getString("detail"),
                        jsonObj.getInt("floor"),
                        jsonObj.getInt("size"),
                        jsonObj.getInt("capacity"),
                        jsonObj.getDouble("price")
                ))
                x++
            }
        }
        catch (e:Exception){
            val jsonArr = JSONObject(JsonString)
                rooms.add(RoomModel(
                        jsonArr.getInt("id"),
                        jsonArr.getString("type"),
                        jsonArr.getString("detail"),
                        jsonArr.getInt("floor"),
                        jsonArr.getInt("size"),
                        jsonArr.getInt("capacity"),
                        jsonArr.getDouble("price")
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

        viewManager = LinearLayoutManager(this)
        recyclerView = findViewById(R.id.listRooms)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = viewManager

        Handler().postDelayed({
            AsyncTaskHandleJSon().execute("http://206.189.239.139:8000/rooms")
            AsyncTaskHandleJSonImage().execute("http://206.189.239.139:8000/rooms/2/images")
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

    fun handleJsonImage(JsonString:String?){
        val jsonArr = JSONArray(JsonString)
        val jsonObj = jsonArr.getJSONObject(0)
        Glide.with(imgRoom).load("http://206.189.239.139:8000/"+jsonObj.getString("url")).into(imgRoom)

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
