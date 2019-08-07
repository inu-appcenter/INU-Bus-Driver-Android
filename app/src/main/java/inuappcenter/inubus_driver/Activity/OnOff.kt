package inuappcenter.inubus_driver.Activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import inuappcenter.inubus_driver.Fragment.OffFragment
import inuappcenter.inubus_driver.R
import kotlinx.android.synthetic.main.activity_on_off.*

class OnOff : AppCompatActivity() {
    var start = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_off)

        val route = getIntent().getStringExtra("route")

        val fragment = OffFragment()
        val bundle :Bundle = Bundle()
        bundle.putString("route",route)
        fragment.arguments = bundle

        supportFragmentManager.beginTransaction()
            .add(R.id.content_on_off, fragment)
            .commit()
    }
    fun driveStatus(start: Boolean){
        this.start = start
        Log.d("status",start.toString())
    }

    override fun onBackPressed() {
    }

    fun finishOnOff(){
//        finish()
        finishAffinity()
    }
    fun getGps(start :Boolean){
        if(start){

        }
    }
}