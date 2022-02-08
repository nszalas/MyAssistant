package com.nszalas.myassistant.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.nszalas.myassistant.R
import com.nszalas.myassistant.model.Grade
import com.nszalas.myassistant.view.GradesListDirections
import kotlinx.android.synthetic.main.item_row_grades_list.view.*


class GradesListAdapter: RecyclerView.Adapter<GradesListAdapter.MyViewHolder>() {

    private var gradeList = emptyList<Grade>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_row_grades_list, parent,false))
    }

    override fun getItemCount(): Int {
        return gradeList.size
    }

    override fun onBindViewHolder(holder: GradesListAdapter.MyViewHolder, position: Int) {
        val currentItem = gradeList[position]
        holder.itemView.ItemRowGradesListGradeName.text = currentItem.name_grade
        holder.itemView.ItemRowGradesListGradeNote.text = currentItem.grade_note
        holder.itemView.ItemRowGradesListGradeStudent.text = currentItem.grade_student
        holder.itemView.ItemRowGradesListGradeSubject.text = currentItem.grade_subject

        holder.itemView.ItemRowGradesListLayout.setOnClickListener {
            val action = GradesListDirections.actionGradesListToUpdateGrade(currentItem)
            holder.itemView.findNavController().navigate(action)

        }


    }

    fun setDataGrade(grade: List<Grade>){
        this.gradeList = grade
        notifyDataSetChanged()
    }
}