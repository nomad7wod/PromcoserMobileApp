package com.example.promcosermobileapp.ui.personal

import android.util.Log
import android.view.WindowInsetsAnimation
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.promcosermobileapp.ui.personal.model.PersonalModel
import com.example.promcosermobileapp.ui.personal.model.Ubigeomodel
import com.example.promcosermobileapp.ui.personal.model.rolmodel
import com.example.promcosermobileapp.ui.personal.repository.PeresonalAPiRepository
import com.example.promcosermobileapp.ui.personal.service.PersonalApiService
import com.example.promcosermobileapp.ui.personal.service.RetrofitInstance

import okhttp3.Response
import retrofit2.Call
import retrofit2.Callback

class PersonalViewModel : ViewModel() {

    private val repository = PeresonalAPiRepository() // Reemplaza con tu implementación

    //personal
    private val _personalList = MutableLiveData<Boolean>()
    val personalList: MutableLiveData<Boolean> get() = _personalList

    //rol y Ubigeo
    private val _roles = MutableLiveData<List<rolmodel>>()
    val roles: LiveData<List<rolmodel>> get() = _roles

    private val _ubigeo = MutableLiveData<List<Ubigeomodel>>()
    val ubigeo: LiveData<List<Ubigeomodel>> get() = _ubigeo

    //carga roles
    fun loadRoles() {
        val call = RetrofitInstance.api.getRoles()
        call.enqueue(object : Callback<List<rolmodel>> {
            override fun onResponse(
                call: Call<List<rolmodel>>,
                response: retrofit2.Response<List<rolmodel>>
            ) {
                if (response.isSuccessful) {
                    _roles.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<rolmodel>>, t: Throwable) {
                _roles.value = emptyList()
            }
        })
    }

    fun loadUbigeo() {
        val call = RetrofitInstance.api.getUbigeo()
        call.enqueue(object : Callback<List<Ubigeomodel>> {
            override fun onResponse(
                call: Call<List<Ubigeomodel>>,
                response: retrofit2.Response<List<Ubigeomodel>>
            ) {
                if (response.isSuccessful) {
                    _ubigeo.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<Ubigeomodel>>, t: Throwable) {
                _ubigeo.value = emptyList()

            }
        })
    }

    fun createPersonal(personal: PersonalModel) {
        repository.createPersonal(personal).enqueue(object : Callback<PersonalModel> {
            override fun onResponse(
                call: Call<PersonalModel>,
                response: retrofit2.Response<PersonalModel>
            ) {
                if (response.isSuccessful) {
                    _personalList.value = true
                }
            }

            override fun onFailure(call: Call<PersonalModel>, t: Throwable) {
                _personalList.value = false
            }
        })
    }
}

