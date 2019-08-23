package inuappcenter.inubus_driver.Custom

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import inuappcenter.inubus_driver.R
import kotlinx.android.synthetic.main.dialog_one_button.*

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class CustomDialogOneButton(context : Context?) : Dialog(context) {

    private var listener : OnOkButtonClickListener? = null

    interface OnOkButtonClickListener {
        fun onClick()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(R.layout.dialog_one_button)

        btn_one_dialog_ok.setOnClickListener {
            setOnOkButtonClickListener(listener)
            if (listener != null) {
                listener!!.onClick()
            }
            dismiss()}
    }

    fun setOnOkButtonClickListener(listener: OnOkButtonClickListener?){
        this.listener = listener
    }
}