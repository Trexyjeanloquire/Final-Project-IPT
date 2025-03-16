import models.Manager
import models.Product
import models.Staff
import services.ValidationServices
import kotlin.system.exitProcess


fun main() {

    val products = mutableListOf<Product>()
    val managerAccounts = mutableListOf<Manager>()
    val staffAccounts = mutableListOf<Staff>()
    val idBase = hashMapOf<Int, MutableList<Product>>()
    val categoryBase = hashMapOf<String, MutableList<Product>>()
    val validation = ValidationServices(products, idBase, categoryBase, managerAccounts, staffAccounts)


    println("!!Welcome to the Inventory Management!!")

    while (true) {

        println(
            """
            1. Admin
            2. Manager
            3. Staff
            4. Exit
            Enter Selection: 
        """.trimIndent()
        )
        val selection = readln().toIntOrNull()

        when (selection) {
            1 -> {
                validation.adminLogin()
            }

            2 -> {
                validation.managerLoginChoices()
            }

            3 -> {
                validation.staffLoginChoice()
            }

            4 -> {
                exitProcess(0)
            }

            else -> {
                println("Invalid Choice, Please input a valid selection")
                println()

            }

        }


    }


}