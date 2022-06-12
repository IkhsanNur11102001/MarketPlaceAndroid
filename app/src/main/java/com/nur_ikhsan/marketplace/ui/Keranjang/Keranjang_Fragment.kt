package com.nur_ikhsan.marketplace.ui.Keranjang

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.nur_ikhsan.marketplace.databinding.FragmentAkunBinding
import com.nur_ikhsan.marketplace.databinding.FragmentKeranjangBinding
import com.nur_ikhsan.marketplace.ui.notifications.NotificationsViewModel


class Keranjang_Fragment : Fragment() {
    private lateinit var keranjangViewModel: KeranjangViewModel
    private var _binding: FragmentKeranjangBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        keranjangViewModel =
            ViewModelProvider(this).get(KeranjangViewModel::class.java)

        _binding = FragmentKeranjangBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textKeranjang
        keranjangViewModel.text.observe(viewLifecycleOwner){
            textView.text = it
        }



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}