package com.mdev.restaurant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mdev.restaurant.databinding.FragmentOrderBinding

class OrderFragment : Fragment() {
    private var _binding: FragmentOrderBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentOrderBinding.inflate(inflater, container, false)
        val view = binding.root
        val dbHelper = DatabaseHelper(requireContext(), null)
        val listView = binding.dataList
        listView.adapter = MenuAdapter(requireContext(), dbHelper.getMenus())
        return view
    }
}