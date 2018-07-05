package cemilcakir.com.hotel.Fragments


import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cemilcakir.com.hotel.Adapters.GalleryAdapter
import cemilcakir.com.hotel.Models.GalleryModel

import cemilcakir.com.hotel.R
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpGet
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory


class GalleryFragment : android.support.v4.app.Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view:View = inflater.inflate(R.layout.fragment_gallery, container, false)

        viewManager = LinearLayoutManager(activity)
        recyclerView = view.findViewById(R.id.listGallery)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = viewManager

        Handler().postDelayed({
            getData()
        }, 100)

        return view
    }

    fun getData()
    {

        "http://206.189.239.139:8000/images".httpGet().responseJson { request, response, result ->
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
            val type = Types.newParameterizedType(List::class.java, GalleryModel::class.java)
            val adapter = moshi.adapter<ArrayList<GalleryModel>>(type)
            val text : String = "[{\"id\":1,\"description\":\"test\",\"hotel_id\":2,\"url\":\"\\/images\\/d8396f89342055143ab34075bd8a203a.jpeg\"},{\"id\":2,\"description\":\"test2\",\"hotel_id\":2,\"url\":\"\\/images\\/46713ff0e5f5a390d3e153267c7c646c.png\"},{\"id\":4,\"description\":\"test\",\"hotel_id\":2,\"room_id\":2,\"url\":\"\\/images\\/83e890efb06f8251c79dff094fd7eb8f.jpeg\"},{\"id\":6,\"description\":\"test\",\"hotel_id\":2,\"room_id\":2,\"url\":\"\\/images\\/dae0931d96ac238377ea7761f66d4f90.jpeg\"},{\"id\":7,\"description\":\"test\",\"hotel_id\":2,\"room_id\":2,\"url\":\"\\/images\\/98b2230a36c7db98d6ceb4d1dc18d6b9.jpeg\"},{\"id\":8,\"description\":\"\",\"hotel_id\":2,\"url\":\"\\/images\\/67bc1355c8bf3fb46aa623e6da1f611a.png\"},{\"id\":9,\"description\":\"\",\"hotel_id\":2,\"url\":\"\\/images\\/e6b887d3f6d595900251f35077a7e59f.png\"},{\"id\":10,\"description\":\"\",\"hotel_id\":2,\"url\":\"\\/images\\/2e901ed440e1cea2cb872a0842a887b0.png\"},{\"id\":11,\"description\":\"\",\"hotel_id\":4,\"url\":\"\\/images\\/8fad0906ce751e05f442eaec56d13d87.jpeg\"},{\"id\":12,\"description\":\"\",\"hotel_id\":4,\"url\":\"\\/images\\/5c5c96b73c6f328d7b63848ebd5cab0f.jpeg\"}]"
            val users:ArrayList<GalleryModel> = adapter.fromJson(text) as ArrayList<GalleryModel>

            viewAdapter = GalleryAdapter(users)
            activity.run {  recyclerView.adapter = viewAdapter }

        }
    }


}
