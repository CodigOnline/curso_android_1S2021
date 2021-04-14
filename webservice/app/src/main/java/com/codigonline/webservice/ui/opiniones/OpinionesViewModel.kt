package com.codigonline.webservice.ui.opiniones

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class OpinionesViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is home Opinion"
    }
    val text: LiveData<String> = _text

}