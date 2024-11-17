package com.example.promcosermobileapp.ui.personal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.promcosermobileapp.R
import com.example.promcosermobileapp.databinding.FragmentPersonalBinding

class PersonalFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view : View = inflater.inflate(R.layout.fragment_personal, container, false)
        val btnhist: Button = view.findViewById(R.id.btnhistorial)
        val navController = findNavController()
        btnhist.setOnClickListener {
            navController.navigate(R.id.personalApiFragment)
        }

        return view
    }
}