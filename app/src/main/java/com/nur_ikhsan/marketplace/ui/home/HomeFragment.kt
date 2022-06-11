package com.nur_ikhsan.marketplace.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.nur_ikhsan.marketplace.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        setData()
        myButton()

        return root
    }

    fun setData() {
        homeViewModel.text.observe(viewLifecycleOwner, {
            binding.tvHai.text = it
        })

    }

    fun myButton(){
        binding.btnTas.setOnClickListener {
            homeViewModel.ubahData()
        }

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}