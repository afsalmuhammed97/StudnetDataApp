package com.practies.studentdataapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.practies.studentdataapp.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
   suspend fun  addUser(user: User)

   @Update
   suspend fun updatUser(user: User)

   @Delete
   suspend fun  deleteUser(user: User)

   @Query("DELETE  FROM  user_table ")
   suspend fun  deleteAllUser()


   @Query("SELECT * FROM user_table ORDER BY  id ASC")

    fun   readAllData():LiveData<List<User>>

}


