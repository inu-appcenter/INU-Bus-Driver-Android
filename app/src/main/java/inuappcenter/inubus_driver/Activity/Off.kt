package inuappcenter.inubus_driver.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import inuappcenter.inubus_driver.R
import kotlinx.android.synthetic.main.fragment_off.*



class Off : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_off)

        val route = intent.getStringExtra("route")

        tv_route_off.text = route
        btn_off.setOnClickListener{
            val intentOn = Intent(applicationContext, On::class.java)
            intentOn.putExtra("route", route)
            startActivity(intentOn)
            overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left)
            finishAffinity()
            finish()
        }

        iv_back.setOnClickListener{
            super.onBackPressed()
            overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right)
    }
}
