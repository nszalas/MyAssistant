package com.nszalas.myassistant.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MyAssistantDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addStudent(student: Student)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateStudent(student: Student)

    @Delete
    suspend fun deleteStudent(student: Student)

    @Query("DELETE FROM students_table")
    suspend fun deleteAllStudents()

    @Query("SELECT * FROM students_table ORDER BY id ASC")
    fun readAllStudents(): LiveData<List<Student>>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addSubject(subject: Subject)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateSubject(subject: Subject)

    @Delete
    suspend fun deleteSubject(subject: Subject)

    @Query("DELETE FROM subjects_table")
    suspend fun deleteAllSubjects()

    @Query("SELECT * FROM subjects_table ORDER BY id_subject ASC")
    fun readAllSubjects(): LiveData<List<Subject>>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addGrade(grade: Grade)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateGrade(grade: Grade)

    @Delete
    suspend fun deleteGrade(grade: Grade)

    @Query("DELETE FROM grades_table")
    suspend fun deleteAllGrades()

    @Query("SELECT * FROM grades_table ORDER BY id_grade ASC")
    fun readAllGrades(): LiveData<List<Grade>>
}