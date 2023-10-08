package com.example.challenge2_foodapp.ui.fragment.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.challenge2_foodapp.R
import com.example.challenge2_foodapp.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getSavedData()
        binding.saveButton.setOnClickListener {
            savedWithSharedPreferences()
        }
    }

    private fun savedWithSharedPreferences() {

        val username = binding.nameEdit.text.toString()
        val password = binding.passwordEdit.text.toString()
        val email = binding.emailEdit.text.toString()
        val phone = binding.phoneNumberEdit.text.toString()

        val sharedPreferences = requireActivity().getSharedPreferences("myPref", 0)
        val editor = sharedPreferences.edit()

        editor.putString("name", username)
        editor.putString("password", password)
        editor.putString("email", email)
        editor.putString("phone", phone)
        editor.apply()

    }

    private fun getSavedData() {
        val sharedPreferences = requireActivity().getSharedPreferences("myPref", 0)
        val name = sharedPreferences.getString("name", "")
        val password = sharedPreferences.getString("password", "")
        val email = sharedPreferences.getString("email", "")
        val phone = sharedPreferences.getString("phone", "")

        binding.nameEdit.setText(name)
        binding.passwordEdit.setText(password)
        binding.emailEdit.setText(email)
        binding.phoneNumberEdit.setText(phone)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}