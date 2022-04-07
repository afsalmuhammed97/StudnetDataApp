package com.practies.studentdataapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.practies.studentdataapp.model.User

@Database(entities = [User::class],version = 1,exportSchema = false)
abstract  class UserDatabase :RoomDatabase(){


    abstract fun  userDao (): UserDao

    companion object{
        @Volatile  //to  visible  to other thread
        private var INSTANCE : UserDatabase ?=null

     fun  getDatabase(context: Context):UserDatabase{

         val temInstance =  INSTANCE
         if (temInstance !=null){
             return  temInstance
         }
         synchronized(this){
             val instance = Room.databaseBuilder(
                 context.applicationContext,
                 UserDatabase::class.java,
                 "user_database"
             ).build()
             INSTANCE=instance
             return instance
         }
     }
    }

}