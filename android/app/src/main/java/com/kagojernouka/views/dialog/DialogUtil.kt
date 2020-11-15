package com.kagojernouka.views.dialog

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.kagojernouka.R
import com.kagojernouka.databinding.LayoutLoaderBinding


/* Created by ashiq.buet16 **/

object DialogUtil {
    private var mLoader: AlertDialog? = null

    fun showLoader(context: Context, cancelable: Boolean = false): AlertDialog? {

        val binding = DataBindingUtil.inflate<LayoutLoaderBinding>(
            LayoutInflater.from(context), R.layout.layout_loader, null, false
        )

        hideLoader()
        mLoader = AlertDialog.Builder(context, R.style.TransparentDialog)
            .setView(binding.root)
            .setCancelable(cancelable)
            .create()

        mLoader?.show()

        return mLoader
    }

    fun hideLoader() {
        try {
            mLoader?.dismiss()
        }catch (e: Exception){

        }
    }

    fun showAlertDialog(
        activity: Activity,
        title: String?,
        msg: String?,
        taskSuccess: DialogInterface.OnClickListener? = null,
        taskCancelled: DialogInterface.OnClickListener? = null,
        btnPosText: String? = null,
        btnNegText: String? = null,
        btnBgColor: Int = R.color.colorSecondary
    ) {
        val alertDialog = AlertDialog.Builder(activity).create()
        alertDialog.setTitle(title)
        alertDialog.setMessage(msg)
        if (btnPosText != null) {
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, btnPosText, taskSuccess)
            alertDialog.setOnShowListener {
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
                    .setBackgroundColor(activity.resources.getColor(btnBgColor))
            }
        }
        if (btnNegText != null) {
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, btnNegText, taskCancelled)
        }
        alertDialog.show()
    }


    fun createCustomDialog(context: Context, view: View, cancelable: Boolean = true): AlertDialog {
        return MaterialAlertDialogBuilder(context)
            .setView(view)
            .setCancelable(cancelable)
            .create()
    }

}