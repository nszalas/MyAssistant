package com.nszalas.myassistant.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.nszalas.myassistant.model.Grade
import com.nszalas.myassistant.model.GradeRepository
import com.nszalas.myassistant.model.MyAssistantDAO
import com.nszalas.myassistant.model.MyAssistantDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class GradeViewModel(application: Application):AndroidViewModel(application) {

    val readAllGrades: LiveData<List<Grade>>
    private val repository: GradeRepository

    init {
        val myAssistantDAO = MyAssistantDatabase.getDatabase(application).MyAssistantDAO()
        repository = GradeRepository(myAssistantDAO)
        readAllGrades = repository.readAllGrades

    }

    fun addGrade(grade: Grade){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addGrade(grade)
        }
    }

    fun updateGrade(grade: Grade){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateGrade(grade)
        }
    }

    fun deleteGrade(grade: Grade){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteGrade(grade)
        }
    }

    fun deleteAllGrades(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllGrades()
        }
    }

}