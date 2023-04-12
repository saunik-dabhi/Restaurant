package com.mdev.restaurant

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(
    context: Context,
    factory: SQLiteDatabase.CursorFactory?,
) :
    SQLiteOpenHelper(context, dbName, factory, dbVersion) {

    override fun onCreate(db: SQLiteDatabase?) {
        val query =
            "CREATE TABLE $menuTable ($menuId INTEGER PRIMARY KEY, $menuCategory TEXT,$menuName TEXT,$menuTopping TEXT,$menuPrice INTEGER,$menuRank INTEGER)"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $menuTable")
        onCreate(db)
    }

    fun createMenu(
        id: Int,
        name: String,
        category: String,
        toppings: String,
        price: Int,
        rank: Int,
    ): Boolean {
        val values = ContentValues()
        values.put(menuId, id)
        values.put(menuCategory, category)
        values.put(menuName, name)
        values.put(menuPrice, price)
        values.put(menuRank, rank)
        values.put(menuTopping, toppings)
        val db = this.writableDatabase
        val res = db.insert(menuTable, null, values)
        db.close()
        return res != (-1).toLong()
    }

    fun getMenus(): List<MenuItem> {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $menuTable", null)
        val menus: ArrayList<MenuItem> = ArrayList()
        if (cursor.moveToFirst()) {
            do {
                menus.add(
                    MenuItem(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3).split('\n'),
                        cursor.getInt(4),
                        cursor.getInt(5)
                    )
                )
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return menus
    }

    fun deleteById(id: Int): Boolean {
        val db = this.writableDatabase
        val cursor = db.rawQuery("DELETE FROM $menuTable WHERE $menuId = $id", null)
        val bool = cursor.moveToFirst()
        cursor.close()
        db.close()
        return bool
    }

    companion object {
        private const val dbName = "restaurant.db"
        private const val dbVersion = 1
        const val menuTable = "menu"
        const val menuId = "id"
        const val menuName = "name"
        const val menuCategory = "category"
        const val menuTopping = "topping"
        const val menuPrice = "price"
        const val menuRank = "rank"
    }
}