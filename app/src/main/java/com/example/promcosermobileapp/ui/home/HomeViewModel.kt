package com.example.promcosermobileapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.promcosermobileapp.ui.home.model.ParteDiarioModel
import com.example.promcosermobileapp.ui.home.repository.ParteDiarioApiRepository
import retrofit2.Call
import retrofit2.Callback

class HomeViewModel : ViewModel() {

    private val repository = ParteDiarioApiRepository() // Reemplaza con tu implementación

    //parte diario
    private val _parteDiarioList = MutableLiveData<Boolean>()
    val parteDiarioList: MutableLiveData<Boolean> get() = _parteDiarioList


    fun createParteDiario(parteDiario: ParteDiarioModel){
        repository.createParteDiario(parteDiario).enqueue(object : Callback<ParteDiarioModel> {
            override fun onResponse(
                call: Call<ParteDiarioModel>,
                response: retrofit2.Response<ParteDiarioModel>
            ){
                if (response.isSuccessful){
                    _parteDiarioList.value = true
                }
            }
            override fun onFailure(call: Call<ParteDiarioModel>, t: Throwable) {
                _parteDiarioList.value = false
            }
        })
    }

}