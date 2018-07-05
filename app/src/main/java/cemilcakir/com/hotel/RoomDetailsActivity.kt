package cemilcakir.com.hotel

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class RoomDetailsActivity : AppCompatActivity() {

    companion object {
        lateinit var roomId:String
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_details)
    }
}
