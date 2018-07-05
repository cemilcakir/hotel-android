package cemilcakir.com.hotel.Models

import com.squareup.moshi.Json

data class RoomModel(
        @Json(name = "id") val id:Int,
        @Json(name = "type") val roomType:String,
        @Json(name = "detail") val roomDetail:String

)
