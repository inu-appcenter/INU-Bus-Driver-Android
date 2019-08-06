//package inuappcenter.inubus_driver.Custom
//
//import android.support.v7.widget.RecyclerView
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import inuappcenter.inubus_driver.R
//import kotlinx.android.synthetic.main.item_recycler_select_route.view.*
//
//class RecyclerSelectRouteAdapter (routeList : ArrayList<routeVO>) :RecyclerView.Adapter<RecyclerSelectRouteAdapter.routeViewHolder>() {
//
//    private var routeList : ArrayList<routeVO> = routeList
//    var itemClick: ItemClick? = null
//
//    override fun onBindViewHolder(holder: routeViewHolder, position: Int) {
//        routeList[position].let { routeVO ->
//            with(holder){
//                tvTitle.text = routeVO.title
//            }
//        }
//
//        holder.itemView.setOnClickListener { v ->
//            itemClick?.onClick(v, position)
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return routeList.size
//    }
//
//    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerSelectRouteAdapter.routeViewHolder {
//        var view = LayoutInflater.from(p0.context).inflate(R.layout.item_recycler_select_route,p0,false)
//        return routeViewHolder(view)
//    }
//
//    inner class routeViewHolder(view: View) : RecyclerView.ViewHolder(view){
//        var tvTitle = view.tv_item_route_title
//    }
//
//
//    interface ItemClick {
//        fun onClick(view: View, position: Int)
//    }
//
//    internal fun setItemClick(itemClick:ItemClick) {
//        this.itemClick = itemClick
//    }
//}
