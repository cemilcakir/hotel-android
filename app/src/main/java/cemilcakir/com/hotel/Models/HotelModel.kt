package cemilcakir.com.hotel.Models

import com.squareup.moshi.Json

data class HotelModel(
        @Json(name = "id") val id:Int,
        @Json(name = "name") val hotelName:String,
        @Json(name = "link") val hotelLink:String,
        @Json(name = "star") val hotelStar:Int,
        @Json(name = "phone") val hotelPhone:String,
        @Json(name = "mail") val hotelMail:String,
        @Json(name = "county") val hotelCounty:String,
        @Json(name = "province") val hotelProvince:String,
        @Json(name = "address") val hotelAddress:String,
        @Json(name = "detail") val hotelDetail:String

)
