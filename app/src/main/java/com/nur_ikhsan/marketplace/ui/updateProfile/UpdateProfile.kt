package com.nur_ikhsan.marketplace.ui.updateProfile

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.github.drjacky.imagepicker.ImagePicker
import com.inyongtisto.myhelper.extension.*
import com.nur_ikhsan.marketplace.Util.Constant
import com.nur_ikhsan.marketplace.Util.Prefs
import com.nur_ikhsan.marketplace.core.data.source.remote.network.State
import com.nur_ikhsan.marketplace.core.data.source.remote.request.RegisterRequest
import com.nur_ikhsan.marketplace.core.data.source.remote.request.UpdateProfileRequest
import com.nur_ikhsan.marketplace.databinding.ActivityRegisterBinding
import com.nur_ikhsan.marketplace.databinding.UpdateProfileBinding
import com.nur_ikhsan.marketplace.ui.akun.AkunFragment
import com.nur_ikhsan.marketplace.ui.auth.AuthViewModel
import com.nur_ikhsan.marketplace.ui.auth.LoginActivity
import com.nur_ikhsan.marketplace.ui.base.MyActivity
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File

class UpdateProfile : MyActivity() {
    private val viewModel: AuthViewModel by viewModel()
    private var _binding: UpdateProfileBinding? = null
    private val binding get() = _binding!!
    private var fileImage: File? = null

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
                tvInisial.text = user.name.getInitial()
                Picasso.get().load(Constant.USER_URL +user.image).into(binding.imgProfile)
            }
        }
    }

    fun mainButon() {
        binding.btnSimpan.setOnClickListener {
            if (fileImage != null){
                upoadImage()
            }else{
                update()

            }


        }

        binding.imgProfile.setOnClickListener {
            pickImage()
        }
    }
    private fun pickImage(){
        ImagePicker.with(this)
            .crop()
            .maxResultSize(1080, 1080, true)
            .createIntentFromDialog { launcher.launch(it) }


    }
    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            val uri = it.data?.data!!
            fileImage = File(uri.path?:"")
            Picasso.get().load(uri).into(binding.imgProfile)
        }
    }







    ///--------update profile-----------///
    private fun update() {

        if (binding.edtUsername.isEmpty()) return
        if (binding.edtEmailReg.isEmpty()) return
        if (binding.edtPhone.isEmpty()) return

        val idUser = Prefs.getUser()?.id
        val body = UpdateProfileRequest(
            idUser.int(),
            binding.edtUsername.text.toString(),
            binding.edtEmailReg.text.toString(),
            binding.edtPhone.text.toString()
        )

        viewModel.updateProfile(body).observe(this, {

          when (it.state){
            State.SUCCES ->{
                progress.dismiss()
                showToast("Update berhasil")
                onBackPressed()

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
    ///--------update profile-----------///








    ///--------upload foto-----------///
    private fun upoadImage(){
        val idUser = Prefs.getUser()?.id
        val file = fileImage.toMultipartBody()
        viewModel.uploadUser(idUser, file).observe(this, {

            when (it.state){
                State.SUCCES ->{
                    showToast("Update berhasil")
                    update()

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
    ///--------upload foto-----------///




    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}