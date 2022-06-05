package com.utp.comidaencasav1.ui.platos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PlatosViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private val _text = MutableLiveData<String>().apply {
        value = "Cheese tres"
    }
    val text: LiveData<String> = _text
}