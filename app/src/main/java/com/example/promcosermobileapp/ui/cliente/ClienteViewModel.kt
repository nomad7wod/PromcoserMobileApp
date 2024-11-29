package com.example.promcosermobileapp.ui.cliente

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.promcosermobileapp.ui.cliente.model.ClienteModel
import com.example.promcosermobileapp.ui.cliente.repository.ClienteAPiRepository
import com.example.promcosermobileapp.ui.personal.repository.PeresonalAPiRepository
import retrofit2.Call
import retrofit2.Callback

class ClienteViewModel : ViewModel() {

    private val repository = ClienteAPiRepository() // Reemplaza con tu implementación

    //cliente
    private val _clienteList = MutableLiveData<Boolean>()
    val clienteList: MutableLiveData<Boolean> get() = _clienteList




    fun createCliente(cliente: ClienteModel) {
        repository.createCliente(cliente).enqueue(object : Callback<ClienteModel> {
            override fun onResponse(
                call: Call<ClienteModel>,
                response: retrofit2.Response<ClienteModel>
            ) {
                if (response.isSuccessful) {
                    _clienteList.value = true
                }
            }

            override fun onFailure(call: Call<ClienteModel>, t: Throwable) {
                _clienteList.value = false
            }

        })

    }
}