package com.eneserdogan.bookcommentapp.LoginUI.ui.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eneserdogan.bookcommentapp.LoginUI.ui.BaseViewModel

class HomeViewModel(application: Application) : BaseViewModel(application) {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}