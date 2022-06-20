package com.nur_ikhsan.marketplace.ui.alamat

import android.os.Bundle
import com.nur_ikhsan.marketplace.ui.base.MyActivity
import com.inyongtisto.myhelper.extension.*
import com.nur_ikhsan.marketplace.core.data.source.remote.network.State
import com.nur_ikhsan.marketplace.databinding.ActivityAlamatTokoListBinding
import com.nur_ikhsan.marketplace.ui.alamatToko.adapter.AlamatTokoAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListAlamatTokoActivity : MyActivity() {
    private lateinit var binding: ActivityAlamatTokoListBinding
    private val viewModel: AlamatTokoViewModel by viewModel()
    private val adapter = AlamatTokoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlamatTokoListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolbar(binding.lyToolbar.toolbar, "Buka Toko")

        getData()
        setupAdapter()
        mainButton()
    }

    private fun mainButton() {

    }

    private fun setupAdapter() {
        binding.rvData.adapter = adapter
    }

    private fun getData() {
        viewModel.get().observe(this) {
            when (it.state) {
                State.SUCCES -> {
                    binding.tvError.toGone()
                    val data = it.data?: emptyList()
                    adapter.addItems(data)

                    if (data.isEmpty()) {
                        binding.tvError.toVisible()
                    }
                }
                State.ERROR -> {
                    binding.tvError.toVisible()
                }
                State.LOADING -> {

                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}