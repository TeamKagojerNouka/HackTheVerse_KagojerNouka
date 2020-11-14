package com.kagojernouka.views.fragments

import androidx.lifecycle.MutableLiveData
import com.kagojernouka.data.*
import com.kagojernouka.utils.GsonUtil
import com.kagojernouka.views.CustomBaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber


/*
 * Created by : 
 * <a href="https://www.github.com/ashiqursuperfly">Ashiqur Rahman</a> on 11/14/20.
*/

class SharedViewModel : CustomBaseViewModel() {

    val mUserInfoLiveData = MutableLiveData<CustomerData>()
    val mDeliveryManDataLiveData = MutableLiveData<DeliveryManData>()
    val mScanResponseLiveData = MutableLiveData<SimpleResponse>()

    fun getTest() {
        mDisposable.add(
            mCommonApiService.getTestResponse()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Timber.d("Test Response: $it")
                }, {
                    //mRepairerListLiveData.postValue(null)
                    Timber.d("Error:$it")
                }
                )
        )
    }

    fun getCustomerInfo() {

        val url = Const.Api.ENDPOINTS.CUSTOMER_INFO.replace("$1", "1")

        mDisposable.add(
            mCommonApiService.getCustomerInfo(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Timber.d("Response: $it")
                    mUserInfoLiveData.postValue(it)
                }, {
                    Timber.d("Error:$it")
                    mUserInfoLiveData.postValue(null)
                }
                )
        )
    }

    fun getDeliveryManInfo() {

        val url = Const.Api.ENDPOINTS.DELIVERY_MAN_INFO.replace("$1", "1")

        mDisposable.add(
            mCommonApiService.getDeliveryInfo(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    mDeliveryManDataLiveData.postValue(it)
                    Timber.d("Response: $it")
                }, {
                    mDeliveryManDataLiveData.postValue(null)
                    Timber.d("Error:$it")
                }
                )
        )
    }

    fun completeDelivery(scanResult: String) {

        val res = GsonUtil.fromJson(scanResult, ScanData::class.java)

        val params = HashMap<String, String>()
        params["uuid"] = res.uuid

        val url = Const.Api.ENDPOINTS.COMPLETE_DELIVERY.replace("$1", "1")

        Timber.d("Res: $res Params: $params")

        mDisposable.add(
            mCommonApiService.completeDelivery(url, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    mScanResponseLiveData.postValue(it)
                    Timber.d("Response: $it")
                }, {
                    // mDeliveryManDataLiveData.postValue(null)
                    Timber.d("Error:$it")
                }
            )
        )

    }

    fun confirmPickup(scanResult: String) {

        val res = GsonUtil.fromJson(scanResult, ScanData::class.java)

        val params = HashMap<String, String>()
        params["uuid"] = res.uuid
        params["delivery_man_id"] = "1"

        Timber.d("Res: $res Params: $params")

        val url = Const.Api.ENDPOINTS.CONFIRM_PICKUP.replace("$1", "1")

        mDisposable.add(
            mCommonApiService.confirmPickup(url, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    mScanResponseLiveData.postValue(it)
                    Timber.d("Response: $it")
                }, {
                    // mDeliveryManDataLiveData.postValue(null)
                    Timber.d("Error:$it")
                }
            )
        )

    }

}