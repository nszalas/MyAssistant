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
import com.nszalas.myassistant.model.Student
import com.nszalas.myassistant.viewmodel.StudentViewModel
import kotlinx.android.synthetic.main.fragment_update_student.*
import kotlinx.android.synthetic.main.fragment_update_student.view.*


class UpdateStudent : Fragment() {


    private val args by navArgs<UpdateStudentArgs>()

    private lateinit var mStudentViewModel: StudentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update_student, container, false)

        mStudentViewModel = ViewModelProvider(this).get(StudentViewModel::class.java)

        view.UpdateStudentNameTextView.setText(args.currentStudent.name)
        view.UpdateStudentSurnameTextView.setText(args.currentStudent.surname)

        view.UpdateStudentUpdateStudentButton.setOnClickListener {
            updateStudent()
        }

        view.UpdateStudentDeleteStudentButton.setOnClickListener{
            deleteStudent()

        }

        return view
    }

    private fun deleteStudent() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Tak"){_,_->
            mStudentViewModel.deleteStudent(args.currentStudent)
            Toast.makeText(requireContext(), "Student usunięty!", Toast.LENGTH_LONG).show()
            activity?.onBackPressed()
        }
        builder.setNegativeButton("Nie"){_,_ ->}
        builder.setTitle("Usuń ${args.currentStudent.name}")
        builder.setMessage("Czy na pewno chcesz usunąć ${args.currentStudent.name}?")
        builder.create().show()
    }

    private fun updateStudent(){
        val studentName = UpdateStudentNameTextView.text.toString()
        val studentSurname = UpdateStudentSurnameTextView.text.toString()

        if(inputCheckStudents(studentName,studentSurname)){
            val updatedStudent = Student(args.currentStudent.id,studentName,studentSurname)

            mStudentViewModel.updateStudent(updatedStudent)
            Toast.makeText(requireContext(),"Student został zmodyfikowany!", Toast.LENGTH_LONG).show()

            activity?.onBackPressed()
        }

    }

    private fun inputCheckStudents(studentName: String, studentSurname: String): Boolean{
        return !(TextUtils.isEmpty(studentName) && TextUtils.isEmpty(studentSurname))
    }


}