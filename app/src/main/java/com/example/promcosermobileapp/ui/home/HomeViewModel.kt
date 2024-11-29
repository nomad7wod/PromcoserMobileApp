package com.example.promcosermobileapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.promcosermobileapp.ui.home.model.ClienteModel
import com.example.promcosermobileapp.ui.home.model.MaquinariaModel
import com.example.promcosermobileapp.ui.home.model.ParteDiarioModel
import com.example.promcosermobileapp.ui.home.model.PersonalModel
import com.example.promcosermobileapp.ui.home.repository.ParteDiarioApiRepository
import com.example.promcosermobileapp.ui.home.service.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback

class HomeViewModel : ViewModel() {

    private val repository = ParteDiarioApiRepository() // Reemplaza con tu implementación

    //parte diario
    private val _parteDiarioList = MutableLiveData<Boolean>()
    val parteDiarioList: MutableLiveData<Boolean> get() = _parteDiarioList

    //clientes, maquinaria, personal
    private val _clientes = MutableLiveData<List<ClienteModel>>()
    val clientes: LiveData<List<ClienteModel>> get() = _clientes

    private val _maquinaria = MutableLiveData<List<MaquinariaModel>>()
    val maquinaria: LiveData<List<MaquinariaModel>> get() = _maquinaria

    private val _personal = MutableLiveData<List<PersonalModel>>()
    val personal: LiveData<List<PersonalModel>> get() = _personal

    // carga clientes
    fun loadClientes(){
        val call = RetrofitInstance.api.getCliente()
        call.enqueue(object : Callback<List<ClienteModel>> {
            override fun onResponse(
                call: Call<List<ClienteModel>>,
                response: retrofit2.Response<List<ClienteModel>>
            ) {
                if (response.isSuccessful) {
                    _clientes.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<ClienteModel>>, t: Throwable) {
                _clientes.value = emptyList()
            }

        })
    }

    // carga maquinaria
    fun loadMaquinaria(){
        val call = RetrofitInstance.api.getMaquinaria()
        call.enqueue(object : Callback<List<MaquinariaModel>> {
            override fun onResponse(
                call: Call<List<MaquinariaModel>>,
                response: retrofit2.Response<List<MaquinariaModel>>
            ) {
                if (response.isSuccessful) {
                    _maquinaria.value = response.body()
                }

            }

            override fun onFailure(call: Call<List<MaquinariaModel>>, t: Throwable) {
                _maquinaria.value = emptyList()
            }

        })
    }


    //carga personal
    fun loadPersonal(){
        val call = RetrofitInstance.api.getPersonal()
        call.enqueue(object : Callback<List<PersonalModel>> {
            override fun onResponse(
                call: Call<List<PersonalModel>>,
                response: retrofit2.Response<List<PersonalModel>>
            ) {
                if (response.isSuccessful) {
                    _personal.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<PersonalModel>>, t: Throwable) {
                _personal.value = emptyList()
            }

        })
    }




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