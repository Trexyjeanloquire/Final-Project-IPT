package services


import models.Product

class ManagerServices(
    products: MutableList<Product>, idBase: HashMap<Int, MutableList<Product>>,
    categoryBase: HashMap<String, MutableList<Product>>

) {
    private val productServices = ProductServices(products, idBase, categoryBase)


    fun choice() {

        println(
            """
            1. Add Product
            2. Update Product
            3. Search Product
            4. Display all Products
            5. Display all Products in selected category
            6. Logout
            Enter Selection: 
        """.trimIndent()
        )
        val selection = readln().toIntOrNull()
        if (selection == null) {
            println("Invalid selection, please enter a valid selection.")
            println()
            return choice()
        }
        when (selection) {

            1 -> {
                productServices.addProduct()
                return choice()
            }

            2 -> {
                productServices.updateProduct()
                return choice()
            }

            3 -> {
                productServices.search()
                return choice()
            }

            4 -> {
                productServices.showAll()
                choice()
            }

            5 -> {
                productServices.showProdCategory()
                return choice()
            }

            6 -> {
                println("Successfully logged out")
                println()
                return
            }

            else -> {
                println("Invalid selection, please enter a valid selection.")
                println()
                return choice()
            }

        }


    }


}