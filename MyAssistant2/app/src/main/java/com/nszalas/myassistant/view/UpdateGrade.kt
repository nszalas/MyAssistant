package com.nszalas.myassistant.view

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.nszalas.myassistant.R
import com.nszalas.myassistant.model.Grade
import com.nszalas.myassistant.viewmodel.GradeViewModel
import kotlinx.android.synthetic.main.fragment_update_grade.*
import kotlinx.android.synthetic.main.fragment_update_grade.view.*


class UpdateGrade : Fragment() {

    private val args by navArgs<UpdateGradeArgs>()

    private lateinit var mGradeViewModel: GradeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update_grade, container, false)

        mGradeViewModel = ViewModelProvider(this).get(GradeViewModel::class.java)

        view.UpdateGradeGradeName.setText(args.currentGrade.name_grade)
        view.UpdateGradeGradeNote.setText(args.currentGrade.grade_note)
        view.UpdateGradeGradeStudent.setText(args.currentGrade.grade_student)
        view.UpdateGradeGradeSubject.setText(args.currentGrade.grade_subject)

        view.UpdateGradeUpdateGradeButton.setOnClickListener {
            updateGrade()
        }

        view.UpdateGradeDeleteGradeImageButton.setOnClickListener{
            deleteGrade()
        }


        return view
    }

    private fun deleteGrade() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Tak"){_,_->
            mGradeViewModel.deleteGrade(args.currentGrade)
            Toast.makeText(requireContext(), "Ocena usunięta!", Toast.LENGTH_LONG).show()
            activity?.onBackPressed()
        }
        builder.setNegativeButton("Nie"){_,_ ->}
        builder.setTitle("Usuń ${args.currentGrade.name_grade}")
        builder.setMessage("Czy na pewno chcesz usunąć ${args.currentGrade.name_grade} należącą do ${args.currentGrade.grade_student} z przedmiotu ${args.currentGrade.grade_subject}?")
        builder.create().show()
    }

    private fun updateGrade() {
        val gradeName = UpdateGradeGradeName.text.toString()
        val gradeNote = UpdateGradeGradeNote.text.toString()
        val gradeStudent = UpdateGradeGradeStudent.text.toString()
        val gradeSubject = UpdateGradeGradeSubject.text.toString()

        if(inputCheckGrade(gradeName,gradeNote,gradeStudent,gradeSubject)){
            val updatedGrade = Grade(args.currentGrade.id_grade,gradeName,gradeNote,gradeStudent,gradeSubject)

            mGradeViewModel.updateGrade(updatedGrade)
            Toast.makeText(requireContext(),"Ocena została zmodyfikowana!", Toast.LENGTH_LONG).show()

            activity?.onBackPressed()
        }
    }

    private fun inputCheckGrade(gradeName: String, gradeNote: String,
                                gradeStudent: String, gradeSubject: String): Boolean{
        return !(TextUtils.isEmpty(gradeName) && TextUtils.isEmpty(gradeNote) && TextUtils.isEmpty(gradeStudent) && TextUtils.isEmpty(gradeSubject))
    }


}