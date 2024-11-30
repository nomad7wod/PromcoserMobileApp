package com.example.promcosermobileapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.promcosermobileapp.databinding.FragmentPersonalApiBinding
import com.example.promcosermobileapp.ui.personal.adapter.PersonalAdapter
import com.example.promcosermobileapp.ui.personal.model.PersonalModel
import com.example.promcosermobileapp.ui.personal.model.rolmodel
import com.example.promcosermobileapp.ui.personal.service.PersonalApiService
import com.example.promcosermobileapp.ui.personal.service.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PersonalApiFragment : Fragment() {

    private lateinit var personalAdapter: PersonalAdapter
    private lateinit var etBuscarEmpleado: EditText
    private var lstPersonal: List<PersonalModel> = emptyList() // Lista de empleados
    private var lstRoles: List<rolmodel> = emptyList() // Lista de roles

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_personal_api, container, false)
        val recyclerViewPersonal = view.findViewById<RecyclerView>(R.id.rvHistorialPersonal)
        recyclerViewPersonal.layoutManager = LinearLayoutManager(requireContext())

        personalAdapter = PersonalAdapter(lstPersonal, lstRoles)
        recyclerViewPersonal.adapter = personalAdapter

        etBuscarEmpleado = view.findViewById(R.id.etBuscarEmpleado)

        // Aplicar filtro cuando el texto cambie
        etBuscarEmpleado.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
                charSequence?.let { filterPersonalList(it.toString()) }
            }

            override fun afterTextChanged(editable: Editable?) {}
        })

        loadRoles()
        loadPersonalData()

        return view
    }

    private fun loadRoles() {
        val call = RetrofitInstance.api.getRoles()

        call.enqueue(object : Callback<List<rolmodel>> {
            override fun onResponse(
                call: Call<List<rolmodel>>,
                response: Response<List<rolmodel>>
            ) {
                if (response.isSuccessful) {
                    lstRoles = response.body() ?: emptyList()
                    personalAdapter.updateRoles(lstRoles)
                }
            }

            override fun onFailure(call: Call<List<rolmodel>>, t: Throwable) {
                Log.e("PersonalApiFragment", "Error al cargar los roles", t)
            }
        })
    }

    private fun loadPersonalData() {
        val call = RetrofitInstance.api.getPersonal()

        call.enqueue(object : Callback<List<PersonalModel>> {
            override fun onResponse(
                call: Call<List<PersonalModel>>,
                response: Response<List<PersonalModel>>
            ) {
                if (response.isSuccessful) {
                    lstPersonal = response.body() ?: emptyList()
                    personalAdapter.updatePersonal(lstPersonal)
                }
            }

            override fun onFailure(call: Call<List<PersonalModel>>, t: Throwable) {
                Log.e("PersonalApiFragment", "Error al cargar el personal", t)
            }
        })
    }

    private fun filterPersonalList(query: String) {
        val filteredList = lstPersonal.filter {
            it.nombre.contains(query, ignoreCase = true) || it.apellido.contains(query, ignoreCase = true)
        }
        personalAdapter.updatePersonal(filteredList)
    }
}


