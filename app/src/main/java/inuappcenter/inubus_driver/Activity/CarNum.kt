package inuappcenter.inubus_driver.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import inuappcenter.inubus_driver.R
import kotlinx.android.synthetic.main.activity_car_num.*

class CarNum : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_num)

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
    }
}
