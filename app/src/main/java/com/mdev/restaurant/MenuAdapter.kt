package com.mdev.restaurant

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MenuAdapter(context: Context, data: List<MenuItem>) :
    ArrayAdapter<MenuItem>(context, R.layout.list_item, data) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        getItem(position)
        var view = convertView
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        }
        val dbHelper = DatabaseHelper(context, null)
        val menu = dbHelper.getMenus()[position]
        val name = view!!.findViewById<TextView>(R.id.names)
        val category = view.findViewById<TextView>(R.id.categories)
        val topping = view.findViewById<TextView>(R.id.toppings)
        val price = view.findViewById<TextView>(R.id.prices)
        val rank = view.findViewById<TextView>(R.id.ranks)
        name.text = menu.name
        category.text = menu.category
        topping.text = menu.topping.toString()
        price.text = menu.price.toString()
        rank.text = menu.rank.toString()
        val btn = view.findViewById<Button>(R.id.delete)
        btn.setOnClickListener {
            var text = "Selected Menu Item with id ${menu.id} is "
            text += if (dbHelper.deleteById(menu.id)) {
                "not deleted"
            } else {
                "deleted"
            }
            Toast.makeText(context, text, Toast.LENGTH_LONG).show()
        }
        return view
    }
}