package cemilcakir.com.hotel.Fragments


import android.os.Bundle
import android.app.Fragment
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cemilcakir.com.hotel.Adapters.HotelAdapter
import cemilcakir.com.hotel.Models.HotelModel

import cemilcakir.com.hotel.R
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpGet
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.json.JSONObject
import java.net.URL
import com.google.gson.JsonObject
import com.koushikdutta.async.future.FutureCallback
import com.koushikdutta.ion.Ion




class HotelsFragment : android.support.v4.app.Fragment(){

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view:View = inflater.inflate(R.layout.fragment_hotels, container, false)

//        viewManager = LinearLayoutManager(activity)
//        recyclerView = view.findViewById(R.id.listHotels)
//        recyclerView.setHasFixedSize(true)
//        recyclerView.layoutManager = viewManager

        Handler().postDelayed({
            getData()
        }, 100)

        return view
    }

    fun getData()
    {
        Ion.with(context)
                .load("http://localhost:8001/hotels")
                .asJsonObject()
                .setCallback { e, result ->
                    println(result)
                }

//        "http://localhost:8001/hotels".httpGet().responseJson { request, response, result ->
//            val data: JSONObject = result.get().obj()
//            val moshi = Moshi.Builder()
//                    .add(KotlinJsonAdapterFactory())
//                    .build()
//            val type = Types.newParameterizedType(List::class.java, HotelModel::class.java)
//            val adapter = moshi.adapter<ArrayList<HotelModel>>(type)
//            val users:ArrayList<HotelModel> = adapter.fromJson(data.toString()) as ArrayList<HotelModel>

//            val moshi = Moshi.Builder()
//                    .add(KotlinJsonAdapterFactory())
//                    .build()
//            val type = Types.newParameterizedType(List::class.java, HotelModel::class.java)
//            val adapter = moshi.adapter<ArrayList<HotelModel>>(type)
//            val text : String = "[{\"id\":4,\"name\":\"Divan Hotel\",\"link\":\"www.divanhotel.com\",\"star\":3,\"phone\":\"15367363507\",\"mail\":\"info@divanhotel.com\",\"county\":\"BAYRAMPA\\u015eA\",\"province\":\"\\u0130STANBUL\",\"address\":\"Alt\\u0131ntepsi Mah. Mete sk. no44\\/5\",\"detail\":\"popopoppop\"},{\"id\":4,\"name\":\"Divan Hotel\",\"link\":\"www.divanhotel.com\",\"star\":3,\"phone\":\"15367363507\",\"mail\":\"info@divanhotel.com\",\"county\":\"BAYRAMPA\\u015eA\",\"province\":\"\\u0130STANBUL\",\"address\":\"Alt\\u0131ntepsi Mah. Mete sk. no44\\/5\",\"detail\":\"popopoppop\"},{\"id\":4,\"name\":\"Divan Hotel\",\"link\":\"www.divanhotel.com\",\"star\":3,\"phone\":\"15367363507\",\"mail\":\"info@divanhotel.com\",\"county\":\"BAYRAMPA\\u015eA\",\"province\":\"\\u0130STANBUL\",\"address\":\"Alt\\u0131ntepsi Mah. Mete sk. no44\\/5\",\"detail\":\"popopoppop\"},{\"id\":4,\"name\":\"Divan Hotel\",\"link\":\"www.divanhotel.com\",\"star\":3,\"phone\":\"15367363507\",\"mail\":\"info@divanhotel.com\",\"county\":\"BAYRAMPA\\u015eA\",\"province\":\"\\u0130STANBUL\",\"address\":\"Alt\\u0131ntepsi Mah. Mete sk. no44\\/5\",\"detail\":\"popopoppop\"}]"
//            val users:ArrayList<HotelModel> = adapter.fromJson(text) as ArrayList<HotelModel>

//            viewAdapter = HotelAdapter(users)
//            activity.run {  recyclerView.adapter = viewAdapter }

        }
    }
