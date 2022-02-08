package com.nszalas.myassistant.view

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.nszalas.myassistant.R
import com.nszalas.myassistant.viewmodel.GradeViewModel
import com.nszalas.myassistant.viewmodel.GradesListAdapter
import kotlinx.android.synthetic.main.fragment_grades_list.view.*


class GradesList : Fragment() {

    private lateinit var mGradeViewModel: GradeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_grades_list, container, false)

        val adapter = GradesListAdapter()
        val recyclerView = view.GradesListRecyclerView

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mGradeViewModel = ViewModelProvider(this).get(GradeViewModel::class.java)
        mGradeViewModel.readAllGrades.observe(viewLifecycleOwner, Observer { grade ->
            adapter.setDataGrade(grade)
        })

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<FloatingActionButton>(R.id.GradesListAddGradeFloatingButton).setOnClickListener {
            view.findNavController().navigate(R.id.action_gradesList_to_addGrade)
        }

        view.findViewById<FloatingActionButton>(R.id.GradesListDeleteAllGradesFloatingActionButton).setOnClickListener {
            deleteAllGrades()
        }

    }

    private fun deleteAllGrades() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_->
            mGradeViewModel.deleteAllGrades()
            Toast.makeText(requireContext(), "Oceny usunięte!", Toast.LENGTH_LONG).show()

        }
        builder.setNegativeButton("No"){_,_ ->}
        builder.setTitle("Usuń wszystko")
        builder.setMessage("Jesteś pewien, że chcesz usunąć wszystko?")
        builder.create().show()
    }
}