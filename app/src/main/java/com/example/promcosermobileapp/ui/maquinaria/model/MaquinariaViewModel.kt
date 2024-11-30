package com.example.promcosermobileapp.ui.maquinaria.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.promcosermobileapp.ui.cliente.model.ClienteModel
import com.example.promcosermobileapp.ui.cliente.repository.ClienteAPiRepository
import com.example.promcosermobileapp.ui.home.model.Maquinaria
import com.example.promcosermobileapp.ui.maquinaria.repository.MaquinariaAPiRepository
import retrofit2.Call
import retrofit2.Callback

class MaquinariaViewModel: ViewModel(){
    private val repository = MaquinariaAPiRepository() // Reemplaza con tu implementación

//cliente
private val _clienteList = MutableLiveData<Boolean>()
val clienteList: MutableLiveData<Boolean> get() = _clienteList




fun createMaquinaria(maquinaria: Maquinaria) {
    repository.postMaquinaria(maquinaria).enqueue(object : Callback<Maquinaria> {
        override fun onResponse(
            call: Call<Maquinaria>,
            response: retrofit2.Response<Maquinaria>
        ) {
            if (response.isSuccessful) {
                _clienteList.value = true
                Log.e("ListaMaquinariasFragment", "OKA"+response.message().toString())

            }
        }

        override fun onFailure(call: Call<Maquinaria>, t: Throwable) {
            _clienteList.value = false
            Log.e("ListaMaquinariasFragment", "false")

        }

    })

}
}