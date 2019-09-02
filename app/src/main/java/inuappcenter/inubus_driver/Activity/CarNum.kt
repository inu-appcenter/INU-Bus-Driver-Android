package inuappcenter.inubus_driver.Activity

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import inuappcenter.inubus_driver.Custom.CustomDialogOneButton
import inuappcenter.inubus_driver.R
import kotlinx.android.synthetic.main.activity_car_num.*

class CarNum : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_num)

        setTitleTV("차량번호를 입력해주세요")

        btn_car_next.setOnClickListener {
//            if(et_car_num.text == ""){
                val intentCarNum = Intent(this,Select::class.java)
                this.startActivity(intentCarNum)
                this.finish()
//            }
//            else{
//            Toast.makeText(this, "이 어플은 등록된 차량만 이용할 수 있습니다",Toast.LENGTH_SHORT).show()
//        }
        }
        tv_car_num_next.setOnClickListener {
            val dialogNumPass = CustomDialogOneButton(this,"차량번호 입력을 건너뛰시면\n" +
                    "운행 서비스 기능을\n" +
                    "이용할 수 없습니다.")
            dialogNumPass.setCancelable(false)
            dialogNumPass.show()
        }
    }

    private fun setTitleTV(title : String){
        val builder = SpannableStringBuilder(title)
        builder.setSpan(StyleSpan(Typeface.BOLD),0,4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        tv_car_num.append(builder)
    }
}
