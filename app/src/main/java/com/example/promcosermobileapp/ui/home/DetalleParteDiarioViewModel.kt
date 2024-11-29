package com.example.promcosermobileapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.promcosermobileapp.ui.home.model.DetalleParteDiarioModel
import com.example.promcosermobileapp.ui.home.repository.DetalleRepository
import retrofit2.Call
import retrofit2.Callback

class DetalleParteDiarioViewModel : ViewModel() {
    private val repository = DetalleRepository()
    private val _detalle = MutableLiveData<Boolean>()
    val detalle: MutableLiveData<Boolean> get() = _detalle

    fun createDetalleParteDiario(detalle: DetalleParteDiarioModel) {
        repository.createDetalleDiario(detalle).enqueue(object : Callback<DetalleParteDiarioModel> {
            override fun onResponse(
                call: Call<DetalleParteDiarioModel>,
                response: retrofit2.Response<DetalleParteDiarioModel>
            ){
                if (response.isSuccessful){
                    _detalle.value = true
                }
            }
            override fun onFailure(call: Call<DetalleParteDiarioModel>, t: Throwable) {
                _detalle.value = false
            }
        })
            }
        }