package com.nszalas.myassistant.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.nszalas.myassistant.R
import com.nszalas.myassistant.model.Subject
import com.nszalas.myassistant.view.SubjectsListDirections
import kotlinx.android.synthetic.main.item_row_subjects_list.view.*

class SubjectsListAdapter: RecyclerView.Adapter<SubjectsListAdapter.MyViewHolder>() {

    private var subjectList = emptyList<Subject>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_row_subjects_list, parent, false))
    }

    override fun getItemCount(): Int {
        return subjectList.size
    }

    override fun onBindViewHolder(holder: SubjectsListAdapter.MyViewHolder, position: Int) {
        val currentSubject = subjectList[position]
        holder.itemView.ItemRowSubjectsListSubjectName.text = currentSubject.name_subject
        holder.itemView.ItemRowSubjectsListWeekDay.text = currentSubject.week_day
        holder.itemView.ItemRowSubjectsListStartHour.text = currentSubject.start_hour
        holder.itemView.ItemRowSubjectsListEndHour.text = currentSubject.end_hour

        holder.itemView.ItemRowSubjectsListLayout.setOnClickListener {
            val action = SubjectsListDirections.actionSubjectsListToUpdateSubject(currentSubject)
            holder.itemView.findNavController().navigate(action)
        }
    }


    fun setDataSubject(subject: List<Subject>){
        this.subjectList = subject
        notifyDataSetChanged()
    }
}