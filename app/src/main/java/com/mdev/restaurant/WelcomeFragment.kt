package com.mdev.restaurant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.mdev.restaurant.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {
    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        val view = binding.root
        val startBtn = binding.start
        startBtn.setOnClickListener {
            view.findNavController().navigate(R.id.action_welcomeFragment_to_addFragment)
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}