package com.example.promcosermobileapp.ui.personal.repository

import com.example.promcosermobileapp.ui.personal.model.PersonalModel
import com.example.promcosermobileapp.ui.personal.service.PersonalApiService
import com.example.promcosermobileapp.ui.personal.service.RetrofitInstance

class PeresonalAPiRepository {

    private val PersonalApiService: PersonalApiService = RetrofitInstance.api

    fun getRoles() = PersonalApiService.getRoles()
    fun getUbigeo() = PersonalApiService.getUbigeo()
    fun createPersonal(personal: PersonalModel) = PersonalApiService.createPersonal(personal)


}