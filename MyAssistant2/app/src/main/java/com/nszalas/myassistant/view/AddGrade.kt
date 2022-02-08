package com.nszalas.myassistant.view

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.nszalas.myassistant.R
import com.nszalas.myassistant.model.Grade
import com.nszalas.myassistant.viewmodel.GradeViewModel
import kotlinx.android.synthetic.main.fragment_add_grade.*
import kotlinx.android.synthetic.main.fragment_add_grade.view.*

class AddGrade : Fragment() {

    private lateinit var mGradeViewModel: GradeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_grade, container, false)

        mGradeViewModel = ViewModelProvider(this).get(GradeViewModel::class.java)

        view.UpdateGradeUpdateGradeButton.setOnClickListener {
            insertGradetoDataBase()
        }

        return view
    }

    private fun insertGradetoDataBase() {
        val gradeName = AddGradeGradeName.text.toString()
        val gradeNote = UpdateGradeGradeNote.text.toString()
        val gradeStudent = UpdateGradeGradeStudent.text.toString()
        val gradeSubject = UpdateGradeGradeSubject.text.toString()

        if(inputCheckGrade(gradeName, gradeNote, gradeStudent,gradeSubject))
        {
            val grade = Grade(0,gradeName, gradeNote,gradeStudent,gradeSubject)
            mGradeViewModel.addGrade(grade)
            Toast.makeText(requireContext(),"Ocena dodana!", Toast.LENGTH_LONG).show()

            activity?.onBackPressed()
        }
        else{
            Toast.makeText(requireContext(),"Uzupełnij wszystkie pola!", Toast.LENGTH_LONG).show()

        }

    }

    private fun inputCheckGrade(gradeName: String, gradeNote: String,
                                gradeStudent: String, gradeSubject: String): Boolean{
        return !(TextUtils.isEmpty(gradeName) && TextUtils.isEmpty(gradeNote) &&TextUtils.isEmpty(gradeStudent) && TextUtils.isEmpty(gradeSubject))
    }
}