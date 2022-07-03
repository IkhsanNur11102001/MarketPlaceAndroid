package com.nur_ikhsan.marketplace.ui.alamatToko.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.inyongtisto.myhelper.extension.intentActivity
import com.inyongtisto.myhelper.extension.logs
import com.inyongtisto.myhelper.extension.popUpMenu
import com.inyongtisto.myhelper.extension.toJson
import com.nur_ikhsan.marketplace.core.data.source.model.AlamatToko
import com.nur_ikhsan.marketplace.databinding.ItemAlamatTokoBinding
import com.nur_ikhsan.marketplace.ui.alamat.EditAlamatToko

@SuppressLint("NotifyDataSetChanged")
class AlamatTokoAdapter : RecyclerView.Adapter<AlamatTokoAdapter.ViewHolder>() {

    private var data = ArrayList<AlamatToko>()

    inner class ViewHolder(private val itemBinding: ItemAlamatTokoBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: AlamatToko, position: Int) {
            itemBinding.apply {

                tvKota.text = item.kota
                var  kecamatan = ""
                if (item.kecamatan != null) kecamatan = "${item.kecamatan}"

                tvAlamat.text = "${item.alamat}, Kec. $kecamatan, Kodepos. ${item.kodepost}, ${item.kota}, Prov. ${item.provinsi}"
                tvEmail.text = item.email
                tvPhone.text = item.phone

                val context = root.context
                btnMenu.setOnClickListener {
                    val listmenu = listOf("Edit alamat", "Hapus alamat")
                    context.popUpMenu(btnMenu, listmenu){
                        when(it){
                            "Edit alamat"-> context.intentActivity(EditAlamatToko::class.java, item.toJson())
                            "Hapus alamat"-> logs("Hapus alamat")
                        }
                    }

//                    val i = Intent(context, EditAlamatToko::class.java)
//                    i.putExtra("alamat", item.toJson())
//                    context.startActivity(i)

                }
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