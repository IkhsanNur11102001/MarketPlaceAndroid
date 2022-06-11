package com.nur_ikhsan.marketplace.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import com.nur_ikhsan.marketplace.R
import com.nur_ikhsan.marketplace.Util.Prefs
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
        viewModel.text.observe(this, {
            binding.edtEmail.setText(it)
        })
        binding.btnMasuk.setOnClickListener {

            val body = LoginRequest(
                binding.edtEmail.text.toString(),
                binding.edtPassword.text.toString())

            viewModel.login(body).observe(this, {

            })
        }

    }
}