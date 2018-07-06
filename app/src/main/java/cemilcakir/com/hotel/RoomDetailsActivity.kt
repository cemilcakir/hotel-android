package cemilcakir.com.hotel

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import cemilcakir.com.hotel.Models.RoomModel
import kotlinx.android.synthetic.main.activity_room_details.*

class RoomDetailsActivity : AppCompatActivity() {

    companion object {
        lateinit var roomModel: RoomModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_details)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        txtRoomCapacity.text = roomModel.capacity.toString() + " Kişilik"
        txtRoomDetail.text = roomModel.roomDetail
        txtRoomFloor.text = roomModel.floor.toString() + ". Kat"
        txtRoomPrice.text = roomModel.price.toString() +" TL"
        txtRoomSize.text = roomModel.capacity.toString() + " m²"
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
