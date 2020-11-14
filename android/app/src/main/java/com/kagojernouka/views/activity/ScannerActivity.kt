package com.kagojernouka.views.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ashiqur.androidbaselib.util.helper.Toaster
import com.google.zxing.Result
import com.kagojernouka.data.Const
import me.dm7.barcodescanner.zxing.ZXingScannerView
import timber.log.Timber


/*
 * Created by : 
 * <a href="https://www.github.com/ashiqursuperfly">Ashiqur Rahman</a> on 11/14/20.
*/


class ScannerActivity : AppCompatActivity(), ZXingScannerView.ResultHandler {

    private var mScannerView: ZXingScannerView? = null

    override fun onCreate(state: Bundle?) {
        super.onCreate(state)
        mScannerView = ZXingScannerView(baseContext)
        setContentView(mScannerView)
    }

    override fun onResume() {
        super.onResume()
        if(mScannerView == null){
            Toaster.showToast("Error Loading Scanner")
        }
        Timber.d("ScannerViewZxxing : Starting camera")
        mScannerView?.setResultHandler(this)
        mScannerView?.startCamera()
    }

    override fun onPause() {
        super.onPause()
        Timber.d("ScannerViewZxxing : Stopping camera")
        mScannerView?.stopCamera()
    }

    override fun handleResult(rawResult: Result) { // Do something with the result here
        val returnIntent = Intent()
        returnIntent.putExtra(Const.RequestResult.SCAN_RESULT, rawResult.text)
        setResult(Activity.RESULT_OK, returnIntent)
        onBackPressed()
        Timber.d("ScannerViewZxxing : Handling Result")
    }
}
