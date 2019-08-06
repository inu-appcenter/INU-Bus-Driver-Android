package inuappcenter.inubus_driver.Activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.AdapterView
import inuappcenter.inubus_driver.Custom.ListViewSelectRouteAdapter
import inuappcenter.inubus_driver.Custom.routeVO
import inuappcenter.inubus_driver.R
import kotlinx.android.synthetic.main.activity_select.*

class Select : AppCompatActivity() {
    var routeList:ArrayList<routeVO> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select)

        addList()
//        val rAdapter = RecyclerSelectRouteAdapter(routeList)
        val rAdapter = ListViewSelectRouteAdapter(this,routeList)

         /* item click listener
            클릭한 값 전달*/
//        rAdapter.setItemClick(object : RecyclerSelectRouteAdapter.ItemClick {
//            override fun onClick(view: View, position: Int) {
//                val intentOnOff = Intent(applicationContext, OnOff::class.java)
//                intentOnOff.putExtra("route", tv_item_route_title.text)
//                startActivity(intentOnOff)
//            }
//        })
        list_view_select.adapter = rAdapter
        list_view_select.onItemClickListener = AdapterView.OnItemClickListener{ parent, v, position, id ->
            // get item
            Log.d("selected item",routeList.get(position).title)
            val intentOnOff = Intent(applicationContext, OnOff::class.java)
            intentOnOff.putExtra("route", routeList.get(position).title)
            startActivity(intentOnOff)
        }

    }

    //dummy data - 서버에서 받아오는걸로 변경
    fun addList(){
        routeList.add(routeVO("송내"))
        routeList.add(routeVO("수원 - 안산 - 시흥"))
        routeList.add(routeVO("일산 - 김포"))
        routeList.add(routeVO("청라"))
        routeList.add(routeVO("광명"))
    }
}
