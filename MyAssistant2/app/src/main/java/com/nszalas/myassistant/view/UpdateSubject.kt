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
import com.nszalas.myassistant.model.Subject
import com.nszalas.myassistant.viewmodel.SubjectViewModel
import kotlinx.android.synthetic.main.fragment_update_subject.*
import kotlinx.android.synthetic.main.fragment_update_subject.view.*


class UpdateSubject : Fragment() {

    private val args by navArgs<UpdateSubjectArgs>()

    private lateinit var mSubjectViewModel: SubjectViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update_subject, container, false)

        mSubjectViewModel = ViewModelProvider(this).get(SubjectViewModel::class.java)

        view.UpdateSubjectSubjectsNameTextView.setText(args.currentSubject.name_subject)
        view.UpdateSubjectWeekDayTextView.setText(args.currentSubject.week_day)
        view.UpdateSubjectStartHourTextView.setText(args.currentSubject.start_hour)
        view.UpdateSubjectEndHourTextView.setText(args.currentSubject.end_hour)

        view.UpdateSubjectUpdateSubjectButton.setOnClickListener {
            updateSubject()
        }

        view.UpdateSubjectDeleteSubjectImageButton.setOnClickListener{
            deleteSubject()
        }

        return view
    }

    private fun deleteSubject() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Tak"){_,_->
            mSubjectViewModel.deleteSubject(args.currentSubject)
            Toast.makeText(requireContext(), "Przedmiot usunięty!", Toast.LENGTH_LONG).show()
            activity?.onBackPressed()
        }
        builder.setNegativeButton("Nie"){_,_ ->}
        builder.setTitle("Usuń ${args.currentSubject.name_subject}")
        builder.setMessage("Czy na pewno chcesz usunąć ${args.currentSubject.name_subject}?")
        builder.create().show()
    }

    private fun updateSubject() {
        val subjectName = UpdateSubjectSubjectsNameTextView.text.toString()
        val subjectWeekDay = UpdateSubjectWeekDayTextView.text.toString()
        val subjectStartHour = UpdateSubjectStartHourTextView.text.toString()
        val subjectEndHour = UpdateSubjectEndHourTextView.text.toString()

        if(inputCheckSubjects(subjectName,subjectWeekDay,subjectStartHour,subjectEndHour)){
            val updatedSubject = Subject(args.currentSubject.id_subject,subjectName,subjectWeekDay,subjectStartHour,subjectEndHour)

            mSubjectViewModel.updateSubject(updatedSubject)
            Toast.makeText(requireContext(),"Przedmiot został zmodyfikowany!", Toast.LENGTH_LONG).show()

            activity?.onBackPressed()
        }

    }

    private fun inputCheckSubjects(subjectName: String, subjectWeekDay: String,
                                   subjectStartHour: String, subjectEndHour: String)  : Boolean{
        return !(TextUtils.isEmpty(subjectName) && TextUtils.isEmpty(subjectWeekDay) && TextUtils.isEmpty(subjectStartHour)&& TextUtils.isEmpty(subjectEndHour))
    }


}