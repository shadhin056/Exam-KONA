package com.example.examshadhin.view.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.examshadhin.R
import com.example.examshadhin.view.model.RegistrationModel
import com.example.examshadhin.view.util.Constant
import com.example.examshadhin.view.viewmodel.CustomerViewModel
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity : AppCompatActivity() {

    private lateinit var customerViewModel: CustomerViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        customerViewModel = ViewModelProvider(this).get(CustomerViewModel::class.java)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        spinnerLoad()
        buttonAction()
        observeViewModel()
    }
    fun observeViewModel() {
        customerViewModel.error_response.observe(this) {
            it?.let {
                if (it) {
                    Toast.makeText(this, "User Already exits or error", Toast.LENGTH_LONG).show()
                }
            }
        }
        customerViewModel.customerRegistrationResponse.observe(this) {

            it?.let {
                Toast.makeText(this, "User-Created and ID : "+it.id, Toast.LENGTH_LONG).show()
                if(Constant.logCheck){
                    Log.d(RegistrationActivity::class.java.getSimpleName(),it.id.toString())
                }
            }
        }
    }
    private fun buttonAction() {
        btnSubmit.setOnClickListener {
            if (edtCustomerName.text.toString() == "") {
              Toast.makeText(this,"Enter Name",Toast.LENGTH_LONG).show()
            } else if (edtCustomerEmail.text.toString() == "") {
                Toast.makeText(this,"Enter Email",Toast.LENGTH_LONG).show()
            } else{
                reqForUserRegistration()
            }
        }
    }
    private fun reqForUserRegistration() {
        val model = RegistrationModel()
        model.gender = spGender.getSelectedItem().toString()
        model.email = edtCustomerEmail.text.toString()
        model.name = edtCustomerName.text.toString()
        model.status = spStatus.getSelectedItem().toString()
        this.let { it1 -> customerViewModel.reqForUserRegistration(model) }
    }

    private fun spinnerLoad() {
        //postal gender
        val adapterForGender = ArrayAdapter.createFromResource(
            this@RegistrationActivity,
            R.array.gender, android.R.layout.simple_spinner_item
        )
        adapterForGender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spGender.setAdapter(adapterForGender)

        //post status
        val adapterForStatus = ArrayAdapter.createFromResource(
            this@RegistrationActivity,
            R.array.status, android.R.layout.simple_spinner_item
        )
        adapterForStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spStatus.setAdapter(adapterForStatus)
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