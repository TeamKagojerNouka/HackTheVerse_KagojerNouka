package com.kagojernouka.views.fragments

import android.app.Activity
import android.os.Build
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.ashiqur.androidbaselib.base.BaseFragment
import com.kagojernouka.R
import com.kagojernouka.data.Const
import com.kagojernouka.data.CustomerData
import com.kagojernouka.data.DeliveryManData
import com.kagojernouka.utils.NavigationUtil
import com.kagojernouka.utils.StyledToaster
import kotlinx.android.synthetic.main.layout_fragment_profile.*


/*
 * Created by : 
 * <a href="https://www.github.com/ashiqursuperfly">Ashiqur Rahman</a> on 11/14/20.
*/

class ProfileFragment: BaseFragment() {

    private lateinit var mSharedViewModel: SharedViewModel

    private lateinit var userData: Any

    override fun afterOnViewCreated() {

        mSharedViewModel = requireActivity().let { ViewModelProviders.of(it).get(SharedViewModel::class.java) }

        observeData()

        setClickListener(btn_scan)
    }

    override fun getLayoutId(): Int = R.layout.layout_fragment_profile

    private fun observeData() {
        mSharedViewModel.mUserInfoLiveData.observe(viewLifecycleOwner, Observer {
            if (it == null) {
                StyledToaster.showErrorToast("Error: Could not load user profile !!")
                return@Observer
            }
            //StyledToaster.showSuccessToast("Loaded Customer Profile")

            userData = it
            updateUi(it)

        })
        mSharedViewModel.mDeliveryManDataLiveData.observe(viewLifecycleOwner, Observer {
            if (it == null) {
                StyledToaster.showErrorToast("Error: Could not load user profile !!")
                return@Observer
            }
            //StyledToaster.showSuccessToast("Loaded Delivery Man Profile")

            userData = it
            updateUi(it)

        })
    }

    private fun updateUi(data: Any) {

        if (data is CustomerData) {
            tv_user_type.text = Const.UserType.CUSTOMER.genericName
            tv_user_name.text = data.full_name

            // dynamic views
            tv_user_id.text = data.id.toString()

            tv_address.text = data.address
            tv_label_address.text = getString(R.string.label_address)

            tv_phone.text = data.phone_num
            tv_label_phone.text = getString(R.string.label_phone_no)

            header.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.colorPrimary))

            btn_scan.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.colorPrimary))
            btn_scan.text = getString(R.string.label_complete_delivery)

            setStatusBarColor(requireActivity(), ContextCompat.getColor(requireContext(), R.color.colorPrimary))

            updateVisibility(
                View.VISIBLE,
                tv_label_user_id,
                tv_user_id,
                tv_address,
                tv_label_address,
                tv_phone,
                tv_label_phone
            )

            updateVisibility(
                View.INVISIBLE,
                tv_extra1,
                tv_label_extra1,
                tv_extra2,
                tv_label_extra2
            )
        }
        else if (data is DeliveryManData) {
            tv_user_type.text = Const.UserType.DELIVERY_MAN.genericName
            tv_user_name.text = data.full_name

            // dynamic views
            tv_user_id.text = data.id.toString()

            tv_address.text = data.nid
            tv_label_address.text = getString(R.string.label_nid)

            tv_phone.text = data.phone_num
            tv_label_phone.text = getString(R.string.label_phone_no)

            tv_extra1.text = data.date_of_birth
            tv_label_extra1.text = getString(R.string.label_dob)

            tv_extra2.text = "9.6" // data.reliability_index
            tv_label_extra2.text = getString(R.string.label_reliability_idx)

            header.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.colorSecondary))

            btn_scan.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.colorSecondary))
            btn_scan.text = getString(R.string.label_confirm_pickup)

            setStatusBarColor(requireActivity(), ContextCompat.getColor(requireContext(), R.color.colorSecondary))

            updateVisibility(
                View.VISIBLE,
                tv_label_user_id,
                tv_user_id,
                tv_address,
                tv_label_address,
                tv_phone,
                tv_label_phone,
                tv_extra1,
                tv_label_extra1,
                tv_extra2,
                tv_label_extra2
            )

        }

    }

    private fun updateVisibility(visibility: Int, vararg views: View) {
        for (item in views)
            item.visibility = visibility
    }

    private fun setStatusBarColor(activity: Activity, statusBarColor: Int, isLight: Boolean = false) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.window?.statusBarColor = statusBarColor
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                activity.window?.decorView?.systemUiVisibility = if(isLight) View.SYSTEM_UI_FLAG_VISIBLE else View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
        }
    }

    override fun onClick(v: View?) {
        super.onClick(v)

        when(v) {
            btn_scan -> {
                val userTypeName = if (userData is CustomerData) Const.UserType.CUSTOMER.genericName else Const.UserType.DELIVERY_MAN.genericName
                NavigationUtil.navigate(
                    findNavController(),
                    R.id.profileFragment,
                    ProfileFragmentDirections.actionProfileFragmentToScanFragment(userType = userTypeName)
                )
            }
        }
    }
}