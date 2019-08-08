package inuappcenter.inubus_driver.Fragment

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import inuappcenter.inubus_driver.Activity.OnOff
import inuappcenter.inubus_driver.R
import kotlinx.android.synthetic.main.fragment_on.view.*


class OnFragment : Fragment() ,View.OnClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= 21) {
            // 21 버전 이상일 때
            activity?.window?.statusBarColor = Color.parseColor("#0061f4")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                activity?.window?.decorView?.systemUiVisibility = R.style.statusBarBlue
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_on, container, false)

        var routeData = arguments?.get("route").toString()

//        val bundleRoute = Bundle()
//        bundleRoute.putString("route",routeData)
//        onFragment.arguments = bundleRoute

        view.tv_route.text = routeData
        view.btn_on.setOnClickListener(this)

        return view
    }

    override fun onClick(v: View?) {
//        val onFragment = OnFragment()

        OnOff().driveStatus(false)


        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.setCustomAnimations(R.anim.enter_from_left,R.anim.exit_to_right)
            ?.replace(R.id.content_on_off, OffFragment())?.commit()
    }
}
