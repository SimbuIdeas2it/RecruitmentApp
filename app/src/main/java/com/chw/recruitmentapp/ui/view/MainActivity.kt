package com.chw.recruitmentapp.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.chw.recruitmentapp.data.model.login.LoginRequest
import com.chw.recruitmentapp.databinding.ActivityMainBinding
import com.chw.recruitmentapp.ui.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        binding.loginBtn.setOnClickListener {
            val emailStr= binding.emailtxt.text.toString()
            val passwordStr = binding.passwordtxt.text.toString()
            if(emailStr.isNotEmpty() && passwordStr.isNotEmpty()) {
                val re = LoginRequest(emailStr, passwordStr, "")
                userViewModel.login(re)
            }
            else {
                Toast.makeText(this, "Email and password are required", Toast.LENGTH_LONG).show()
            }
        }
        userViewModel.returnedVal.observe(this, Observer {
            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
        })
    }
}