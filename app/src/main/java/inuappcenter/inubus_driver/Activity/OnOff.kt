package inuappcenter.inubus_driver.Activity

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.JsonObject
import inuappcenter.inubus_driver.Custom.CustomDialogOneButton
import inuappcenter.inubus_driver.Custom.CustomDialogTwoButton
import inuappcenter.inubus_driver.GPS.FusedLocationProvider
import inuappcenter.inubus_driver.R
import inuappcenter.inubus_driver.Util.Config
import inuappcenter.inubus_driver.Util.RetrofitService
import kotlinx.android.synthetic.main.activity_on_off.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class OnOff : AppCompatActivity() {

    var gps : String? = "nothing"
    lateinit var retrofit : Retrofit
    private lateinit var route : String

    private lateinit var mDelayHandler : Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_off)

        setTitleTV("운행을 시작하시겠습니까?",false)

        mDelayHandler = Handler(Looper.getMainLooper())

        route = intent.getStringExtra("route")
        tv_route_off.text = route
        tv_route_on.text = route

         retrofit = Retrofit.Builder()
            .baseUrl(Config.serverUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var fusedLocationProvider = FusedLocationProvider(applicationContext)

        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            val dialogGPS = CustomDialogOneButton(this)
            dialogGPS.setCancelable(false)
            dialogGPS.show()
            dialogGPS.setOnOkButtonClickListener(object : CustomDialogOneButton.OnOkButtonClickListener{
                override fun onClick() {
                    startActivityForResult(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS),11)
                }
            })
        }

        btn_off.setOnClickListener {
            val dialog = CustomDialogTwoButton(this,
                "운행모드를 시작하시겠습니까?\n"+"\n" +
                        "확인 버튼을 누르면 \n버스 위치추적이 시작됩니다.")
            dialog.show()
            dialog.setOnOkButtonClickListener(object : CustomDialogTwoButton.OnOkButtonClickListener {
                override fun onClick() {
                    setLayout()
                    gpsOnOff(true,fusedLocationProvider)
                }
            })
        }

        btn_on.setOnClickListener {
            val dialogOff = CustomDialogTwoButton(this,
                "셔틀버스 운행 중입니다.\n운행모드를 해제하시겠습니까?\n\n확인 버튼을 누르면 \n운행이 종료됩니다.")
            dialogOff.show()
            dialogOff.setOnOkButtonClickListener(object : CustomDialogTwoButton.OnOkButtonClickListener{
                override fun onClick() {
                    gpsOnOff(false,fusedLocationProvider)
                    sendData(0.toDouble(),0.toDouble(),0)
                    finish()
                }
            })
        }
        iv_back.setOnClickListener {
            super.onBackPressed()
        }
    }

    fun setLayout(){
        content_on_off.setBackgroundResource(R.color.bright_blue)

        if (Build.VERSION.SDK_INT >= 21) {
            // 21 버전 이상일 때
            this.window?.statusBarColor = Color.parseColor("#0061f4")
        }

        tv_title.setTextColor(Color.WHITE)
        setTitleTV("운행을 종료하시겠습니까?",true)

        tv_off_sub.visibility = View.INVISIBLE
        tv_on_sub.visibility = View.VISIBLE

        tv_route_off.visibility = View.INVISIBLE
        tv_route_on.visibility = View.VISIBLE

        tv_off.visibility = View.INVISIBLE
        tv_on.visibility = View.VISIBLE

        btn_off.visibility = View.INVISIBLE
        btn_on.visibility = View.VISIBLE

        iv_back.visibility = View.INVISIBLE
    }

    override fun onBackPressed() {}

    fun gpsOnOff(start : Boolean, fusedLocationProvider: FusedLocationProvider ){
        if (start)
        {
            fusedLocationProvider.startLocationUpdates()
            gps = fusedLocationProvider.locationData
            setDelayHandler(fusedLocationProvider)
            Log.d("on activity gps",gps)
        }else{
            fusedLocationProvider.stopLocationUpdates()
            Log.d("on activity gps","stop")
        }
    }

    // handler
    private fun setDelayHandler(fusedLocationProvider: FusedLocationProvider){
        mDelayHandler.postDelayed({
            getGps(fusedLocationProvider)
        },2*1000)
    }

    fun getGps(fusedLocationProvider: FusedLocationProvider) {
        var gpsData : Location? = fusedLocationProvider.mLastLocation
        Log.w("timer",gpsData.toString())

        if (gpsData != null){
            sendData(gpsData.latitude, gpsData.longitude,1)
        }
        setDelayHandler(fusedLocationProvider)
    }

    private fun sendData(lat : Double, lng : Double, status : Int){

        var service : RetrofitService = retrofit.create(RetrofitService::class.java)

        service.sendGPS(route.substring(0,2),status,lat,lng).enqueue(object : Callback<JsonObject>{

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Toast.makeText(applicationContext,"통신 에러! 서버 연결을 확인해주세요",Toast.LENGTH_SHORT).show()
                Log.w("retrofit test failure", "status : $status,$lat,$lng,${route.substring(0,2)}")
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.code() == 200){
                    Log.w("retrofit test", "status$status$lat$lng$route")
                }
            }

        })
    }

    private fun setTitleTV(title : String, on : Boolean){
        val builder = SpannableStringBuilder(title)
        if (on){
            tv_title.text = ""
            builder.setSpan(ForegroundColorSpan(Color.parseColor("#ffe624")), 0, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        builder.setSpan(StyleSpan(Typeface.BOLD),0,6,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        tv_title.append(builder)
    }

    override fun onDestroy() {
        mDelayHandler.removeMessages(0)
        super.onDestroy()
    }
}