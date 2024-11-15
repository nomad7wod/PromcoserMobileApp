package com.example.promcosermobileapp.ui.cliente

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ClienteViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is cliente Fragment"
    }
    val text: LiveData<String> = _text
}