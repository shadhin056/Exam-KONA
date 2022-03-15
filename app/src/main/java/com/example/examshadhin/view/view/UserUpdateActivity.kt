package com.example.examshadhin.view.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.examshadhin.R
import com.example.examshadhin.view.model.RegistrationModel
import com.example.examshadhin.view.util.Constant
import com.example.examshadhin.view.util.CustomerInfoAdapter
import com.example.examshadhin.view.util.GlobalVariable
import com.example.examshadhin.view.viewmodel.CustomerViewModel
import kotlinx.android.synthetic.main.activity_registration.*
import kotlinx.android.synthetic.main.activity_user_update.*
import kotlinx.android.synthetic.main.activity_user_update.edtCustomerEmail
import kotlinx.android.synthetic.main.activity_user_update.edtCustomerName
import kotlinx.android.synthetic.main.activity_user_update.spGender
import kotlinx.android.synthetic.main.activity_user_update.spStatus

class UserUpdateActivity : AppCompatActivity() {
    private lateinit var globalVariable: GlobalVariable
    private lateinit var customerViewModel: CustomerViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_update)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        globalVariable = this.applicationContext as GlobalVariable
        customerViewModel = ViewModelProvider(this).get(CustomerViewModel::class.java)
        spinnerLoad()
        setValue()
        buttonAction()
        observeViewModel()
    }
    fun observeViewModel() {
        customerViewModel.error_response.observe(this) {
            it?.let {
                if (it) {
                    Toast.makeText(this, "error occurred", Toast.LENGTH_LONG).show()
                }
            }
        }
        customerViewModel.customerRegistrationResponse.observe(this) {

            it?.let {
                Toast.makeText(this, "User-Updated and ID : "+it.id, Toast.LENGTH_LONG).show()
                if(Constant.logCheck){
                    Log.d(RegistrationActivity::class.java.getSimpleName(),it.id.toString())
                }
            }
        }
    }
    private fun buttonAction() {
        btnUpdate.setOnClickListener {
            if (edtCustomerName.text.toString() == "") {
                Toast.makeText(this,"Enter Name", Toast.LENGTH_LONG).show()
            } else if (edtCustomerEmail.text.toString() == "") {
                Toast.makeText(this,"Enter Email", Toast.LENGTH_LONG).show()
            } else{
                updateUserInfo()
            }
        }

    }

    private fun updateUserInfo() {
        val model = RegistrationModel()
        model.id = globalVariable.id
        model.gender = spGender.getSelectedItem().toString()
        model.email = edtCustomerEmail.text.toString()
        model.name = edtCustomerName.text.toString()
        model.status = spStatus.getSelectedItem().toString()
        this.let { it1 -> customerViewModel.updateUserInfo(model) }
    }
    private fun spinnerLoad() {
        //postal gender
        val adapterForGender = ArrayAdapter.createFromResource(
            this@UserUpdateActivity,
            R.array.gender, android.R.layout.simple_spinner_item
        )
        adapterForGender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spGender.setAdapter(adapterForGender)

        //post status
        val adapterForStatus = ArrayAdapter.createFromResource(
            this@UserUpdateActivity,
            R.array.status, android.R.layout.simple_spinner_item
        )
        adapterForStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spStatus.setAdapter(adapterForStatus)
    }
    private fun setValue() {
        edtCustomerEmail.setText(globalVariable.email)
        edtCustomerName.setText(globalVariable.name)
        if(globalVariable.gender=="male"){
            spGender.setSelection(0);
        }else{
            spGender.setSelection(1);
        }
        if(globalVariable.status=="active"){
            spStatus.setSelection(0);
        }else{
            spStatus.setSelection(1);
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                val intent: Intent = Intent(
                    this,
                    ViewCustomerListActivity::class.java
                )
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}