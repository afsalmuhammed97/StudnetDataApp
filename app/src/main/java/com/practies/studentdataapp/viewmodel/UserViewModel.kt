package com.practies.studentdataapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.practies.studentdataapp.data.UserDatabase
import com.practies.studentdataapp.repository.UserRepository
import com.practies.studentdataapp.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application):AndroidViewModel(application) {
   val  readAllData  :LiveData<List<User>>
    private  val repository : UserRepository
       //fist exicuted  when viewModel  called
    init {
        val userDao = UserDatabase.getDatabase(application).userDao()

        repository= UserRepository(userDao)
        readAllData= repository.readAllData
    }
    fun  addUser(user: User){
        //Dispatches .Io  for  runnig  this  section  in background thread(worker thread)
        viewModelScope.launch (Dispatchers.IO){
            repository.addUser(user)
        }
    }
    fun updatUser(user: User){
        viewModelScope.launch (Dispatchers.IO){
            repository.updatUser(user)
        }
    }

    fun deleteUser(user: User){
        viewModelScope.launch (Dispatchers.IO ){
            repository.deleteUser(user)
        }
    }

//    fun  deleteAllUser(user: User){
//        viewModelScope.launch (Dispatchers.IO){
//            repository.deleteAllUser()
//        }
//    }

    fun deleteAllUser() {
       viewModelScope.launch  (Dispatchers.IO){
           repository.deleteAllUser()
       }
    }


}