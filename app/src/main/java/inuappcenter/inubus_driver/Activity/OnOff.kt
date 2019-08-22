package inuappcenter.inubus_driver.Activity

import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import inuappcenter.inubus_driver.Custom.CustomDialogTwoButton
import inuappcenter.inubus_driver.GPS.FusedLocationProvider
import inuappcenter.inubus_driver.R
import kotlinx.android.synthetic.main.activity_on_off.*

class OnOff : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_off)

        setTitleTV("운행을 시작하시겠습니까?",false)

        val route = intent.getStringExtra("route")
        tv_route_off.text = route
        tv_route_on.text = route

        btn_off.setOnClickListener {
            val dialog = CustomDialogTwoButton(this,
                "운행모드를 시작하시겠습니까?\n"+"\n" +
                        "확인 버튼을 누르면 \n버스 위치추적이 시작됩니다.")
            dialog.show()
            dialog.setOnOkButtonClickListener(object : CustomDialogTwoButton.OnOkButtonClickListener {
                override fun onClick() {
                    setLayout()
                    FusedLocationProvider(applicationContext).startLocationUpdates()
                }
            })
        }
        btn_on.setOnClickListener {
            val dialogOff = CustomDialogTwoButton(this,
                "셔틀버스 운행 중입니다.\n운행모드를 해제하시겠습니까?\n\n확인 버튼을 누르면 \n운행이 종료됩니다.")
            dialogOff.show()
            dialogOff.setOnOkButtonClickListener(object : CustomDialogTwoButton.OnOkButtonClickListener{
                override fun onClick() {
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

    override fun onBackPressed() {
    }

    fun getGps(start :Boolean){

    }
    fun setTitleTV(title : String, on : Boolean){
        val builder = SpannableStringBuilder(title)
        if (on){
            tv_title.text = ""
            builder.setSpan(ForegroundColorSpan(Color.parseColor("#ffe624")), 0, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        builder.setSpan(StyleSpan(Typeface.BOLD),0,6,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        tv_title.append(builder)
    }
}