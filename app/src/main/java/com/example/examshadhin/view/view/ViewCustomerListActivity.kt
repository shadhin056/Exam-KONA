package com.example.examshadhin.view.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.examshadhin.R
import com.example.examshadhin.view.util.Constant
import com.example.examshadhin.view.util.CustomerInfoAdapter
import com.example.examshadhin.view.viewmodel.CustomerViewModel

class ViewCustomerListActivity : AppCompatActivity() {
    private lateinit var customerViewModel: CustomerViewModel
    private var rvUserList: RecyclerView? = null
    private var mAdapter: CustomerInfoAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_customer_list)
        customerViewModel = ViewModelProvider(this).get(CustomerViewModel::class.java)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        rvUserList=findViewById(R.id.rv_users);
        viewList()
        observeViewModel()
        val mLayoutManager = LinearLayoutManager(applicationContext)
        rvUserList!!.setLayoutManager(mLayoutManager)
        rvUserList!!.setItemAnimator(DefaultItemAnimator())
        Toast.makeText(this, "Information fetching", Toast.LENGTH_SHORT).show()
    }
    @SuppressLint("NotifyDataSetChanged")
    fun observeViewModel() {
        customerViewModel.error_response.observe(this) {
            it?.let {
                if (it) {
                    Toast.makeText(this, "error occurred", Toast.LENGTH_LONG).show()
                }
            }
        }
        customerViewModel.customerListResponse.observe(this) {

            it?.let {

                if(Constant.logCheck){
                    Log.d(ViewCustomerListActivity::class.java.getSimpleName(),it.toString())
                }
                mAdapter = CustomerInfoAdapter(this, it, this)
                rvUserList!!.adapter = mAdapter
                mAdapter?.notifyDataSetChanged();
            }
        }
    }
    private fun viewList() {
        this.let { it1 -> customerViewModel.getCustomerList() }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                val intent: Intent = Intent(
                    this,
                    HomeActivity::class.java
                )
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}