package com.practies.studentdataapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import javax.security.auth.Subject

@Parcelize
@Entity (tableName = "user_table")
data class User (
    @PrimaryKey(autoGenerate = true)
     val  id:Int,
     val  name:String,
     val email:String,
     val age:Int,
     val subject:String
         ):Parcelable