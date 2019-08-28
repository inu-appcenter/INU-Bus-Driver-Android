package inuappcenter.inubus_driver.Util

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RetrofitService {

    @FormUrlEncoded
    @POST("gps")
    abstract fun sendGPS(
        @Field("routeId") routeId: String,
        @Field("status") status: Int,
        @Field("lat") lat: Double,
        @Field("lng") lng: Double): Call<JsonObject>
}