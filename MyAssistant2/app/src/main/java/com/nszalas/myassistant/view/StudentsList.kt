package com.nszalas.myassistant.view

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ListAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.nszalas.myassistant.R
import com.nszalas.myassistant.viewmodel.StudentViewModel
import com.nszalas.myassistant.viewmodel.StudentsListAdapter
import kotlinx.android.synthetic.main.fragment_students_list.view.*
import res.layout.*


class StudentsList : Fragment() {

    private lateinit var mStudentViewModel: StudentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_students_list, container, false)

        val adapter = StudentsListAdapter()
        val recyclerView = view.StudentsListRecyclerView

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mStudentViewModel = ViewModelProvider(this).get(StudentViewModel::class.java)
        mStudentViewModel.readAllStudents.observe(viewLifecycleOwner, Observer { student ->
            adapter.setDataStudent(student)
        })

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<FloatingActionButton>(R.id.StudentsListAddStudentFloatingButton).setOnClickListener{
            view.findNavController().navigate(R.id.action_studentsList_to_addStudent)
        }

        view.findViewById<FloatingActionButton>(R.id.StudentsListDeleteAllStudentsFloatingActionButton).setOnClickListener {
            deleteAllStudents()
        }


    }

    private fun deleteAllStudents() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_->
            mStudentViewModel.deleteAllStudents()
            Toast.makeText(requireContext(), "Studenci usunięci!", Toast.LENGTH_LONG).show()

        }
        builder.setNegativeButton("No"){_,_ ->}
        builder.setTitle("Usuń wszystkich")
        builder.setMessage("Jesteś pewien, że chcesz usunąć wszystkich studentów?")
        builder.create().show()
    }


}