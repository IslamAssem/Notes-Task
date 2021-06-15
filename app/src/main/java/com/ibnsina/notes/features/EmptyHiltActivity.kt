package com.ibnsina.notes.features

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ibnsina.utils.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
//Empty activity used for testing
@AndroidEntryPoint
class EmptyHiltActivity : BaseActivity() {
    override fun doBinding() {
    }
    override fun initViews() {
    }

    override fun saveInstanceState(savedInstanceState: Bundle) {
    }

    override fun initData(data: Bundle) {
    }

    override fun initVariables(context: Context) {
    }

    override fun initViewModel() {
    }
}