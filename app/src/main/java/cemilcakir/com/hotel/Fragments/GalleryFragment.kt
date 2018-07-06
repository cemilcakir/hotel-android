package cemilcakir.com.hotel.Fragments


import android.os.AsyncTask
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
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL


class GalleryFragment : android.support.v4.app.Fragment() {

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

        viewAdapter = GalleryAdapter(images)
        activity.run {  recyclerView.adapter = viewAdapter }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view:View = inflater.inflate(R.layout.fragment_gallery, container, false)

        viewManager = LinearLayoutManager(activity)
        recyclerView = view.findViewById(R.id.listGallery)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = viewManager

        Handler().postDelayed({
            AsyncTaskHandleJSon().execute("http://206.189.239.139:8000/images")
        }, 100)

        return view
    }

}
