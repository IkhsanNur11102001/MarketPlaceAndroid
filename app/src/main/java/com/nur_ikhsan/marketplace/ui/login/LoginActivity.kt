package com.nur_ikhsan.marketplace.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.inyongtisto.myhelper.extension.*
import com.nur_ikhsan.marketplace.NavigationActivity
import com.nur_ikhsan.marketplace.core.data.source.remote.network.State
import com.nur_ikhsan.marketplace.core.data.source.remote.request.LoginRequest
import com.nur_ikhsan.marketplace.databinding.ActivityLoginBinding
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
                   dismisLoading()
                   showToast("selamat datang " + it.data?.name)
                   pushActivity(NavigationActivity::class.java)

               }
                State.ERROR ->{
                    dismisLoading()
                    toastError(it.message ?: "Error")

                }
                State.LOADING ->{
                    showLoading()

                }
            }
        })

    }
}