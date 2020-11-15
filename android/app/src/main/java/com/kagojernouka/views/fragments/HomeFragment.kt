package com.kagojernouka.views.fragments

import android.app.Activity
import android.os.Build
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.ashiqur.androidbaselib.base.BaseFragment
import com.kagojernouka.R
import com.kagojernouka.utils.NavigationUtil
import com.kagojernouka.views.dialog.DialogUtil
import kotlinx.android.synthetic.main.layout_fragment_home.*
import androidx.lifecycle.ViewModelProviders

/*
 * Created by : 
 * <a href="https://www.github.com/ashiqursuperfly">Ashiqur Rahman</a> on 11/14/20.
*/

class HomeFragment: BaseFragment() {

    private lateinit var mSharedViewModel: SharedViewModel

    override fun getLayoutId(): Int = R.layout.layout_fragment_home

    override fun afterOnViewCreated() {

        mSharedViewModel = requireActivity().let { ViewModelProviders.of(it).get(SharedViewModel::class.java) }

        observeData()

        setStatusBarColor(requireActivity(), ContextCompat.getColor(requireContext(), R.color.white))

        setClickListener(btn_delivery_man, btn_customer)
    }

    private fun observeData() {

        mSharedViewModel.mUserInfoLiveData.observe(viewLifecycleOwner,
        Observer {
            DialogUtil.hideLoader()
            if (it == null) {
                return@Observer
            }
            NavigationUtil.navigate(
                findNavController(),
                R.id.homeFragment,
                HomeFragmentDirections.actionHomeFragmentToProfile()
            )
        })

        mSharedViewModel.mDeliveryManDataLiveData.observe(viewLifecycleOwner,
                Observer {
                    DialogUtil.hideLoader()
                    if (it == null) {
                        return@Observer
                    }
                    NavigationUtil.navigate(
                        findNavController(),
                        R.id.homeFragment,
                        HomeFragmentDirections.actionHomeFragmentToProfile()
                    )
        })

    }

    override fun onClick(v: View?) {
        super.onClick(v)

        when(v) {
            btn_customer -> {
                DialogUtil.showLoader(requireContext())
                mSharedViewModel.getCustomerInfo()
            }
            btn_delivery_man -> {
                DialogUtil.showLoader(requireContext())
                mSharedViewModel.getDeliveryManInfo()
            }
        }

    }

    private fun setStatusBarColor(activity: Activity, statusBarColor: Int, isLight: Boolean = false) {
        // Timber.d("setStatusBar: Light${isLight}")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.window?.statusBarColor = statusBarColor
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                activity.window?.decorView?.systemUiVisibility = if(isLight) View.SYSTEM_UI_FLAG_VISIBLE else View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
        }
    }
}