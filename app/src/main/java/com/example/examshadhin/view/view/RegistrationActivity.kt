package com.example.examshadhin.view.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import com.example.examshadhin.R
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        spinnerLoad()
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