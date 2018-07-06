package cemilcakir.com.hotel

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import cemilcakir.com.hotel.Adapters.HotelAdapter
import cemilcakir.com.hotel.Adapters.RoomAdapter
import cemilcakir.com.hotel.Models.HotelModel
import cemilcakir.com.hotel.Models.RoomModel
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpGet
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.android.synthetic.main.appbar_hotel_details.*

class HotelDetailsActivity : AppCompatActivity() {

    companion object {
        lateinit var hotelId:String
    }


    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

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
            getData()
        }, 100)

    }

    fun getData()
    {

        "http://206.189.239.139:8000/rooms".httpGet().responseJson { request, response, result ->
            //            val data: JSONObject = result.get().obj()
//            val moshi = Moshi.Builder()
//                    .add(KotlinJsonAdapterFactory())
//                    .build()
//            val type = Types.newParameterizedType(List::class.java, HotelModel::class.java)
//            val adapter = moshi.adapter<ArrayList<HotelModel>>(type)
//            val users:ArrayList<HotelModel> = adapter.fromJson(data.toString()) as ArrayList<HotelModel>

            val moshi = Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()
            val type = Types.newParameterizedType(List::class.java, RoomModel::class.java)
            val adapter = moshi.adapter<ArrayList<RoomModel>>(type)
            val text : String = "[{\"id\":2,\"type\":\"test\",\"price\":21,\"floor\":21,\"size\":21,\"capacity\":21,\"detail\":\"ewqqqqqqeqweqweqwqweeqw\",\"hotel_id\":2},{\"id\":2,\"type\":\"test\",\"price\":21,\"floor\":21,\"size\":21,\"capacity\":21,\"detail\":\"ewqqqqqqeqweqweqwqweeqw\",\"hotel_id\":2},{\"id\":2,\"type\":\"test\",\"price\":21,\"floor\":21,\"size\":21,\"capacity\":21,\"detail\":\"ewqqqqqqeqweqweqwqweeqw\",\"hotel_id\":2},{\"id\":2,\"type\":\"test\",\"price\":21,\"floor\":21,\"size\":21,\"capacity\":21,\"detail\":\"ewqqqqqqeqweqweqwqweeqw\",\"hotel_id\":2},{\"id\":2,\"type\":\"test\",\"price\":21,\"floor\":21,\"size\":21,\"capacity\":21,\"detail\":\"ewqqqqqqeqweqweqwqweeqw\",\"hotel_id\":2},{\"id\":2,\"type\":\"test\",\"price\":21,\"floor\":21,\"size\":21,\"capacity\":21,\"detail\":\"ewqqqqqqeqweqweqwqweeqw\",\"hotel_id\":2},{\"id\":2,\"type\":\"test\",\"price\":21,\"floor\":21,\"size\":21,\"capacity\":21,\"detail\":\"ewqqqqqqeqweqweqwqweeqw\",\"hotel_id\":2},{\"id\":2,\"type\":\"test\",\"price\":21,\"floor\":21,\"size\":21,\"capacity\":21,\"detail\":\"ewqqqqqqeqweqweqwqweeqw\",\"hotel_id\":2},{\"id\":2,\"type\":\"test\",\"price\":21,\"floor\":21,\"size\":21,\"capacity\":21,\"detail\":\"ewqqqqqqeqweqweqwqweeqw\",\"hotel_id\":2}]"
            val rooms:ArrayList<RoomModel> = adapter.fromJson(text) as ArrayList<RoomModel>

            viewAdapter = RoomAdapter(rooms)
            recyclerView.adapter = viewAdapter

        }
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
