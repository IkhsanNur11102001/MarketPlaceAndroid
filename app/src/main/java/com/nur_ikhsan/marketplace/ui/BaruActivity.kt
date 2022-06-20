package com.nur_ikhsan.marketplace.ui

import android.os.Bundle
import com.nur_ikhsan.marketplace.ui.base.MyActivity
import com.nur_ikhsan.marketplace.ui.toko.TokoViewModel
import com.inyongtisto.myhelper.extension.*
import com.nur_ikhsan.marketplace.databinding.ActivityBukatokoBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class BaruActivity : MyActivity() {
    private lateinit var binding: ActivityBukatokoBinding
    private val viewModel: TokoViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBukatokoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolbar(binding.lyToolbar.toolbar, "Buka Toko")

        mainButton()
    }

    private fun mainButton() {

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}