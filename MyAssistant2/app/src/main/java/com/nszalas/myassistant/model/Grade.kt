package com.nszalas.myassistant.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "grades_table")
data class Grade(
    @PrimaryKey(autoGenerate = true)
    var id_grade: Int,
    var name_grade: String,
    var grade_note: String,
    var grade_student: String,
    var grade_subject: String

): Parcelable