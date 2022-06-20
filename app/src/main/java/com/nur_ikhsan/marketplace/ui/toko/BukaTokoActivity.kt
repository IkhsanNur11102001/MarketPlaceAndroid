package com.nur_ikhsan.marketplace.ui.toko

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.inyongtisto.myhelper.extension.*
import com.nur_ikhsan.marketplace.Util.Prefs
import com.nur_ikhsan.marketplace.core.data.source.model.Toko
import com.nur_ikhsan.marketplace.core.data.source.remote.network.State
import com.nur_ikhsan.marketplace.core.data.source.remote.request.CreatTokoRequest
import com.nur_ikhsan.marketplace.databinding.ActivityBukatokoBinding
import com.nur_ikhsan.marketplace.ui.base.MyActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class BukaTokoActivity : MyActivity() {
    private lateinit var binding: ActivityBukatokoBinding
    private val tokoViewModel: TokoViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBukatokoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbar(binding.lyToolbar.toolbar, "Buka Toko")
        Log.d("RESPON", "PESAN SINGKAT")
        mainButton()
    }

    private fun mainButton(){
        binding.btnBukToko.setOnClickListener {
            intentActivity(TokoSaya::class.java)
            bukaToko()
        }

    }

    private fun bukaToko() {
        val body = CreatTokoRequest(
            userId = Prefs.getUser()?.id ?: 0,
            name = binding.edtName.getString(),
            kota = binding.edtLokasi.getString()
        )
        tokoViewModel.creatToko(body).observe(this) {
            when (it.state) {
                State.SUCCES -> {
                    progress.dismiss()
                    val data = it.data
                    toastSimple("nama Toko:" + data?.name)
                    intentActivity(TokoSaya::class.java)

                    val user = Prefs.getUser()
                    user?.toko = Toko(
                        id = data?.id,
                        name = data?.name,
                        kota = data?.kota
                    )
                    Prefs.setUser(user)
                    finish()
                }
                State.ERROR -> {
                    toastError(it.message ?: "Error")
                    progress.dismiss()

                }
                State.LOADING -> {
                    progress.show()

                }
            }
        }

    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}