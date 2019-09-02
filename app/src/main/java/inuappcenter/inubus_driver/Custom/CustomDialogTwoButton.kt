package inuappcenter.inubus_driver.Custom

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import inuappcenter.inubus_driver.R
import kotlinx.android.synthetic.main.dialog_two_button.*

//import inuappcenter.inubus_driver.Custom.CustomDialogTwoButton.OnOkButtonClickListener as OnOkButtonClickListener

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class CustomDialogTwoButton(context : Context?, contents : String?) : Dialog(context) {
    private val mTitle: String = contents.toString()
    private var listener : OnOkButtonClickListener? = null

    interface OnOkButtonClickListener {
        fun onClick()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(R.layout.dialog_two_button)

        setDialogText(mTitle)
        btn_dialog_cancel.setOnClickListener { dismiss() }
        btn_dialog_ok.setOnClickListener {
            setOnOkButtonClickListener(listener)
            if (listener != null) {
                listener!!.onClick()
            }
            dismiss()}
    }

    private fun setDialogText(text: String?) {
        tv_dialog_contents?.text = text
    }

    fun setOnOkButtonClickListener(listener: OnOkButtonClickListener?){
        this.listener = listener
    }
}