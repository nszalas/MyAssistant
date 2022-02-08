package com.nszalas.myassistant.model

import androidx.lifecycle.LiveData


class StudentRepository(private val myAssistantDao: MyAssistantDAO) {

        val readAllStudents: LiveData<List<Student>> = myAssistantDao.readAllStudents()


    suspend fun addStudent(student: Student){
        myAssistantDao.addStudent(student)
    }

    suspend fun updateStudent(student: Student){
        myAssistantDao.updateStudent(student)
    }

    suspend fun deleteStudent(student: Student){
        myAssistantDao.deleteStudent(student)
    }

    suspend fun deleteAllStudents(){
        myAssistantDao.deleteAllStudents()
    }
}