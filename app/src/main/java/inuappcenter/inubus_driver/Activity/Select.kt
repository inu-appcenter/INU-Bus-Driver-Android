package inuappcenter.inubus_driver.Activity

import android.Manifest
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.util.Log
import android.widget.AdapterView
import android.widget.Toast
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import inuappcenter.inubus_driver.Custom.ListViewSelectRouteAdapter
import inuappcenter.inubus_driver.Custom.routeVO
import inuappcenter.inubus_driver.R
import kotlinx.android.synthetic.main.activity_select.*

class Select : androidx.appcompat.app.AppCompatActivity() {
    var routeList:ArrayList<routeVO> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select)

        setTitleTV("어떤 노선을 이용하시겠어요?")

        makePermission()
        addList()
        val rAdapter = ListViewSelectRouteAdapter(this,routeList)

        list_view_select.adapter = rAdapter
        list_view_select.onItemClickListener = AdapterView.OnItemClickListener{ parent, v, position, id ->
            // get item
            Log.d("selected item",routeList.get(position).title)
            val intentOnOff = Intent(applicationContext, OnOff::class.java)
            intentOnOff.putExtra("route", routeList.get(position).title)
            startActivity(intentOnOff)
            overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left)
        }

    }

    private fun makePermission() {
        val permissionListener = object : PermissionListener {
            override fun onPermissionGranted() {}

            override fun onPermissionDenied(deniedPermissions: List<String>) {
                Toast.makeText(applicationContext, "다음 권한이 거부되었습니다\n$deniedPermissions", Toast.LENGTH_SHORT).show()
            }
        }
        TedPermission.with(this)
            .setPermissionListener(permissionListener)
            .setRationaleTitle("권한 허용")
            .setRationaleMessage("실시간 위치정보 사용을 위해 권한을 허용해주세요")
            .setDeniedMessage("허용하지 않을 시 기능에 제약이 생깁니다 \n [설정]>[권한]에서 허용할 수 있습니다")
            .setPermissions(Manifest.permission.ACCESS_FINE_LOCATION)
            .check()
    }

    //dummy data - 서버에서 받아오는걸로 변경
    fun addList(){
        routeList.add(routeVO("송내"))
        routeList.add(routeVO("수원 - 안산 - 시흥"))
        routeList.add(routeVO("일산 - 김포"))
        routeList.add(routeVO("청라"))
        routeList.add(routeVO("광명"))
    }

    private fun setTitleTV(title : String){
        val builder = SpannableStringBuilder(title)
        builder.setSpan(StyleSpan(Typeface.BOLD),0,5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        tv_select_question.append(builder)
    }

}
