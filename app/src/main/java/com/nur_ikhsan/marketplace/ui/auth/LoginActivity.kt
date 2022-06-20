package com.nur_ikhsan.marketplace.ui.auth


import android.os.Bundle
import com.inyongtisto.myhelper.extension.*
import com.nur_ikhsan.marketplace.ui.Navigation.NavigationActivity
import com.nur_ikhsan.marketplace.core.data.source.remote.network.State
import com.nur_ikhsan.marketplace.core.data.source.remote.request.LoginRequest
import com.nur_ikhsan.marketplace.databinding.ActivityLoginBinding
import com.nur_ikhsan.marketplace.ui.base.MyActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : MyActivity() {

    private val viewModel : AuthViewModel by viewModel()

    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainButton()
        buttonReg()
    }


    private fun mainButton(){
        binding.btnMasuk.setOnClickListener {
            login()
        }

    }


    private fun buttonReg(){
        binding.btnReg.setOnClickListener {
            intentActivity(RegisterActivity::class.java)
        }

    }



    private fun login(){

        if (binding.edtEmail.isEmpty()) return
        if (binding.edtPassword.isEmpty()) return

        val body = LoginRequest(
            binding.edtEmail.text.toString(),
            binding.edtPassword.text.toString())

        viewModel.login(body).observe(this,{
            when (it.state){
               State.SUCCES ->{
                  progress.dismiss()
                   showToast("selamat datang " + it.data?.name)
                   pushActivity(NavigationActivity::class.java)
               }
                State.ERROR ->{
                   progress.dismiss()
                    toastError(it.message ?: "Error")
                }
                State.LOADING->{
                    progress.show()
                }
            }
        })

    }
}