package com.practies.studentdataapp.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.practies.studentdataapp.R
import com.practies.studentdataapp.model.User
import com.practies.studentdataapp.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_updat.*
import kotlinx.android.synthetic.main.fragment_updat.view.updateBt
import kotlinx.android.synthetic.main.fragment_updat.view.updateAge
import kotlinx.android.synthetic.main.fragment_updat.view.updateCourse
import kotlinx.android.synthetic.main.fragment_updat.view.updateEmail
import kotlinx.android.synthetic.main.fragment_updat.view.updateName


class UpdatFragment : Fragment() {

          private  val args  by  navArgs<UpdatFragmentArgs >()

      private  lateinit var mUserViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_updat, container, false)

        mUserViewModel= ViewModelProvider(this).get(UserViewModel ::class.java)


        view.updateName.setText(args.currentUser.name)
          view.updateEmail.setText(args.currentUser.email)
        view.updateCourse.setText(args.currentUser.subject)
        view.updateAge.setText(args.currentUser.age.toString())

        view.updateBt.setOnClickListener{
                   updateItem()
        }
        //for  add delete menu
        setHasOptionsMenu(true)


        return  view
    }
         private  fun updateItem(){
             val name= updateName.text.toString()
             val email=updateEmail.text.toString()
             val course=updateCourse.text.toString()
             val  age= Integer.parseInt(updateAge.text.toString())

             if (inputCheck(name,updateAge.text,email,course)){

                 val updateUser = User(args.currentUser.id,name,email,age,course)
                 //to update the current user
                mUserViewModel.updatUser(updateUser)
                 Toast.makeText( requireContext(),"Student data is updated successfully", Toast.LENGTH_SHORT).show()
                //need for  all  fragmentes

                 findNavController().popBackStack()

             }else{
                 Toast.makeText( requireContext(),"Pleas fill  all fields",Toast.LENGTH_SHORT).show()
             }

         }
    private  fun  inputCheck(name:String, age: Editable, email:String, course :String) :Boolean{

        return  !(TextUtils.isEmpty(name) && TextUtils.isEmpty(email)&& TextUtils.isEmpty(course)&& age.isEmpty() )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId ==  R.id.delete){
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }
    private fun  deleteUser(){
        val bulider = AlertDialog.Builder(requireContext())
        bulider.setPositiveButton("yes"){ _,_->
            mUserViewModel.deleteUser(args.currentUser)
            Toast.makeText (requireContext(),"this item is  removed successfully",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updatFragment_to_listFragment)
        }
        bulider.setNegativeButton("No"){_,_->

        }
        bulider.setTitle("Delete  ${args.currentUser.name}?" )
        bulider.setMessage("Are you sure want to delete  ${args.currentUser.name} ?")
        bulider.create().show()
    }


}