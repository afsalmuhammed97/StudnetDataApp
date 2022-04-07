package com.practies.studentdataapp.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.practies.studentdataapp.R
import com.practies.studentdataapp.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*


class ListFragment : Fragment() {
    private  lateinit var  mUserViewModel :UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_list, container, false)
    //recycler view
        val adapter= ListAdapter()
        val recyclerView=view.recyclerView
        recyclerView.adapter =adapter

        recyclerView.layoutManager =LinearLayoutManager(requireContext())

        //user view Model

           mUserViewModel= ViewModelProvider(this).get(UserViewModel::class.java)
           mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
               adapter.setData( user )
           })

        view.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
        //to add option menu
        setHasOptionsMenu(true)

        return  view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
       inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.delete){


            deletAllUser()

        }
        return super.onOptionsItemSelected(item)
    }

    private fun deletAllUser(){

        val bulider = AlertDialog.Builder(requireContext())
        bulider.setPositiveButton("yes"){ _,_->
            mUserViewModel.deleteAllUser()
            Toast.makeText (requireContext(),"all data deleted ", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updatFragment_to_listFragment)
        }
        bulider.setNegativeButton("No"){_,_->

        }
        bulider.setTitle("Delete all ?" )
        bulider.setMessage("Are you sure want to delete  ?")
        bulider.create().show()

    }


    }
