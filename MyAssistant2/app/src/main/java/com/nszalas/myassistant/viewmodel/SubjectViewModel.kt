package com.nszalas.myassistant.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.nszalas.myassistant.model.MyAssistantDAO
import com.nszalas.myassistant.model.MyAssistantDatabase
import com.nszalas.myassistant.model.Subject
import com.nszalas.myassistant.model.SubjectRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SubjectViewModel(application: Application):AndroidViewModel(application) {

    val readAdllSubjects: LiveData<List<Subject>>
    private val repository: SubjectRepository

    init {
        val myAssistantDAO = MyAssistantDatabase.getDatabase(application).MyAssistantDAO()
        repository = SubjectRepository(myAssistantDAO)
        readAdllSubjects = repository.readAllSubjects
    }

    fun addSubject(subject: Subject){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addSubject(subject)
        }
    }

    fun updateSubject(subject: Subject){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateSubjects(subject)
        }
    }

    fun deleteSubject(subject: Subject){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteSubject(subject)
        }
    }

    fun deleteAllSubjects(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllSubjects()
        }
    }
}