package com.kagojernouka.views.fragments

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ashiqur.androidbaselib.base.BaseFragment
import com.ashiqur.androidbaselib.util.helper.PermissionsUtil
import com.ashiqur.androidbaselib.util.helper.Toaster
import com.kagojernouka.R
import com.kagojernouka.data.Const
import com.kagojernouka.utils.StyledToaster
import com.kagojernouka.views.activity.ScannerActivity
import com.kagojernouka.views.dialog.DialogUtil
import com.kagojernouka.views.dialog.RateNowDialog
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.android.synthetic.main.layout_fragment_scan.*
import kotlinx.android.synthetic.main.layout_fragment_scan.btn_scan

/*
 * Created by : 
 * <a href="https://www.github.com/ashiqursuperfly">Ashiqur Rahman</a> on 11/14/20.
*/

class ScanFragment: BaseFragment() {

    private val args: ScanFragmentArgs by navArgs()

    private lateinit var mSharedViewModel: SharedViewModel

    override fun afterOnViewCreated() {
        mSharedViewModel = requireActivity().let { ViewModelProviders.of(it).get(SharedViewModel::class.java) }

        setStatusBarColor(requireActivity(), ContextCompat.getColor(requireContext(), R.color.white))
        processArgs()

        observeData()

        requestCameraPermission()
        setClickListener(btn_scan)
    }

    override fun getLayoutId(): Int = R.layout.layout_fragment_scan

    private fun observeData() {
        mSharedViewModel.mScanResponseLiveData.observe(viewLifecycleOwner,
        Observer {
            DialogUtil.hideLoader()
            if (it == null) return@Observer

            StyledToaster.showSuccessToast(it.message)

            if (args.userType == Const.UserType.CUSTOMER.genericName) {
                val bs = RateNowDialog(
                    object : RateNowDialog.OnAppliedRating {
                        override fun onAppliedRating(rating: Float) {
                            StyledToaster.showInfoToast("Thank You For Providing Your Valuable Feedback")
                        }
                    }
                )
                bs.show(requireActivity().supportFragmentManager, RateNowDialog::class.java.simpleName)
            }
            mSharedViewModel.mScanResponseLiveData.value = null
            findNavController().popBackStack()

        })
    }

    private fun routeToCamera() {
        val i = Intent(requireContext(), ScannerActivity::class.java)
        startActivityForResult(i, Const.RequestCode.QR_SCAN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            val result = data?.getStringExtra(Const.RequestResult.SCAN_RESULT)
            StyledToaster.showSuccessToast("Scanned Successfully")
            handleScanResult(result, requestCode)
        } else {
            StyledToaster.showErrorToast("Could not fetch Scan Result!")
        }
    }

    private fun handleScanResult(result: String?, requestCode: Int) {
        if (result == null) {
            StyledToaster.showErrorToast("Could not fetch Scan Result!")
            return
        }
        when (requestCode) {
            Const.RequestCode.QR_SCAN -> {
                when (args.userType) {
                    Const.UserType.DELIVERY_MAN.genericName -> {
                        DialogUtil.showLoader(requireContext())
                        mSharedViewModel.confirmPickup(result.trim())
                    }
                    Const.UserType.CUSTOMER.genericName -> {
                        DialogUtil.showLoader(requireContext())
                        mSharedViewModel.completeDelivery(result.trim())
                    }
                    else -> {
                        StyledToaster.showErrorToast("Error: Invalid Data")
                        findNavController().popBackStack()
                    }
                }
            }
            else -> {
                StyledToaster.showErrorToast("Unknown Error Occurred!")
            }
        }

    }

    override fun onClick(v: View?) {
        super.onClick(v)
        when(v) {
            btn_scan -> {
                routeToCamera()
            }
        }
    }

    private fun processArgs() {

        when (args.userType) {
            Const.UserType.DELIVERY_MAN.genericName -> {
                tv_action_name.text = getString(R.string.msg_confirm_pickup)
                btn_scan.text = getString(R.string.label_confirm_pickup)
                btn_scan.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.colorSecondary))
            }
            Const.UserType.CUSTOMER.genericName -> {
                tv_action_name.text = getString(R.string.msg_complete_delivery)
                btn_scan.text = getString(R.string.label_complete_delivery)
                btn_scan.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.colorPrimary))
            }
            else -> {
                StyledToaster.showErrorToast("Error: Invalid Data")
                findNavController().popBackStack()
            }
        }
    }

    private fun requestCameraPermission() {
        if (!PermissionsUtil.isPermissionAllowed(requireContext(), Manifest.permission.CAMERA)) {
            PermissionsUtil.requestPermission(
                requireContext(),
                requireActivity(),
                Manifest.permission.CAMERA,
                object : PermissionListener {
                    override fun onPermissionGranted(response: PermissionGrantedResponse) {
                        StyledToaster.showSuccessToast("Permission Granted")
                    }

                    override fun onPermissionDenied(response: PermissionDeniedResponse) {
                        StyledToaster.showSuccessToast("Please enable camera permission.")
                    }

                    override fun onPermissionRationaleShouldBeShown(
                        permission: PermissionRequest?,
                        token: PermissionToken?
                    ) {

                    }
                }
            )
        }
    }

    private fun setStatusBarColor(activity: Activity, statusBarColor: Int, isLight: Boolean = false) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.window?.statusBarColor = statusBarColor
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                activity.window?.decorView?.systemUiVisibility = if(isLight) View.SYSTEM_UI_FLAG_VISIBLE else View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
        }
    }
}