package com.kagojernouka.views

import com.ashiqur.androidbaselib.base.BaseViewModel
import com.kagojernouka.utils.api.ApiClient
import io.reactivex.disposables.CompositeDisposable


/*
 * Created by : 
 * <a href="https://www.github.com/ashiqursuperfly">Ashiqur Rahman</a> on 11/14/20.
*/

abstract class CustomBaseViewModel : BaseViewModel(){
    val mDisposable: CompositeDisposable = CompositeDisposable()
    val mCommonApiService by lazy { ApiClient.createCommonApiService() }

    override fun onCleared() {

        mDisposable.dispose()

        super.onCleared()
    }
}