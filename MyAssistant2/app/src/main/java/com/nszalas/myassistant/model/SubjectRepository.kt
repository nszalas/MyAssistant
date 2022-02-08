package com.nszalas.myassistant.model

import androidx.lifecycle.LiveData

class SubjectRepository(private val myAssistantDAO: MyAssistantDAO) {

    val readAllSubjects: LiveData<List<Subject>> = myAssistantDAO.readAllSubjects()

    suspend fun addSubject(subject: Subject){
        myAssistantDAO.addSubject(subject)
    }

    suspend fun updateSubjects(subject: Subject){
        myAssistantDAO.updateSubject(subject)
    }

    suspend fun deleteSubject(subject: Subject){
        myAssistantDAO.deleteSubject(subject)
    }

    suspend fun deleteAllSubjects(){
        myAssistantDAO.deleteAllSubjects()
    }
}