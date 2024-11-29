package com.example.promcosermobileapp.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.example.promcosermobileapp.R
import com.example.promcosermobileapp.ui.home.model.DetalleParteDiarioModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DetalleParteDiarioFragment : BottomSheetDialogFragment() {

    private val viewModel: DetalleParteDiarioViewModel by activityViewModels()


    private lateinit var btnguardar: Button
    private lateinit var idpartediario: TextView
    private lateinit var horas: EditText
    private lateinit var trabajo: EditText
    private lateinit var descripcion: EditText


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View =  inflater.inflate(R.layout.fragment_detalle_parte_diario, container, false)

        btnguardar= view.findViewById(R.id.btnguardardpt)
        idpartediario = view.findViewById(R.id.idpd)
        horas = view.findViewById(R.id.ethoras)
        trabajo = view.findViewById(R.id.ettrabajo)
        descripcion = view.findViewById(R.id.etdescripcion)

        setupActions()


        return view
    }
    private fun setupActions() {
        btnguardar.setOnClickListener {
            val id = idpartediario.text.toString().toInt()
            val horas = horas.text.toString().toInt()
            val trabajo = trabajo.text.toString()
            val descripcion = descripcion.text.toString()

            val detalleParteDiario = DetalleParteDiarioModel(
                id_parte_diario = id, //conseguir de parte diario
                horas = horas,
                trabajo_efectuado = trabajo,
                descripcion = descripcion
            )

            dismiss()

        }

    }
    override fun onStart() {
        super.onStart()
        // Configurar el BottomSheet para que sea expandible
        dialog?.let { dialog ->
            val bottomSheet = dialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheet?.let {
                val behavior = BottomSheetBehavior.from(it)
                behavior.state = BottomSheetBehavior.STATE_COLLAPSED // Estado inicial colapsado
                behavior.peekHeight = 300 // Altura inicial visible

            }
        }
    }

}