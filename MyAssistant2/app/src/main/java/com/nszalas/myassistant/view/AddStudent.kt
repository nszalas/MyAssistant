package com.nszalas.myassistant.view

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.nszalas.myassistant.R
import com.nszalas.myassistant.model.Student
import com.nszalas.myassistant.viewmodel.StudentViewModel
import kotlinx.android.synthetic.main.fragment_add_student.*
import kotlinx.android.synthetic.main.fragment_add_student.view.*


class AddStudent : Fragment() {

    private lateinit var mStudentViewModel: StudentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_add_student, container, false)

        mStudentViewModel = ViewModelProvider(this).get(StudentViewModel::class.java)

        view.AddStudentAddStudentButton.setOnClickListener {
            insertStudentToDataBase()
        }

        return view
    }

    private fun insertStudentToDataBase() {
        val studentName = AddStudentNameTextView.text.toString()
        val studentSurname = AddStudentSurnameTextView.text.toString()

        if (inputCheckStudents(studentName,studentSurname)){
            val student = Student(0,studentName, studentSurname)
            mStudentViewModel.addStudent(student)
            Toast.makeText(requireContext(),"Student został dodany!",Toast.LENGTH_LONG).show()

            activity?.onBackPressed()
        }
        else{
            Toast.makeText(requireContext(),"Uzupełnij wszystkie pola!",Toast.LENGTH_LONG).show()
        }

    }

    private fun inputCheckStudents(studentName: String, studentSurname: String): Boolean{
        return !(TextUtils.isEmpty(studentName) && TextUtils.isEmpty(studentSurname))
    }

}