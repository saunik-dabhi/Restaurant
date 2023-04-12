package com.mdev.restaurant

class MenuItem(
    id: Int,
    category: String,
    name: String,
    topping: List<String>,
    price: Int,
    rank: Int
) {
    val id: Int
    val category: String
    val name: String
    val topping: List<String>
    val price: Int
    val rank: Int

    init {
        this.id = id
        this.category = category
        this.name = name
        this.topping = topping
        this.price = price
        this.rank = rank
    }

//    @Override
//    override fun toString(): String {
//        return "ID: $id\nCategory: $category\nName: $name\nTopping: $topping\nPrice: $price\nRank: $rank"
//    }
}