package com.kagojernouka.views.dialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


/*
 * Created by : 
 * <a href="https://www.github.com/ashiqursuperfly">Ashiqur Rahman</a> on 11/15/20.
*/

abstract class BaseBottomSheetDialog() : BottomSheetDialogFragment(), View.OnClickListener {

    lateinit var mContext: Context
    lateinit var mRootView: View

    abstract fun getLayoutId(): Int

    abstract fun afterOnCreateView()

    @Nullable
    override fun onCreateView(
        inflater: LayoutInflater,
        @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?
    ): View? {

        mRootView = (DataBindingUtil.inflate(inflater, getLayoutId(), container, false) as ViewDataBinding).root

        afterOnCreateView()

        return mRootView
    }

    override fun onClick(v: View?) { }

    fun setClickListener(vararg views: View) {
        views.forEach { view -> view.setOnClickListener(this) }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
}