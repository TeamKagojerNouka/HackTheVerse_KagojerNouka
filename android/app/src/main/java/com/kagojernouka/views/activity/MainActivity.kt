package com.kagojernouka.views.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ashiqur.androidbaselib.base.BaseActivity
import com.kagojernouka.R

class MainActivity : BaseActivity() {

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun getToolbarId(): Int = NO_TOOLBAR

    override fun afterOnCreate() {

    }

}