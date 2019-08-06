package inuappcenter.inubus_driver.Util

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Retrofit {

    @FormUrlEncoded
    @POST("gps")
    abstract fun sendGPS(
        @Field("status") status: String,
        @Field("routeId") routeId: Int,
        @Field("lat") lat: String,
        @Field("lng") lng: String): Call<JsonObject>
}