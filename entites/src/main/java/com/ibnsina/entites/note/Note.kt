package com.ibnsina.entites.note

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
@Entity
@Parcelize
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val title: String,
    val body: String,
    val updated_at: String,//simulate laravel default fields
    val created_at: String
) : Parcelable