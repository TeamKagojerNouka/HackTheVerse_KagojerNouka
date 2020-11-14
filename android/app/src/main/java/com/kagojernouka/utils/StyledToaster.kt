package com.kagojernouka.utils

import android.content.Context
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.ashiqur.androidbaselib.base.BaseApplication
import com.kagojernouka.R
import com.muddzdev.styleabletoast.StyleableToast
import timber.log.Timber
import java.lang.RuntimeException

/* Created by ashiq.buet16 **/

object StyledToaster {

    fun showSuccessToast(message: String, context: Context = BaseApplication.getApplicationContext()) {
        show(context, message, R.style.successToast)
    }

    fun showErrorToast(message: String, context: Context = BaseApplication.getApplicationContext()) {
        show(context, message, R.style.errorToast)
    }

    fun showInfoToast(message: String, context: Context = BaseApplication.getApplicationContext()) {
        show(context, message, R.style.infoToast)
    }

    fun showCustomToast(message: String, drawableRes: Int, context: Context = BaseApplication.getApplicationContext()) {
        StyleableToast.Builder(context)
            .text(message)
            .textColor(ContextCompat.getColor(context, R.color.black))
            .backgroundColor(ContextCompat.getColor(context, R.color.white))
            .iconStart(drawableRes)
            .show()
    }

    private fun show(context: Context, message: String, styleRes: Int) {
        try {
            StyleableToast.makeText(context, message, Toast.LENGTH_LONG, styleRes).show();
        } catch (e: RuntimeException) {
            Timber.e("Could not show toast because app is likely on the background !")
        }
    }

    private fun show(context: Context, stringRes: Int, styleRes: Int) {
        StyleableToast.makeText(context, context.getString(stringRes), Toast.LENGTH_LONG, styleRes).show()
    }
}