package com.nur_ikhsan.marketplace.ui.updateProfile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.inyongtisto.myhelper.extension.*
import com.nur_ikhsan.marketplace.Util.Prefs
import com.nur_ikhsan.marketplace.core.data.source.remote.network.State
import com.nur_ikhsan.marketplace.core.data.source.remote.request.RegisterRequest
import com.nur_ikhsan.marketplace.core.data.source.remote.request.UpdateProfileRequest
import com.nur_ikhsan.marketplace.databinding.ActivityRegisterBinding
import com.nur_ikhsan.marketplace.databinding.UpdateProfileBinding
import com.nur_ikhsan.marketplace.ui.auth.AuthViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class UpdateProfile : AppCompatActivity() {
    private val viewModel: AuthViewModel by viewModel()
    private var _binding: UpdateProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = UpdateProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolbar(binding.toolbar,"Update Profile")

        mainButon()
        setData()
    }

    private fun setData(){
        val user = Prefs.getUser()
        if (user!= null){
            binding.apply {
                edtUsername.setText(user.name)
                edtEmailReg.setText(user.email)
                edtPhone.setText(user.phone)
            }
        }
    }

    fun mainButon() {
        binding.btnSimpan.setOnClickListener {
            regsiter()
        }
    }

    private fun regsiter() {

        if (binding.edtUsername.isEmpty()) return
        if (binding.edtEmailReg.isEmpty()) return
        if (binding.edtPhone.isEmpty()) return

        val body = UpdateProfileRequest(
            1,
                    binding.edtUsername.text.toString(),
            binding.edtEmailReg.text.toString(),
            binding.edtPhone.text.toString()
        )

        // viewModel.regsiter(body).observe(this, {

        //   when (it.state){
        //    State.SUCCES ->{
        //  dismisLoading()
        //      showToast("Registrasi berhasil")
        //    pushActivity(LoginActivity::class.java)

        //    }
        //      State.ERROR ->{
        //         //dismisLoading()
        //          toastError(it.message ?: "Error")
        //      }
        //     State.LOADING->{
        //showLoading()
        //   }
        //   }
        // })

        //   }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}