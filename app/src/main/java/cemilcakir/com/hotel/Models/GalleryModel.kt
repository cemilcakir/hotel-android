package cemilcakir.com.hotel.Models

import com.squareup.moshi.Json

data class GalleryModel(
        val id:Int,
        val description:String,
        val url:String,
        val hotel_id:Int

)
