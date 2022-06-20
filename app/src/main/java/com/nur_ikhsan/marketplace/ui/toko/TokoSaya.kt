package com.nur_ikhsan.marketplace.ui.toko


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.inyongtisto.myhelper.extension.*
import com.inyongtisto.myhelper.util.Constants
import com.nur_ikhsan.marketplace.Util.Constant.USER_URL
import com.nur_ikhsan.marketplace.Util.Prefs
import com.nur_ikhsan.marketplace.core.data.source.model.User
import com.nur_ikhsan.marketplace.databinding.ActivityTokoSayaBinding
import com.nur_ikhsan.marketplace.databinding.FragmentAkunBinding
import com.nur_ikhsan.marketplace.ui.auth.LoginActivity
import com.nur_ikhsan.marketplace.ui.updateProfile.UpdateProfile
import com.squareup.picasso.Picasso

class TokoSaya : AppCompatActivity() {
    private  lateinit var binding: ActivityTokoSayaBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTokoSayaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolbar(binding.lyToolbar.toolbar, "Toko saya")


        //get data dari server
        setData()

    }
    private fun setData() {
        val user = Prefs.getUser()
        if (user != null) {
            binding.apply {
                if (user.toko != null) {
                    tvName.text = user.toko?.name
                    tvInisial.text = user.toko?.name.getInitial()
                    Picasso.get().load(USER_URL + user.toko?.name).into(binding.imageProfile)
                }
            }
        }


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}