package com.practies.studentdataapp.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.practies.studentdataapp.R
import com.practies.studentdataapp.model.User
import kotlinx.android.synthetic.main.custom_raw_layout.view.*

class ListAdapter :RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var userList = emptyList<User>()


     class MyViewHolder( itemView: View):RecyclerView.ViewHolder(itemView){


     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return  MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_raw_layout,parent,false))
    }
    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem= userList[position]
       holder.itemView.id_text.text=currentItem.id.toString()
        holder.itemView.name.text =currentItem.name
        holder.itemView.Email.text=currentItem.email
        holder.itemView.age.text=currentItem.age.toString()
        holder.itemView.course.text= currentItem.subject
        holder.itemView.rowLayout.setOnClickListener{
            val action=ListFragmentDirections.actionListFragmentToUpdatFragment(currentItem)

            holder.itemView.findNavController().navigate(action)
        }
    }



            fun  setData(user: List<User>){
                this.userList =user
                notifyDataSetChanged()

            }

}