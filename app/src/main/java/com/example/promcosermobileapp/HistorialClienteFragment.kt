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
import com.example.promcosermobileapp.ui.cliente.adapter.ClienteAdapter
import com.example.promcosermobileapp.ui.cliente.model.ClienteModel
import com.example.promcosermobileapp.ui.cliente.service.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistorialClienteFragment : Fragment() {
    private var lstClientes = listOf<ClienteModel>()
    private lateinit var etSearchCliente: EditText
    private lateinit var clienteAdapter: ClienteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_historial_cliente, container, false)
        val rvHistorialCliente = view.findViewById<RecyclerView>(R.id.rvHistorialClientes)
        rvHistorialCliente.layoutManager = LinearLayoutManager(requireContext())

        clienteAdapter = ClienteAdapter(lstClientes)
        rvHistorialCliente.adapter = clienteAdapter

        etSearchCliente = view.findViewById(R.id.etBuscar)

        etSearchCliente.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
                charSequence?.let { filterClienteList(it.toString()) }
            }

            override fun afterTextChanged(p0: Editable?) {}

        })
        loadHistorialCliente()
        return view
    }
    private fun loadHistorialCliente() {
        val call = RetrofitInstance.api.getClientes()

        call.enqueue(object : Callback<List<ClienteModel>> {
            override fun onResponse(
                call: Call<List<ClienteModel>>,
                response: Response<List<ClienteModel>>
            ) {
                if (response.isSuccessful) {
                    lstClientes = response.body() as ArrayList<ClienteModel>
                    clienteAdapter.updateCliente(lstClientes)

                }
            }

            override fun onFailure(call: Call<List<ClienteModel>>, t: Throwable) {
                Log.e("HistorialClienteFragment", "Error al cargar el historial de clientes", t)
            }
        })

    }
    private fun filterClienteList(query: String) {
        val filteredList = lstClientes.filter {
            it.nombre.contains(query, ignoreCase = true) || it.apellido.contains(query, ignoreCase = true)
        }
        clienteAdapter.updateCliente(filteredList)

    }


}