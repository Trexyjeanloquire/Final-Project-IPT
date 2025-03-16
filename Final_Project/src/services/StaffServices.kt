package services

import models.Product

class StaffServices(
    products: MutableList<Product>, idBase: HashMap<Int, MutableList<Product>>,
    categoryBase: HashMap<String, MutableList<Product>>
) {

    private val productServices = ProductServices(products, idBase, categoryBase)

    fun staffChoices() {

        println(
            """
            1. Display all Products
            2. Display products by selected category
            3. Search Products
            4. Logout
            
        """.trimIndent()
        )
        val selection = readln().toIntOrNull()

        if (selection == null) {
            println("Invalid selection, please enter a valid selection.")
            println()
            return staffChoices()
        }

        when (selection) {
            1 -> {
                productServices.showAll()
                return staffChoices()
            }

            2 -> {
                productServices.showProdCategory()
                return staffChoices()
            }

            3 -> {
                productServices.search()
                return staffChoices()
            }

            4 -> {
                println("Successfully Logged out.")
                println()
                return
            }


            else -> {
                println("Invalid selection, please enter a valid selection.")
                println()
                return staffChoices()
            }


        }


    }
}