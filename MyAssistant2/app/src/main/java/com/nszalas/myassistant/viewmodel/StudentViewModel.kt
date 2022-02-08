package com.nszalas.myassistant.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.nszalas.myassistant.model.MyAssistantDAO
import com.nszalas.myassistant.model.MyAssistantDatabase
import com.nszalas.myassistant.model.Student
import com.nszalas.myassistant.model.StudentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StudentViewModel(application: Application): AndroidViewModel(application) {


    val readAllStudents: LiveData<List<Student>>
    private val repository: StudentRepository

    init{
        val myAssistantDAO = MyAssistantDatabase.getDatabase(application).MyAssistantDAO()
        repository = StudentRepository(myAssistantDAO)
        readAllStudents = repository.readAllStudents
    }

    fun addStudent(student: Student){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addStudent(student)
        }
    }

    fun updateStudent(student: Student){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateStudent(student)
        }
    }

    fun deleteStudent(student: Student){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteStudent(student)
        }
    }

    fun deleteAllStudents(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllStudents()
        }
    }
}