package com.example.examshadhin.view.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.examshadhin.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        buttonAction()
    }
    private fun buttonAction() {
        btnRegCustomer.setOnClickListener {
            val intent = Intent(this@HomeActivity, RegistrationActivity::class.java)
            startActivity(intent);
        }
    }
}