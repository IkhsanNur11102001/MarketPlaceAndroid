package com.nur_ikhsan.marketplace.ui.alamat

import android.os.Bundle
import com.nur_ikhsan.marketplace.ui.base.MyActivity
import com.nur_ikhsan.marketplace.ui.toko.TokoViewModel
import com.inyongtisto.myhelper.extension.*
import com.nur_ikhsan.marketplace.Util.defaultError
import com.nur_ikhsan.marketplace.Util.getTokoId
import com.nur_ikhsan.marketplace.core.data.source.model.AlamatToko
import com.nur_ikhsan.marketplace.core.data.source.remote.network.State
import com.nur_ikhsan.marketplace.databinding.ActivityBukatokoBinding
import com.nur_ikhsan.marketplace.databinding.ActivityTambahAlamatTokoBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class TambahAlamatTokoActivity : MyActivity() {
    private lateinit var binding: ActivityTambahAlamatTokoBinding
    private val viewModel: AlamatTokoViewModel by viewModel()
    private var provinsiId: Int? = null
    private var kotaId: Int? = null
    private var kecamatanId: Int? = null
    private var provinsi: String? = null
    private var kota: String? = null
    private var kecamatan: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTambahAlamatTokoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolbar(binding.lyToolbar.toolbar, "Tambah alamat")

        setupUI()
        mainButton()
    }

    private fun setupUI() {
        // 0, 1, 2, 3
        val listProvinsi = listOf("Pilih Provinsi", "Jawa Timur", "Jawa Tengah", "Jawa Barat")
        val listKota = listOf("Pilih Kota", "Kuningan", "Cirebon", "Bogor")
        val listKecamatan = listOf("Pilih Kecamatan", "Kramatmulya", "Kesambi", "Kejaksan, Gunung Jati")

        binding.spnProvinsi.setOnPositionSelectedListener(this, listProvinsi) {
            if (it == 0) {
                provinsiId = null
            } else {
                // it == 1
                provinsiId = 10
                provinsi = listProvinsi[it]
            }
        }

        binding.spnKota.setOnPositionSelectedListener(this, listKota) {
            if (it == 0) {
                kotaId = null
            } else {
                // it == 1
                kotaId = 399
                kota = listKota[it]
            }
        }

        binding.spnKecamatan.setOnPositionSelectedListener(this, listKecamatan) {
            if (it == 0) {
                kecamatanId = null
            } else {
                // it == 1
                kecamatanId = 5505
                kecamatan = listKecamatan[it]
            }
        }
    }

    private fun mainButton() {
        binding.apply {
            lyToolbar.btnSimpan.toVisible()
            lyToolbar.btnSimpan.setOnClickListener {
                if (validate()) simpan()
                simpan()

            }
            lyToolbar.btnSimpan.setOnLongClickListener {
                edtLabel.setText("Rumah Ikhsan")
                edtAlamat.setText("Desa Cibentang")
                edtKodePos.setText("4553")
                edtEmail.setText("ikhsan@gmail.com")
                edtPhone.setText("083823065878")
                return@setOnLongClickListener true
            }
        }
    }

    private fun validate(): Boolean {
        binding.apply {
            if (edtLabel.isEmpty()) return false
            if (edtAlamat.isEmpty()) return false
            if (edtKodePos.isEmpty()) return false
            if (edtEmail.isEmpty()) return false
            if (edtPhone.isEmpty()) return false
            if (provinsiId == null) {
                toastSimple("Harap pilih Provinsi")
                return false
            }
            if (kotaId == null) {
                toastSimple("Harap pilih Kota")
                return false
            }
            if (kecamatanId == null) {
                toastSimple("Harap pilih Kecamatan")
                return false
            }
        }
        return true
    }

    private fun simpan(){
        val reqData = AlamatToko(
            tokoId = getTokoId(),
            label = binding.edtLabel.getString(),
            alamat = binding.edtAlamat.getString(),
            provinsi = provinsi,
            kota = kota,
            kecamatan = kecamatan,
            provinsiId = provinsiId,
            kotaId = kotaId,
            kecamatanId = kotaId,
            kodepost = binding.edtKodePos.getString(),
            email = binding.edtEmail.getString(),
            phone = binding.edtPhone.getString(),
        )
        viewModel.create(reqData).observe(this) {
            when (it.state) {
                State.SUCCES -> {
                    progress.dismiss()
                    toastSuccess("Berhasil menambah alamat")
                    onBackPressed()
                }
                State.ERROR -> {
                    progress.dismiss()
                    showErrorDialog(it.message.defaultError()
                    )
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