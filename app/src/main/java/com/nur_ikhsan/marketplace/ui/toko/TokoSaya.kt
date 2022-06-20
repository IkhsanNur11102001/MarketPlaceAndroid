package com.nur_ikhsan.marketplace.ui.toko


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.inyongtisto.myhelper.extension.*
import com.nur_ikhsan.marketplace.Util.Constant.USER_URL
import com.nur_ikhsan.marketplace.Util.Prefs
import com.nur_ikhsan.marketplace.databinding.ActivityTokoSayaBinding
import com.nur_ikhsan.marketplace.ui.alamat.ListAlamatTokoActivity
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
        setUpLinstener()

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
    private fun setUpLinstener(){
        binding.apply {
            btnAlamat.setOnClickListener {
                intentActivity(ListAlamatTokoActivity::class.java)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}