package com.nszalas.myassistant.viewmodel


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.ListFragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nszalas.myassistant.R
import com.nszalas.myassistant.model.Student
import com.nszalas.myassistant.view.StudentsList
import com.nszalas.myassistant.view.StudentsListDirections
import kotlinx.android.synthetic.main.item_row_students_list.view.*
import res.layout.*

class StudentsListAdapter: RecyclerView.Adapter<StudentsListAdapter.MyViewHolder>() {

    private var studentList = emptyList<Student>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentsListAdapter.MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_row_students_list, parent,false))
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onBindViewHolder(holder: StudentsListAdapter.MyViewHolder, position: Int) {
        val currentItem = studentList[position]
        holder.itemView.ItemRowStudentsListName.text = currentItem.name
        holder.itemView.ItemRowStudentsListSurname.text = currentItem.surname

        holder.itemView.ItemRowStudentsListLayout.setOnClickListener {
            val action = StudentsListDirections.actionStudentsListToUpdateStudent(currentItem)
            holder.itemView.findNavController().navigate(action)
        }


    }

    fun setDataStudent(student: List<Student>){
        this.studentList = student
        notifyDataSetChanged()
    }
}