package com.practies.studentdataapp.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.practies.studentdataapp.R
import com.practies.studentdataapp.model.User
import com.practies.studentdataapp.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*


class AddFragment : Fragment() {
    private  lateinit var  mUserViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_add, container, false)

        mUserViewModel= ViewModelProvider(this).get(UserViewModel ::class.java)


         view.updateBt.setOnClickListener{
                insertDataToDataBase()
         }

        return view
    }
    private  fun  insertDataToDataBase(){
             val name=updateName.text.toString()
              val  age = updateAge.text
              val email= updateEmail.text.toString()
              val course=updateCourse.text.toString()
         if (inputCheck(name,age,email,course)){
             //creating user obj

             val user = User(0,name,email,Integer.parseInt(age.toString()),course)

             //Adding data  to database
             mUserViewModel.addUser(user)
             Toast.makeText( requireContext(),"Student data is added successfully",Toast.LENGTH_SHORT).show()
                 // for  navigating to  back
              findNavController().popBackStack()

         }else{
             Toast.makeText( requireContext(),"Pleas fill  all fields",Toast.LENGTH_SHORT).show()
         }


    }
    private  fun  inputCheck(name:String,age:Editable, email:String, course :String) :Boolean{

        return  !(TextUtils.isEmpty(name) &&TextUtils.isEmpty(email)&&TextUtils.isEmpty(course)&& age.isEmpty() )
    }
            }

