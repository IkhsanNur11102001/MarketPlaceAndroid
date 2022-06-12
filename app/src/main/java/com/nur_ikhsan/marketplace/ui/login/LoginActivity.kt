package com.nur_ikhsan.marketplace.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.inyongtisto.myhelper.extension.*
import com.nur_ikhsan.marketplace.R
import com.nur_ikhsan.marketplace.Util.Prefs
import com.nur_ikhsan.marketplace.core.data.source.remote.network.State
import com.nur_ikhsan.marketplace.core.data.source.remote.request.LoginRequest
import com.nur_ikhsan.marketplace.databinding.ActivityLoginBinding
import com.nur_ikhsan.marketplace.databinding.FragmentDashboardBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val viewModel : LoginViewModel by viewModel()

    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setData()
    }

    fun setData() {
        binding.btnMasuk.setOnClickListener {
            login()

        }

    }

    private fun login(){

        if (binding.edtEmail.isEmpty()) return
        if (binding.edtPassword.isEmpty()) return

        val body = LoginRequest(
            binding.edtEmail.text.toString(),
            binding.edtPassword.text.toString())

        viewModel.login(body).observe(this, {

            when (it.state){
               State.SUCCES ->{
                   binding.progresbar.visibility = View.GONE
                   showToast("selamat datang " + it.data?.name)

               }
                State.ERROR ->{
                    binding.progresbar.visibility = View.GONE
                    toastError(it.message ?: "Error")

                }
                State.LOADING ->{
                    binding.progresbar.visibility = View.VISIBLE

                }
            }
        })

    }
}