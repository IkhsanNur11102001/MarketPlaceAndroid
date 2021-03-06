package com.nur_ikhsan.marketplace.ui.auth

import android.os.Bundle
import com.inyongtisto.myhelper.extension.*
import com.nur_ikhsan.marketplace.core.data.source.remote.network.State
import com.nur_ikhsan.marketplace.core.data.source.remote.request.RegisterRequest
import com.nur_ikhsan.marketplace.databinding.ActivityRegisterBinding
import com.nur_ikhsan.marketplace.ui.base.MyActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : MyActivity() {
    private val viewModel : AuthViewModel by viewModel()
    private var _binding: ActivityRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setData()
    }

    fun setData() {
        binding.btnDaftar.setOnClickListener {
            regsiter()
        }
    }
    private fun regsiter(){

        if (binding.edtUsername.isEmpty()) return
        if (binding.edtEmailReg.isEmpty()) return
        if (binding.edtPhone.isEmpty()) return
        if (binding.edtPasswordReg.isEmpty()) return

        val body = RegisterRequest(
            binding.edtUsername.text.toString(),
            binding.edtEmailReg.text.toString(),
            binding.edtPhone.text.toString(),
            binding.edtPasswordReg.text.toString())

        viewModel.regsiter(body).observe(this, {

            when (it.state){
               State.SUCCES ->{
                 progress.dismiss()
                   showToast("Registrasi berhasil, selamata datang " +it.data?.name)
                   pushActivity(LoginActivity::class.java)

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