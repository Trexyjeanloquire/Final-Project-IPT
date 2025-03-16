package services

import models.Product
import kotlin.random.Random

class ProductServices(
    private val products: MutableList<Product>,
    private val idBase: HashMap<Int, MutableList<Product>>,
    private val categoryBase: HashMap<String, MutableList<Product>>
) {


    fun addProduct() {
        print("Enter a Product Name: ")
        val name = readlnOrNull()?.trim()

        if (name.isNullOrEmpty()) {
            println("Invalid name, please enter a valid  name")
            println()
            return addProduct()
        }
        val locate = products.find { it.getProductName() == name }
        if (locate != null) {
            println("The product $name is already exist!")
            return addProduct()
        }

        print("Enter a Product Category: ")
        val category = readlnOrNull()?.trim()
        if (category.isNullOrEmpty()) {
            println("Invalid Category, please enter a valid  Category")
            println()
            return addProduct()
        }


        print("Enter a Product Brand: ")
        val brand = readlnOrNull()?.trim()
        if (brand.isNullOrEmpty()) {
            println("Invalid name, please enter a valid  Brand")
            println()
            return addProduct()
        }
        print("Enter a Product Price: ")
        val price = readln().toDoubleOrNull()
        if (price == null || price <= 0) {
            println("Invalid Price, please enter a valid Price")
            println()
            return addProduct()
        }

        print("Enter a Product Quantity: ")
        val quantity = readln().toIntOrNull()
        if (quantity == null || quantity <= 0) {
            println("Invalid Quantity, please enter a valid Quantity")
            println()
            return addProduct()
        }

        val id = generatorProductId().toInt()

        val product = Product(name, id, category, brand, price, quantity)
        products.add(product)

        categoryBase.computeIfAbsent(category) { mutableListOf() }.add(product)
        idBase.computeIfAbsent(id) { mutableListOf() }.add(product)
        println("The product $name has been successfully added!")
        println()
    }


    fun showAll() {
        if (products.isNotEmpty()) {
            println("ID       | Product Name      | Category        | Brand       | Price    | Quantity")
            println("----------------------------------------------------------------------------")

            for (product in products) {
                println(
                    "${product.getProductId().toString().padEnd(8)} | ${
                        product.getProductName()?.padEnd(18)
                    } | ${product.getProductName()?.padEnd(14)} | ${
                        product.getProductBrand()?.padEnd(10)
                    } | ${product.getProductPrice().toString().padEnd(8)} | ${
                        product.getProductQuantity().toString().padEnd(8)
                    }"
                )
                println("----------------------------------------------------------------------------")
            }
        } else {
            println("There are no products")
        }


    }


    fun showProdCategory() {
        if (products.isNotEmpty()) {
            println("Enter a Category: ")
            val category = readlnOrNull()?.trim()
            if (category.isNullOrEmpty()) {
                println("Invalid Category, please enter a valid  Category")
            }

            val locateProd = categoryBase[category]
            if (locateProd.isNullOrEmpty()) {
                println("No products found in this category.")
                return
            }
            println("ID       | Product Name      | Category        | Brand       | Price    | Quantity")
            println("----------------------------------------------------------------------------")

            locateProd.forEach { products ->
                println(
                    "${products.getProductId().toString().padEnd(8)} | ${
                        products.getProductName()?.padEnd(18)
                    } | ${products.getProductCategory()?.padEnd(14)} | ${
                        products.getProductBrand()?.padEnd(10)
                    } | ${products.getProductPrice().toString().padEnd(8)} | ${
                        products.getProductQuantity().toString().padEnd(8)
                    }"
                )
                println("----------------------------------------------------------------------------")
            }


        } else {
            println("There are no products")
        }

    }


    fun updateProduct() {
        if (products.isNotEmpty()) {
            println("Enter the product name: ")
            val name = readlnOrNull()?.trim()

            if (name.isNullOrEmpty()) {
                println("Invalid name, please enter a valid  name")
                println()
                return updateProduct()
            }
            println("Enter the product id: ")
            val id = readln().toIntOrNull()

            if (id == null) {
                println("Invalid id, please enter a valid  id")
                println()
                return updateProduct()
            }
            val locateName1 = products.find { it.getProductName() == name }
            val locateName2 = products.indexOfFirst { it.getProductName() == name }


            if (locateName1 != null) {

                if (locateName2 == -1) {
                    println("Product not did not exist!")
                    return updateProduct()
                }
                if (id != locateName1.getProductId()) {
                    println("Id did not match!")
                    return updateProduct()
                }

                println("Enter new Product Name")
                val newName = readlnOrNull()?.trim()
                if (newName.isNullOrEmpty()) {
                    println("Invalid name, please enter a valid  name")
                    println()
                    return updateProduct()
                }

                print("Enter a new Product Category: ")
                val category = readlnOrNull()?.trim()
                if (category.isNullOrEmpty()) {
                    println("Invalid Category, please enter a valid  Category")
                    println()
                    return updateProduct()
                }


                print("Enter a new Product Brand: ")
                val brand = readlnOrNull()?.trim()
                if (brand.isNullOrEmpty()) {
                    println("Invalid name, please enter a valid  Brand")
                    println()
                    return updateProduct()
                }
                print("Enter a new Product Price: ")
                val price = readln().toDoubleOrNull()
                if (price == null) {
                    println("Invalid Price, please enter a valid Price")
                    println()
                    return updateProduct()
                }
                print("Enter a new Product Quantity: ")
                val quantity = readln().toIntOrNull()
                if (quantity == null) {
                    println("Invalid Quantity, please enter a valid Quantity")
                    println()
                    return updateProduct()
                }


                val product = Product(newName, id, category, brand, price, quantity)
                products[locateName2] = product

                categoryBase[locateName1.getProductCategory()]?.remove(locateName1)
                categoryBase.computeIfAbsent(category) { mutableListOf() }.add(product)
                idBase[locateName1.getProductId()]?.remove(locateName1)
                idBase.computeIfAbsent(id) { mutableListOf() }.add(product)
                println("Successfully updated!")
            } else {
                println("Product not did not exist!")
            }


        } else {
            println("There are no products")
        }

    }


    fun deleteProduct() {
        if (products.isNotEmpty()) {
            println("Enter a product name to delete: ")
            val name = readlnOrNull()?.trim()
            if (name.isNullOrEmpty()) {
                println("Invalid name, please enter a valid  name")
                println()
                return deleteProduct()
            }
            println("Enter a product name to delete: ")
            val id = readln().toIntOrNull()

            if (id == null) {
                println("Invalid id, please enter a valid  id")
                println()
                return deleteProduct()
            }
            val locateName1 = products.find { it.getProductName() == name }
            val locateName2 = products.indexOfFirst { it.getProductName() == name }
            if (locateName1 != null) {
                if (locateName2 == -1) {
                    println("Product did not exist!")
                    return deleteProduct()
                }
                println("Are you sure you want to delete ${locateName1.getProductName()}")
                println(
                    """
                    1. Yes
                    2. no
                """.trimIndent()
                )
                val choice = readln().toIntOrNull()
                if (choice == null) {
                    println("Please enter a valid choice")
                    println()
                    return deleteProduct()
                }
                when (choice) {
                    1 -> {


                        products.removeAt(locateName2)
                        categoryBase[locateName1.getProductCategory()]?.removeIf { it.getProductId() == locateName1.getProductId() }
                        idBase[id]?.removeIf { it.getProductId() == locateName1.getProductId() }
                        println("The product $name has been deleted!")
                        println()
                    }

                    2 -> {
                        println("Cancelled")
                    }

                    else -> {
                        println("Please enter a valid choice")
                        return deleteProduct()
                    }

                }


            } else {
                println("Product not did not exist!")
                println()
            }
        } else {
            println("There are no products")
            println()
        }


    }


    fun search() {
        if (products.isNotEmpty()) {
            println(
                """
                    Search using?: 
                    1. Product name
                    2. ID
                    3. Back
                    Enter selection:
                """.trimIndent()
            )
            val selection = readln().toIntOrNull()
            if (selection == null) {
                println("Invalid selection, please enter a valid selection.")
                return search()
            }

            when (selection) {
                1 -> {


                    println("Enter the product name to search: ")
                    val name = readlnOrNull()?.trim()
                    if (name.isNullOrEmpty()) {
                        println("Invalid selection, please enter a valid selection.")
                        return search()
                    }

                    val locateId2 = products.find { it.getProductName() == name }

                    if (locateId2 != null) {
                        println("Product: ")
                        println("Product Name: ${locateId2.getProductName()}")
                        println("Product id: ${locateId2.getProductId()}")
                        println("Product category: ${locateId2.getProductCategory()}")
                        println("Product Brand: ${locateId2.getProductBrand()}")
                        println("Product price: ${locateId2.getProductPrice()}")
                        println("Product quantity: ${locateId2.getProductQuantity()}")
                        println("")
                    } else {
                        println("Product does not exist!")
                    }


                }

                2 -> {
                    println("Enter the product id to search: ")
                    val id = readln().toIntOrNull()
                    if (id == null) {
                        println("Invalid selection, please enter a valid selection.")
                        return search()
                    }
                    val locateId = products.indexOfFirst { it.getProductId() == id }
                    val locateId2 = products.find { it.getProductId() == id }
                    if (locateId == -1) {
                        println("Product does not exist!")
                        return search()
                    }
                    if (locateId2 != null) {
                        println("Product: ")
                        println("Product Name: ${locateId2.getProductName()}")
                        println("Product id: ${locateId2.getProductId()}")
                        println("Product category: ${locateId2.getProductCategory()}")
                        println("Product Brand: ${locateId2.getProductBrand()}")
                        println("Product price: ${locateId2.getProductPrice()}")
                        println("Product quantity: ${locateId2.getProductQuantity()}")
                        println("")
                    } else {
                        println("Product does not exist!")
                    }


                }

                3 -> {
                    return
                }

                else -> {
                    println("Invalid selection, please enter a valid selection.")
                    return search()
                }
            }


        } else {
            println("There are no products")
            println()
        }


    }

    private fun generatorProductId(): String {
        val rand = Random
        var newNumber: String

        do {
            newNumber = (1..6).map { rand.nextInt(0, 10) }.joinToString("")
        } while (idBase.containsKey(newNumber.toInt()))
        return newNumber
    }
}