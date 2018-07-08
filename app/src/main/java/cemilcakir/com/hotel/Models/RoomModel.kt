package cemilcakir.com.hotel.Models

import com.squareup.moshi.Json

data class RoomModel(
         val id:Int,
         val roomType:String?,
         val roomDetail:String?,
         val floor:Int?,
         val size:Int?,
         val capacity:Int?,
         val price: Double?

)
