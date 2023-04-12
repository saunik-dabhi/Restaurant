package com.mdev.restaurant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.mdev.restaurant.databinding.FragmentAddBinding
import com.mobsandgeeks.saripaar.ValidationError
import com.mobsandgeeks.saripaar.Validator
import com.mobsandgeeks.saripaar.annotation.NotEmpty
import com.mobsandgeeks.saripaar.annotation.Pattern


class AddFragment : Fragment(), Validator.ValidationListener {
    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!
    private lateinit var validator: Validator

    @NotEmpty
    @Pattern(regex = "^\\d+$", message = "ID should be number")
    lateinit var id: EditText

    @NotEmpty
    @Pattern(regex = "^\\d+$", message = "Price should be integer")
    lateinit var price: EditText

    @NotEmpty
    @Pattern(regex = "^\\d+$", message = "Rank should be integer")
    lateinit var rank: EditText

    @NotEmpty
    @Pattern(regex = "^[a-zA-Z\\s]*$", message = "Name should be in alphabets")
    lateinit var name: EditText

    @NotEmpty
    @Pattern(regex = "^[a-zA-Z\\s]*$", message = "Category should be in alphabets")
    lateinit var category: EditText

    @NotEmpty
    @Pattern(regex = "^[a-zA-Z\\s]*$", message = "Topping should be in alphabets")
    lateinit var topping: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        val view = binding.root
        validator = Validator(this)
        validator.setValidationListener(this)
        val submit = binding.submit
        id = binding.id
        price = binding.price
        rank = binding.rank
        name = binding.name
        topping = binding.topping
        category = binding.category
        submit.setOnClickListener {
            validator.validate()
            val dbHelper = DatabaseHelper(requireContext(), null)
            val idText = id.text.toString()
            val nameText = name.text.toString()
            val categoryText = id.text.toString()
            val toppingText = id.text.toString()
            val priceText = id.text.toString()
            val rankText = id.text.toString()
            if (idText.isEmpty() || nameText.isEmpty() || categoryText.isEmpty() || toppingText.isEmpty() || priceText.isEmpty() || rankText.isEmpty()) {
                Toast.makeText(requireContext(), "Some Fields may be empty", Toast.LENGTH_LONG)
                    .show()
            } else {
                val res = dbHelper.createMenu(
                    idText.toInt(),
                    nameText,
                    categoryText,
                    toppingText,
                    priceText.toInt(),
                    rankText.toInt()
                )
                var text = "Order with ${binding.id.text} "
                text += if (res) {
                    "created"
                } else {
                    "not created"
                }
                Toast.makeText(
                    requireContext(),
                    text,
                    Toast.LENGTH_LONG
                ).show()
                binding.id.setText("")
                binding.name.setText("")
                binding.rank.setText("")
                binding.price.setText("")
                binding.category.setText("")
                binding.topping.setText("")
            }
        }
        val navBtn = binding.nav
        navBtn.setOnClickListener {
            view.findNavController().navigate(R.id.action_addFragment_to_orderFragment)
        }
        return view
    }

    override fun onValidationSucceeded() {
        // we have already made a toast in case of validation success
    }

    override fun onValidationFailed(errors: MutableList<ValidationError>?) {
        if (errors != null) {
            for (error in errors) {
                val errorView = error.view
                val text = error.getCollatedErrorMessage(requireContext())
                if (errorView is EditText) {
                    errorView.error = text
                } else {
                    Toast.makeText(requireContext(), text, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}