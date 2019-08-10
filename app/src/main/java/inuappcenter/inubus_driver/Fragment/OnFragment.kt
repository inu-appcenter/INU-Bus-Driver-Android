//package inuappcenter.inubus_driver.Fragment
//
//import android.Manifest
//import android.content.Context
//import android.content.pm.PackageManager
//import android.graphics.Color
//import android.location.Location
//import android.location.LocationManager
//import android.os.Build
//import android.os.Bundle
//import android.util.Log
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Toast
//import androidx.core.app.ActivityCompat
//import androidx.core.content.ContextCompat.getSystemService
//import com.google.android.gms.common.ConnectionResult
//import com.google.android.gms.common.api.GoogleApiClient
//import com.google.android.gms.location.FusedLocationProviderClient
//import com.google.android.gms.location.LocationRequest
//import com.google.android.gms.location.LocationServices
//import com.google.android.gms.tasks.OnSuccessListener
//import inuappcenter.inubus_driver.Activity.OnOff
//import inuappcenter.inubus_driver.R
//import kotlinx.android.synthetic.main.fragment_on.view.*
//
//
//class OnFragment : Fragment() ,View.OnClickListener,GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener{
//
//    private val TAG = "GET Location Test"
//    private lateinit var mGoogleApiClient: GoogleApiClient
//    private var mLocationManager: LocationManager? = null
//    lateinit var mLocation: Location
//    private var mLocationRequest: LocationRequest? = null
//    private val listener: com.google.android.gms.location.LocationListener? = null
//    private val UPDATE_INTERVAL = (2 * 1000).toLong()  /* 10 secs */
//    private val FASTEST_INTERVAL: Long = 2000 /* 2 sec */
//
//    lateinit var locationManager: LocationManager
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        if (Build.VERSION.SDK_INT >= 21) {
//            // 21 버전 이상일 때
//            activity?.window?.statusBarColor = Color.parseColor("#0061f4")
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
////                activity?.window?.decorView?.systemUiVisibility = R.style.statusBarBlue
//            }
//        }
//        mGoogleApiClient = GoogleApiClient.Builder(requireContext())
//            .addConnectionCallbacks(this)
//            .addOnConnectionFailedListener(this)
//            .addApi(LocationServices.API)
//            .build()
//
//        mLocationManager = context?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
//
//    }
//
//    override fun onPause() {
//        super.onPause()
//        if (mGoogleApiClient != null) {
//            mGoogleApiClient.connect()
//        }
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val view = inflater.inflate(R.layout.fragment_on, container, false)
//
//        var routeData = arguments?.get("route").toString()
////        val bundleRoute = Bundle()
////        bundleRoute.putString("route",routeData)
////        onFragment.arguments = bundleRoute
//
//        view.tv_route.text = routeData
//        view.btn_on.setOnClickListener(this)
//
//        return view
//    }
//
//    override fun onClick(v: View?) {
////        val onFragment = OnFragment()
//
//        OnOff().driveStatus(false)
//
//
//        activity?.supportFragmentManager
//            ?.beginTransaction()
//            ?.setCustomAnimations(R.anim.enter_from_left,R.anim.exit_to_right)
//            ?.replace(R.id.content_on_off, OffFragment())?.commit()
//    }
//
//
//    // Fused Location Provider
//
//    private fun checkLocation(): Boolean {
//        if(!isLocationEnabled()){
//            Toast.makeText(context,"위치정보 사용이 거부되어있습니다", Toast.LENGTH_LONG).show()
//        }
//        return isLocationEnabled()
//    }
//
//
//    override fun onLocationChanged(location: Location?) {
//        if (location != null) {
//            var msg = "Updated Location: Latitude-" + location.latitude.toString() + "Longitude-"+location.longitude
//            Log.d("get location changed",msg)
//        }
//    }
//
//    override fun onConnectionFailed(connectionResult: ConnectionResult) {
//        Log.i(TAG, "Connection failed. Error: " + connectionResult.errorCode)
//    }
//
//    override fun onConnectionSuspended(p0: Int) {
//        Log.d(TAG, "Connection Suspended")
//        mGoogleApiClient.connect()
//    }
//
//    override fun onConnected(p0: Bundle?) {
//        if (ActivityCompat.checkSelfPermission(requireContext()
//                , Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
//            && ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//
//            return}
//        startLocationUpdates()
//
//        var fusedLocationProviderClient :
//                FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(OnOff())
//        fusedLocationProviderClient .lastLocation
//            .addOnSuccessListener(OnOff(), OnSuccessListener<Location> { location ->
//                // Got last known location. In some rare situations this can be null.
//                if (location != null) {
//                    // Logic to handle location object
//                    mLocation = location
//                    var msg = "Updated Location: Latitude " + location.longitude.toString() + location.longitude
//                    Log.d("get location",msg)
//                }
//            })
//    }
//    private fun isLocationEnabled(): Boolean {
//        locationManager = context?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
//        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
//            LocationManager.NETWORK_PROVIDER)
//    }
//
//    private fun startLocationUpdates() {
//        // Create the location request
//        mLocationRequest = LocationRequest.create()
//            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
//            .setInterval(UPDATE_INTERVAL)
//            .setFastestInterval(FASTEST_INTERVAL)
//        // Request location updates
//        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
//            && ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            return
//        }
//        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,
//            mLocationRequest, this)
//    }
//
//}
