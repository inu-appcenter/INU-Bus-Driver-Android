package inuappcenter.inubus_driver.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import inuappcenter.inubus_driver.R

class Splash : AppCompatActivity() {

    val SPLASH_DISPLAY_LENGTH = 1300

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        Handler().postDelayed({
                val intentSplash = Intent(this,CarNum::class.java)
                this.startActivity(intentSplash)
                this.finish()
        }, SPLASH_DISPLAY_LENGTH.toLong())
    }
}
