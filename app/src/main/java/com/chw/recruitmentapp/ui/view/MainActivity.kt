package com.chw.recruitmentapp.ui.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.chw.recruitmentapp.data.api.Status
import com.chw.recruitmentapp.data.model.login.LoginRequest
import com.chw.recruitmentapp.databinding.ActivityMainBinding
import com.chw.recruitmentapp.ui.base.BaseActivity
import com.chw.recruitmentapp.ui.viewmodel.UserViewModel
import com.chw.recruitmentapp.utils.Utils
import com.chw.recruitmentapp.utils.Utils.Companion.negativeButton
import com.chw.recruitmentapp.utils.Utils.Companion.positiveButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var userViewModel: UserViewModel
    lateinit var dialog: AlertDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        dialog = Utils.setProgressDialog(this, "Loading...")
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
        userViewModel.loginRes.observe(this, Observer {
            when(it.status){
                Status.SUCCESS -> {
                    dialog.hide()
                    it.data.let {res->
                        Toast.makeText(this, res?.message, Toast.LENGTH_LONG).show()
                    }
                }
                Status.LOADING -> {
                    dialog.show()
                }
                Status.ERROR -> {
                    dialog.hide()
                }
            }
        })

        userViewModel.profileRes.observe(this, Observer {
            when(it.status){
                Status.SUCCESS -> {
                    dialog.hide()
                    it.data.let {res->

                    }
                }
                Status.LOADING -> {
                    dialog.show()
                }
                Status.ERROR -> {
                    dialog.hide()
                }
            }
            print(it)
        })

        binding.profileBtn.setOnClickListener {
            userViewModel.profile()
        }

        binding.logoutBtn.setOnClickListener {
            showProgressBar()
            userViewModel.doLogout()
        }
    }

    fun showProgressBar() {
        Utils.showAlertDialog(this) {
            setTitle("Recruitment App")
            setMessage("Alert Dialog message Content")
            positiveButton("Ok") {
                Toast.makeText(this@MainActivity, "Yes Clicked", Toast.LENGTH_LONG).show()
            }
            negativeButton("Cancel") {
                Toast.makeText(this@MainActivity, "cancel Clicked", Toast.LENGTH_LONG).show()
            }
        }
    }
}