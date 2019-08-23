package inuappcenter.inubus_driver.GPS

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.core.app.ActivityCompat
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.*
import com.google.android.gms.tasks.OnSuccessListener

class FusedLocationProvider(private val mContext: Context) : GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener {

    private val TAG = "Get Location Test"

    var locationData : String = "nothing"
    private lateinit var mGoogleApiClient: GoogleApiClient
    lateinit var mLocation: Location
    private lateinit var mLastLocation : Location
    private var mLocationRequest: LocationRequest? = null
    private val UPDATE_INTERVAL:Long = 10 * 1000  /* 10 secs */
    private val FASTEST_INTERVAL: Long = 2000 /* 2 sec */
    private var fusedLocationClient= LocationServices.getFusedLocationProviderClient(mContext)

    override fun onLocationChanged(location: Location?) {
        if (location != null) {
            mLastLocation = location
            Log.d("get location","latitude :"+location.latitude+",longitude :"+location.longitude)

            locationData = location.latitude.toString()+","+location.longitude.toString()
        }
      }

    override fun onConnectionFailed(connectionResult: ConnectionResult) {
        Log.i(TAG, "Connection failed. Error: " + connectionResult.getErrorCode())
    }

    override fun onConnectionSuspended(p0: Int) {
        Log.d(TAG, "Connection Suspended")
        mGoogleApiClient.connect()
    }

    override fun onConnected(p0: Bundle?) {
        if (ActivityCompat.checkSelfPermission(mContext
                , Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return}
        startLocationUpdates()

       fusedLocationClient .getLastLocation()
            .addOnSuccessListener(Activity(), OnSuccessListener<Location> { location ->
                // Got last known location. In some rare situations this can be null.
                if (location != null) {
                    // Logic to handle location object
                    mLocation = location
                }
            })
    }

    fun startLocationUpdates() {
        // Create the location request
        mLocationRequest = LocationRequest.create()
        mLocationRequest.run {
            this!!.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            interval = UPDATE_INTERVAL
            fastestInterval = FASTEST_INTERVAL
        }

        val builder = LocationSettingsRequest.Builder()
        builder.addLocationRequest(mLocationRequest!!)

        val locationSettingsRequest = builder.build()

        val settingsClient = LocationServices.getSettingsClient(mContext)
        settingsClient.checkLocationSettings(locationSettingsRequest)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(mContext)
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return
        }

        fusedLocationClient.requestLocationUpdates(mLocationRequest,mLocationCallback, Looper.myLooper())
    }

    fun stoplocationUpdates() {
        fusedLocationClient.removeLocationUpdates(mLocationCallback)
    }


    private val mLocationCallback = object : LocationCallback(){
        override fun onLocationResult(locationResult: LocationResult?) {
            locationResult?.lastLocation
            onLocationChanged(locationResult?.lastLocation)
        }
    }
}