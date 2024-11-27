package com.example.promcosermobileapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.promcosermobileapp.databinding.FragmentPersonalApiBinding
import com.example.promcosermobileapp.ui.personal.adapter.PersonalAdapter
import com.example.promcosermobileapp.ui.personal.model.PersonalModel
import com.example.promcosermobileapp.ui.personal.service.PersonalApiService
import com.example.promcosermobileapp.ui.personal.service.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PersonalApiFragment : Fragment() {
    private var lstPersonal = listOf<PersonalModel>()
    private lateinit var personalAdapter: PersonalAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflar el layout del fragmento
        val view: View = inflater.inflate(R.layout.fragment_personal_api, container, false)

        // Obtener referencia al RecyclerView
        val recyclerViewPersonal = view.findViewById<RecyclerView>(R.id.rvHistorialPersonal)
        recyclerViewPersonal.layoutManager = LinearLayoutManager(requireContext())

        // Crear el adaptador y asignarlo al RecyclerView
        personalAdapter = PersonalAdapter(lstPersonal)
        recyclerViewPersonal.adapter = personalAdapter

        // Llamar a la función para cargar los datos desde la API
        loadPersonalData()

        return view
    }

    private fun loadPersonalData() {
        // Llamada a la API usando Retrofit
        val call = RetrofitInstance.api.getPersonal() // Asegúrate de tener tu Retrofit configurado

        // Realizar la llamada de manera asincrónica
        call.enqueue(object : Callback<List<PersonalModel>> {
            override fun onResponse(
                call: Call<List<PersonalModel>>,
                response: Response<List<PersonalModel>>
            ) {
                if (response.isSuccessful) {
                    // Si la respuesta es exitosa, actualiza la lista de personal
                    lstPersonal = response.body() ?: emptyList()
                    personalAdapter.updatePersonal(lstPersonal) // Aquí actualizas la lista en el adaptador
                }
            }

            override fun onFailure(call: Call<List<PersonalModel>>, t: Throwable) {
                // En caso de error, loguear el error
                Log.e("PersonalApiFragment", "Error al cargar el personal", t)
            }
        })
    }
}