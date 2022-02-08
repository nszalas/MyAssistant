package com.nszalas.myassistant.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "subjects_table")
data class Subject(
    @PrimaryKey(autoGenerate = true)
    var id_subject: Int,
    var name_subject: String,
    var week_day: String,
    var start_hour: String,
    var end_hour: String
) :Parcelable