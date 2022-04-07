package com.practies.studentdataapp.repository

import androidx.lifecycle.LiveData
import com.practies.studentdataapp.data.UserDao
import com.practies.studentdataapp.model.User

class UserRepository(private val userDao: UserDao) {

     val readAllData :LiveData<List<User>> = userDao.readAllData()


  suspend  fun addUser(user: User){
        userDao.addUser(user)
    }

    suspend fun  updatUser(user: User){
        userDao.updatUser(user)
    }
    suspend fun  deleteUser(user: User) {
        userDao.deleteUser(user)
    }
    suspend fun  deleteAllUser() {
        userDao.deleteAllUser()
    }

}