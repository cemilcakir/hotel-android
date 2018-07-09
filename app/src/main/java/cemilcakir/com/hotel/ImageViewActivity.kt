package cemilcakir.com.hotel

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.view.PagerAdapter
import cemilcakir.com.hotel.Adapters.GalleryAdapter
import cemilcakir.com.hotel.Adapters.ImageSliderAdapter
import cemilcakir.com.hotel.Models.GalleryModel
import kotlinx.android.synthetic.main.activity_image_view.*
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL

class ImageViewActivity : AppCompatActivity() {

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

        var adapter : PagerAdapter = ImageSliderAdapter(this,images)
        viewPagerImage.adapter=adapter


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_view)

        Handler().postDelayed({
            AsyncTaskHandleJSon().execute("http://206.189.239.139:8000/images")
        }, 100)
    }
}
