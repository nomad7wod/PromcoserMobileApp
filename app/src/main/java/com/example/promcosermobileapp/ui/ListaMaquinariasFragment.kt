package com.example.promcosermobileapp.ui

import Adapter.MaquinariaAdapter
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.promcosermobileapp.R
import com.example.promcosermobileapp.ui.home.model.Maquinaria
import com.example.promcosermobileapp.ui.maquinaria.service.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListaMaquinariasFragment : Fragment() {
    private var lstMaquinarias = listOf<Maquinaria>()
    private lateinit var maquinariaAdapter: MaquinariaAdapter
    private lateinit var etBuscarMaquinaria: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.activity_lista_maquinarias_fragment, container, false)

        // Configurar el RecyclerView
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewMaquinarias)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        maquinariaAdapter = MaquinariaAdapter(lstMaquinarias)
        recyclerView.adapter = maquinariaAdapter

        etBuscarMaquinaria = view.findViewById(R.id.etBuscarMaquinaria)

        etBuscarMaquinaria.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
                charSequence?.let { filterMaquinariaList(it.toString()) }
            }

            override fun afterTextChanged(editable: Editable?) {}
        })

        loadMaquinariasData() // Cargar los datos de maquinarias

        return view
    }

    private fun loadMaquinariasData() {
        val call = RetrofitInstance.api.getMaquinaria()

        call.enqueue(object : Callback<List<Maquinaria>> {
            override fun onResponse(
                call: Call<List<Maquinaria>>,
                response: Response<List<Maquinaria>>
            ) {
                if (response.isSuccessful) {
                    lstMaquinarias = response.body() ?: emptyList()
                    maquinariaAdapter.updateMaquinariaList(lstMaquinarias)
                }
            }

            override fun onFailure(call: Call<List<Maquinaria>>, t: Throwable) {
                Log.e("ListaMaquinariasFragment", "Error al cargar las maquinarias", t)
            }
        })
    }

    private fun filterMaquinariaList(query: String) {
        val filteredList = lstMaquinarias.filter {
            it.placa.contains(query, ignoreCase = true) ||
                    it.modelo.contains(query, ignoreCase = true) ||
                    it.marca.contains(query, ignoreCase = true)
        }
        maquinariaAdapter.updateMaquinariaList(filteredList)
    }
}
