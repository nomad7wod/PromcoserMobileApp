package com.example.promcosermobileapp.ui.personal.service

import com.example.promcosermobileapp.ui.personal.model.PersonalModel
import com.example.promcosermobileapp.ui.personal.model.Ubigeomodel
import com.example.promcosermobileapp.ui.personal.model.rolmodel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PersonalApiService {
    @GET("RolsConS")
    fun getRoles(): Call<List<rolmodel>>
    @GET("UbigeosConS")
    fun getUbigeo(): Call<List<Ubigeomodel>>
    @GET("Personal")
    fun getPersonal(): Call<List<PersonalModel>>
    @POST("Personal")
    fun createPersonal(@Body personal: PersonalModel): Call<PersonalModel>



}