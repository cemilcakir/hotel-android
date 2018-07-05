package cemilcakir.com.hotel.Models

import com.squareup.moshi.Json

data class GalleryModel(
        @Json(name = "id") val id:Int,
        @Json(name = "description") val description:String,
        @Json(name = "url") val url:String,
        @Json(name = "hotel_id") val hotel_id:Int

)
