package inuappcenter.inubus_driver.Custom

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import inuappcenter.inubus_driver.R

class ListViewSelectRouteAdapter(var context: Context, private val routeList:ArrayList<routeVO>) : BaseAdapter() {
    override fun getCount(): Int {
        return routeList.size
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getItem(position: Int): Any {
        return routeList[position]
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_recycler_select_route,null)

        val title = view.findViewById<TextView>(R.id.tv_item_route_title)
        title.text = routeList[position].title

        return view
    }

}