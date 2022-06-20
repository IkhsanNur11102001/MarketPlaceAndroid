package com.nur_ikhsan.marketplace.ui.akun


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.inyongtisto.myhelper.extension.getInitial
import com.inyongtisto.myhelper.extension.intentActivity
import com.inyongtisto.myhelper.extension.pushActivity
import com.inyongtisto.myhelper.extension.toGone
import com.nur_ikhsan.marketplace.Util.Constant.USER_URL
import com.nur_ikhsan.marketplace.Util.Prefs
import com.nur_ikhsan.marketplace.databinding.FragmentAkunBinding
import com.nur_ikhsan.marketplace.ui.auth.LoginActivity
import com.nur_ikhsan.marketplace.ui.toko.BukaTokoActivity
import com.nur_ikhsan.marketplace.ui.toko.TokoSaya
import com.nur_ikhsan.marketplace.ui.updateProfile.UpdateProfile
import com.squareup.picasso.Picasso

class AkunFragment : Fragment() {

    private lateinit var akunViewModel: AkunViewModel
    private var _binding: FragmentAkunBinding? = null


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        akunViewModel =
            ViewModelProvider(this).get(AkunViewModel::class.java)

        _binding = FragmentAkunBinding.inflate(inflater, container, false)
        val root: View = binding.root


        mainButton()
        return root
    }

    override fun onResume() {
        setUser()
        super.onResume()
    }

 private fun mainButton(){
        binding.btnLogout.setOnClickListener {
            Prefs.isLogin = false
            pushActivity(LoginActivity::class.java)
        }
     binding.btnUpdate.setOnClickListener {
         intentActivity(UpdateProfile::class.java)
     }

     binding.btnToko.setOnClickListener {
         intentActivity(BukaTokoActivity::class.java)
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
                Picasso.get().load(USER_URL+user.image).into(binding.imgProfile)

                if (user.toko != null) {
                    tvStatusToko.toGone()
                    tvNameToko.text = user.toko?.name
                    binding.btnToko.setOnClickListener {
                        intentActivity(TokoSaya::class.java)
                    }
                } else {
                    binding.btnToko.setOnClickListener {
                        intentActivity(BukaTokoActivity::class.java)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}