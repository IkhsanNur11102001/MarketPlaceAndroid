package com.nur_ikhsan.marketplace.ui.alamatToko.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nur_ikhsan.marketplace.core.data.source.model.AlamatToko
import com.nur_ikhsan.marketplace.databinding.ItemAlamatTokoBinding

@SuppressLint("NotifyDataSetChanged")
class AlamatTokoAdapter : RecyclerView.Adapter<AlamatTokoAdapter.ViewHolder>() {

    private var data = ArrayList<AlamatToko>()

    inner class ViewHolder(private val itemBinding: ItemAlamatTokoBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: AlamatToko, position: Int) {
            itemBinding.apply {

                tvKota.text = "Nama Kota : "+item.kota
                var  kecamatan = ""
                if (item.kecamatan != null) kecamatan = "${item.kecamatan}"

                tvAlamat.text = "Alamat lengkap : ${item.alamat}, Kec. $kecamatan, KodePos. ${item.kodepost}"
                tvEmail.text = item.email
                tvPhone.text = item.phone
            }
        }
    }

    fun addItems(items: List<AlamatToko>) {
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemAlamatTokoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

}