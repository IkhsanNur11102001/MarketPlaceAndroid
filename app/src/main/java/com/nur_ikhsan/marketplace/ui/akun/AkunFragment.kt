package com.nur_ikhsan.marketplace.ui.akun

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.inyongtisto.myhelper.extension.getInitial
import com.inyongtisto.myhelper.extension.intentActivity
import com.inyongtisto.myhelper.extension.pushActivity
import com.nur_ikhsan.marketplace.NavigationActivity
import com.nur_ikhsan.marketplace.Util.Prefs
import com.nur_ikhsan.marketplace.core.data.source.remote.network.State
import com.nur_ikhsan.marketplace.databinding.FragmentAkunBinding
import com.nur_ikhsan.marketplace.ui.updateProfile.UpdateProfile
import org.koin.androidx.viewmodel.ext.android.viewModel

class AkunFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel
    private var _binding: FragmentAkunBinding? = null


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentAkunBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setUser()
        mainButton()
        return root
    }

 private fun mainButton(){
        binding.btnLogout.setOnClickListener {
            Prefs.isLogin = false
            pushActivity(NavigationActivity::class.java)
        }
     binding.btnUpdate.setOnClickListener {
         intentActivity(UpdateProfile::class.java)
     }
    }
    private fun setUser(){
        val user = Prefs.getUser()
        if (user!= null){
            binding.apply {
                tvName.text = user.name
                tvPhone.text = user.phone
                tvEmail.text = user.email
                tvInisial.text = user.name.getInitial()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}