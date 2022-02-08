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
import com.nszalas.myassistant.model.Subject
import com.nszalas.myassistant.viewmodel.SubjectViewModel
import kotlinx.android.synthetic.main.fragment_add_subject.*
import kotlinx.android.synthetic.main.fragment_add_subject.view.*


class AddSubject : Fragment() {

    private lateinit var mSubjectViewModel: SubjectViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_subject, container, false)

        mSubjectViewModel = ViewModelProvider(this).get(SubjectViewModel::class.java)

        view.AddSubjectAddSubjectButton.setOnClickListener {
            insertSubjecttoDataBase()
        }

        return view
    }

    private fun insertSubjecttoDataBase() {
        val subjectName = AddSubjectSubjectsNameTextView.text.toString()
        val subjectWeekDay = AddSubjectWeekDayTextView.text.toString()
        val subjectStartHour = AddSubjectStartHourTextView.text.toString()
        val subjectEndHour = AddSubjectEndHourTextView.text.toString()

        if(inputCheckSubjects(subjectName,subjectWeekDay,subjectStartHour,subjectEndHour)){
            val subject = Subject(0,subjectName,subjectWeekDay,subjectStartHour,subjectEndHour)
            mSubjectViewModel.addSubject(subject)
            Toast.makeText(requireContext(),"Przedmiot został dodany!", Toast.LENGTH_LONG).show()

            activity?.onBackPressed()
        }
        else{
            Toast.makeText(requireContext(),"Uzupełnij wszystkie pola!",Toast.LENGTH_LONG).show()

        }
    }

    private fun inputCheckSubjects(subjectName: String, subjectWeekDay: String,
                                   subjectStartHour: String, subjectEndHour: String)  : Boolean{
        return !(TextUtils.isEmpty(subjectName) && TextUtils.isEmpty(subjectWeekDay) && TextUtils.isEmpty(subjectStartHour)&& TextUtils.isEmpty(subjectEndHour))
    }


}