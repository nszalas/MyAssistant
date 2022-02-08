package com.nszalas.myassistant.model

import androidx.lifecycle.LiveData


class GradeRepository(private val myAssistantDAO: MyAssistantDAO) {

    val readAllGrades: LiveData<List<Grade>> = myAssistantDAO.readAllGrades()

    suspend fun addGrade(grade: Grade){
        myAssistantDAO.addGrade(grade)
    }

    suspend fun updateGrade(grade: Grade){
        myAssistantDAO.updateGrade(grade)
    }

    suspend fun deleteGrade(grade: Grade){
        myAssistantDAO.deleteGrade(grade)
    }

    suspend fun deleteAllGrades(){
        myAssistantDAO.deleteAllGrades()
    }
}