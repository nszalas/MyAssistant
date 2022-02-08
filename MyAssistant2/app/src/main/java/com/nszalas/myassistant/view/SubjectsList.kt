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
import com.nszalas.myassistant.viewmodel.SubjectViewModel
import com.nszalas.myassistant.viewmodel.SubjectsListAdapter
import kotlinx.android.synthetic.main.fragment_subjects_list.view.*

class SubjectsList : Fragment() {


    private lateinit var mSubjectViewModel: SubjectViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_subjects_list, container, false)

        val adapter = SubjectsListAdapter()
        val recyclerView = view.SubjectListRecyclerView

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mSubjectViewModel = ViewModelProvider(this).get(SubjectViewModel::class.java)
        mSubjectViewModel.readAdllSubjects.observe(viewLifecycleOwner, Observer { subject ->
            adapter.setDataSubject(subject)
        })


        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<FloatingActionButton>(R.id.SubjectListAddSubjectfloatingActionButton).setOnClickListener {
            view.findNavController().navigate(R.id.action_subjectsList_to_addSubject)
        }

        view.findViewById<FloatingActionButton>(R.id.SubjectListDeleteSubjectfloatingActionButton).setOnClickListener {
            deleteAllSubjects()
        }
    }

    private fun deleteAllSubjects() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_->
            mSubjectViewModel.deleteAllSubjects()
            Toast.makeText(requireContext(), "Przedmioty usunięte!", Toast.LENGTH_LONG).show()

        }
        builder.setNegativeButton("No"){_,_ ->}
        builder.setTitle("Usuń wszystko")
        builder.setMessage("Jesteś pewien, że chcesz usunąć wszystkie przedmioty?")
        builder.create().show()
    }


}