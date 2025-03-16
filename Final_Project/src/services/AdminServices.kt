package services

import models.Manager
import models.Product
import models.Staff

class AdminServices(
    products: MutableList<Product>, idBase: HashMap<Int, MutableList<Product>>,
    categoryBase: HashMap<String, MutableList<Product>>, private val managerAccounts: MutableList<Manager>,
    private val staffAccounts: MutableList<Staff>
) {


    private val productServices = ProductServices(products, idBase, categoryBase)

    fun choices() {
        println(
            """
            1. Add Product
            2. Display All Products
            3. Display All Products using Category
            4. Update a Product
            5. Delete a Product
            6. Search a product
            7. Create Account for Staff or Manager
            8. Log out
          
            Enter Selection: 
        """.trimIndent()
        )
        val choice = readln().toIntOrNull()

        if (choice == null) {
            println("Invalid selection, please enter a valid selection.")
            return choices()
        }



        when (choice) {
            1 -> {
                productServices.addProduct()
                return choices()
            }

            2 -> {
                productServices.showAll()
                return choices()
            }

            3 -> {
                productServices.showProdCategory()
                return choices()
            }

            4 -> {
                productServices.updateProduct()
                return choices()
            }

            5 -> {
                productServices.deleteProduct()
                return choices()
            }

            6 -> {
                productServices.search()
                return choices()
            }

            7 -> {
                createAccounts()
                return choices()
            }

            8 -> {
                println("Successfully logged out!")
                return
            }


            else -> {
                println("Invalid selection, please enter a valid selection.")
                println()
                return choices()
            }
        }


    }


    private fun createAccounts() {

        println(
            """
            1. Manager
            2. Staff
            3. Back
            Enter Selection: 
        """.trimIndent()
        )
        val choice = readln().toIntOrNull()
        if (choice == null) {
            println("Invalid selection, please enter a valid selection.")
            return createAccounts()
        }


        when (choice) {
            1 -> {
                println("Enter a Manager Name: ")
                val name = readlnOrNull()?.trim()

                if (name.isNullOrEmpty()) {
                    println("Invalid name, please enter  a valid name.")
                    println()
                    return createAccounts()
                }
                println("Enter a Manager password: ")
                val password = readlnOrNull()?.trim()

                if (password.isNullOrEmpty()) {
                    println("Invalid password, please enter  a valid password.")
                    println()
                    return createAccounts()
                }
                val contains = managerAccounts.find { it.getManagerName() == name }
                if (contains == null) {
                    val manager = Manager(name, password)
                    managerAccounts.add(manager)
                    println("Successfully created the account!")
                    println()
                    return
                } else {
                    println("manager name already exist, please enter a  unique one.")
                    println()
                    return createAccounts()

                }
            }

            2 -> {
                println("Enter a staff Name: ")
                val name = readlnOrNull()?.trim()

                if (name.isNullOrEmpty()) {
                    println("Invalid name, please enter  a valid name.")
                    println()
                    return createAccounts()
                }
                println("Enter a staff password: ")
                val password = readlnOrNull()?.trim()

                if (password.isNullOrEmpty()) {
                    println("Invalid password, please enter  a valid password.")
                    println()
                    return createAccounts()
                }
                val contains = staffAccounts.find { it.getStaffName() == name }
                if (contains == null) {
                    val staff = Staff(name, password)
                    staffAccounts.add(staff)
                    println(staff)
                    println("Successfully created the account!")
                    println()
                    return
                } else {
                    println("staff name already exist, please enter a  unique one.")
                    println()
                    return createAccounts()

                }
            }

            3 -> {
                return
            }

            else -> {
                println("Invalid selection, please enter a valid selection.")
                println()
                return createAccounts()
            }
        }


    }

}