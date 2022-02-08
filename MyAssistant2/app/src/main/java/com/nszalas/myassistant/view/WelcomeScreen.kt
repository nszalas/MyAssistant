package com.nszalas.myassistant.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.nszalas.myassistant.R
import res.layout.*


class WelcomeScreen : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_welcome_screen, container, false)



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.WelcomeStudentsListButton).setOnClickListener {
            view.findNavController().navigate(R.id.action_welcomeScreen_to_studentsList)
        }
        view.findViewById<Button>(R.id.WelcomeSubjectsListButton).setOnClickListener {
            view.findNavController().navigate(R.id.action_welcomeScreen_to_subjectsList)
        }

        view.findViewById<Button>(R.id.WelcomeGradesListButton).setOnClickListener {
            view.findNavController().navigate(R.id.action_welcomeScreen_to_gradesList)
        }
    }

}