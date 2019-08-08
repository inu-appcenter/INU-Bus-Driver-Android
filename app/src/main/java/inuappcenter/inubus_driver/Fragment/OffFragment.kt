package inuappcenter.inubus_driver.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import inuappcenter.inubus_driver.Activity.OnOff
import inuappcenter.inubus_driver.R
import kotlinx.android.synthetic.main.fragment_off.*
import kotlinx.android.synthetic.main.fragment_off.view.*
import kotlinx.android.synthetic.main.fragment_off.view.btn_off

class OffFragment : Fragment() ,View.OnClickListener{
    private val fragment = OnFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_off, container, false)
        val route = arguments?.get("route").toString()

        addBundle(route)

        view.tv_route_off.text = route
        view.btn_off.setOnClickListener(this)
        view.iv_back.setOnClickListener(this)

        return view
    }

    override fun onClick(p0: View?) {
//        when (p0){
//           btn_off -> {
               OnOff().driveStatus(true)

               activity?.supportFragmentManager
                   ?.beginTransaction()
                   ?.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left)
                   ?.replace(R.id.content_on_off, fragment)?.commit()
           }
//            iv_back -> {
//                OnOff().finishOnOff()
//            }
//        }
//    }
    private fun addBundle(data : String){
        val bundle :Bundle = Bundle()
        bundle.putString("route", data)
        fragment.arguments = bundle
    }
}
