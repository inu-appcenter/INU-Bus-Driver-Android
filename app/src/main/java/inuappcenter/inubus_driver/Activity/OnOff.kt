//package inuappcenter.inubus_driver.Activity
//
////import android.support.v7.app.AppCompatActivity
//import android.os.Bundle
//import android.util.Log
//import androidx.appcompat.app.AppCompatActivity
//import inuappcenter.inubus_driver.Fragment.OffFragment
//import inuappcenter.inubus_driver.R
//
//class OnOff : AppCompatActivity() {
//    var start = false
//
////    private val TAG = "GET Location Test"
////    private lateinit var mGoogleApiClient: GoogleApiClient
////    private var mLocationManager: LocationManager? = null
////    lateinit var mLocation: Location
////    private var mLocationRequest: LocationRequest? = null
////    private val listener: com.google.android.gms.location.LocationListener? = null
////    private val UPDATE_INTERVAL = (2 * 1000).toLong()  /* 10 secs */
////    private val FASTEST_INTERVAL: Long = 2000 /* 2 sec */
////
////    lateinit var locationManager: LocationManager
//
//
////    override fun onStart() {
//        super.onStart()
////        if (mGoogleApiClient != null) {
////            mGoogleApiClient.connect()
////        }
//    }
////
////    override fun onPause() {
////        super.onPause()
////        if (mGoogleApiClient != null) {
////            mGoogleApiClient.connect()
////        }
////    }
//
////    override fun onStop() {
////        super.onStop()
////        if (mGoogleApiClient.isConnected()) {
////            mGoogleApiClient.disconnect();
////        }
////    }
//
////    override fun onDestroy() {
////        super.onDestroy()
////        if (mGoogleApiClient.isConnected) {
////            mGoogleApiClient.disconnect()
////        }
////    }
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_on_off)
//
//        val route = intent.getStringExtra("route")
//
//        val fragment = OffFragment()
//        val bundle :Bundle = Bundle()
//        bundle.putString("route",route)
//        fragment.arguments = bundle
//
//        supportFragmentManager.beginTransaction()
//            .add(R.id.content_on_off, fragment)
//            .commit()
//
////        mGoogleApiClient = GoogleApiClient.Builder(this)
////            .addConnectionCallbacks(this)
////            .addOnConnectionFailedListener(this)
////            .addApi(LocationServices.API)
////            .build()
////
////        mLocationManager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
//    }
//
////    private fun checkLocation(): Boolean {
////        if(!isLocationEnabled()){
////            Toast.makeText(this,"위치정보 사용이 거부되어있습니다",Toast.LENGTH_LONG).show()
////        }
////        return isLocationEnabled()
////    }
//
//    fun driveStatus(start: Boolean){
//        this.start = start
//        Log.d("status",start.toString())
//    }
//
//    override fun onBackPressed() {
//    }
//
////    fun getGps(start :Boolean){
////        if(start){
////            if (mGoogleApiClient != null) {
////                mGoogleApiClient.connect()
////            }
////            checkLocation()
////        }
////        else if (!start)
////            if (mGoogleApiClient.isConnected) {
////                mGoogleApiClient.disconnect()
////            }
////    }
//
////    // Fused Location Provider
////
////    override fun onLocationChanged(location: Location?) {
////        if (location != null) {
////            var msg = "Updated Location: Latitude-" + location.latitude.toString() + "Longitude-"+location.longitude
////            Log.d("get location changed",msg)
////        }
////    }
////
////    override fun onConnectionFailed(connectionResult: ConnectionResult) {
////        Log.i(TAG, "Connection failed. Error: " + connectionResult.errorCode)
////    }
////
////    override fun onConnectionSuspended(p0: Int) {
////        Log.d(TAG, "Connection Suspended")
////        mGoogleApiClient.connect()
////    }
////
////    override fun onConnected(p0: Bundle?) {
////        if (ActivityCompat.checkSelfPermission(this
////                , Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
////            && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
////
////            return}
////        startLocationUpdates()
////
////        var fusedLocationProviderClient :
////                FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
////        fusedLocationProviderClient .lastLocation
////            .addOnSuccessListener(this, OnSuccessListener<Location> { location ->
////                // Got last known location. In some rare situations this can be null.
////                if (location != null) {
////                    // Logic to handle location object
////                    mLocation = location
////                    var msg = "Updated Location: Latitude " + location.longitude.toString() + location.longitude
////                    Log.d("get location",msg)
////                }
////            })
////    }
////    private fun isLocationEnabled(): Boolean {
////        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
////        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
////    }
////
////    private fun startLocationUpdates() {
////        // Create the location request
////        mLocationRequest = LocationRequest.create()
////            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
////            .setInterval(UPDATE_INTERVAL)
////            .setFastestInterval(FASTEST_INTERVAL)
////        // Request location updates
////        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
////            && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
////            return
////        }
////        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,
////            mLocationRequest, this)
////    }
//
//
//}