package com.nur_ikhsan.marketplace.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.nur_ikhsan.marketplace.databinding.FragmentHomeBinding
import com.nur_ikhsan.marketplace.ui.home.adapter.CategoryAdapter
import com.nur_ikhsan.marketplace.ui.home.adapter.ProductTerbaruAdapter
import com.nur_ikhsan.marketplace.ui.home.adapter.ProductTerlarisAdapter
import com.nur_ikhsan.marketplace.ui.home.adapter.SliderAdapter

class HomeFragment : Fragment() {
    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val adapterCategory = CategoryAdapter()
    private val adapterSlider = SliderAdapter()

    private val adapterProductTerlaris = ProductTerlarisAdapter()
    private val adapterProductTerbaru = ProductTerbaruAdapter()



    override fun onCreateView(
        inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


      setData()
      myButton()
        setupAdapter()

        return root
    }

    private fun setupAdapter() {
        binding.rvCategory.adapter = adapterCategory
        binding.rvSlider.adapter = adapterSlider
        binding.rvProductTerlaris.adapter = adapterProductTerlaris
        binding.rvProductTerbaru.adapter = adapterProductTerbaru

    }

    fun setData() {
        homeViewModel.listCategory.observe(requireActivity(), {
            adapterCategory.addItems(it)
        })

        homeViewModel.listSlider.observe(requireActivity(), {
            adapterSlider.addItems(it)
        })

        homeViewModel.listProduct.observe(requireActivity(), {
            adapterProductTerlaris.addItems(it)
            adapterProductTerbaru.addItems(it)
        })
    }

  fun myButton(){

    }



   override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}