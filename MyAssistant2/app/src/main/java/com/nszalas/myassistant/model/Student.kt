package com.nszalas.myassistant.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "students_table")
data class Student(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var name: String,
    var surname: String
): Parcelable