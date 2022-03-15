package com.example.examshadhin.view.util

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.examshadhin.R
import com.example.examshadhin.view.model.CustomerListResponse
import com.example.examshadhin.view.view.RegistrationActivity
import com.example.examshadhin.view.view.UserUpdateActivity
import com.example.examshadhin.view.view.ViewCustomerListActivity
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.row_user.view.*

class CustomerInfoAdapter(
    private val activity: Activity,
    private val mainRequestList: List<CustomerListResponse>,
    private val listener: ViewCustomerListActivity
) :
    RecyclerView.Adapter<CustomerInfoAdapter.MyViewHolder>() {

    private lateinit var globalVariable: GlobalVariable
    private val mainList: List<CustomerListResponse>
    private var listFiltered: List<CustomerListResponse>
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomerInfoAdapter.MyViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_user, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: CustomerInfoAdapter.MyViewHolder,
        position: Int
    ) {
        val currentItem = mainList[position]
        holder.itemView.txtGender.setText(currentItem.gender)
        holder.itemView.txtName.setText(currentItem.name)
        holder.itemView.txtStatus.setText(currentItem.status)
        holder.itemView.txtemail.setText(currentItem.email)
        holder.itemView.llClick.setOnClickListener {
            globalVariable.email=currentItem.email
            globalVariable.status=currentItem.status
            globalVariable.gender=currentItem.gender
            globalVariable.name=currentItem.name
            globalVariable.id=currentItem.id
            val intent = Intent(activity, UserUpdateActivity::class.java)
            activity.startActivity(intent);
        }
    }
    override fun getItemCount(): Int {
        return try {
            mainList.size
        } catch (e: Exception) {
            0
        }
    }
    init {
        this.mainList = mainRequestList
        listFiltered = mainList
        globalVariable = activity.applicationContext as GlobalVariable
    } 
}