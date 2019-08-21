package inuappcenter.inubus_driver.Custom

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.Window
import android.widget.Button
import butterknife.BindView
import butterknife.ButterKnife
import inuappcenter.inubus_driver.R
import kotlinx.android.synthetic.main.dialog_two_button.*

//import inuappcenter.inubus_driver.Custom.CustomDialogTwoButton.OnOkButtonClickListener as OnOkButtonClickListener

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class CustomDialogTwoButton(context : Context?, contents : String?) : Dialog(context) {
    private val mTitle: String = contents.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(R.layout.dialog_two_button)
        ButterKnife.bind(this)

        setDialogText(mTitle)
        btn_dialog_cancel.setOnClickListener { dismiss() }
    }

    fun setDialogText(text: String?) {
        tv_dialog_contents?.text = text
    }
}