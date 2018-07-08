package cemilcakir.com.hotel

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import cemilcakir.com.hotel.Models.RoomModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_room_details.*
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL

class RoomDetailsActivity : AppCompatActivity() {

    companion object {
        lateinit var roomModel: RoomModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_details)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val roomImage = "http://206.189.239.139:8000/rooms/"+ roomModel.id.toString()+"/images"

        Handler().postDelayed({
            AsyncTaskHandleJSonRoomImage().execute(roomImage)
        },100)

                txtRoomCapacity.text = roomModel.capacity.toString() + " Kişilik"
        txtRoomDetail.text = roomModel.roomDetail
        txtRoomFloor.text = roomModel.floor.toString() + ". Kat"
        txtRoomPrice.text = roomModel.price.toString() +" TL"
        txtRoomSize.text = roomModel.capacity.toString() + " m²"
    }

    inner class AsyncTaskHandleJSonRoomImage: AsyncTask<String, String, String>(){
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
            handleJsonRoomImage(result)
        }
    }

    fun handleJsonRoomImage(JsonString:String?){
        val jsonArr = JSONArray(JsonString)
        val jsonObj = jsonArr.getJSONObject(0)
        Glide.with(imgRoomDet).load("http://206.189.239.139:8000/"+jsonObj.getString("url")).into(imgRoomDet)

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
