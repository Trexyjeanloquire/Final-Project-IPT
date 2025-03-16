package services

import models.Admin
import models.Manager
import models.Product
import models.Staff

class ValidationServices(
    products: MutableList<Product>, idBase: HashMap<Int, MutableList<Product>>,
    categoryBase: HashMap<String, MutableList<Product>>, private val managerAccounts: MutableList<Manager>,
    private val staffAccounts: MutableList<Staff>
) {
    private val managerServices = ManagerServices(products, idBase, categoryBase)
    private val admin = Admin()
    private val adminServices = AdminServices(products, idBase, categoryBase, managerAccounts, staffAccounts)
    private val staffServices = StaffServices(products, idBase, categoryBase)


    fun adminLogin() {
        println("Enter a admin name: ")
        val name = readlnOrNull()?.trim()
        if (name.isNullOrEmpty()) {
            println("Invalid admin name, please enter a valid admin name")
            println()
            return adminLogin()
        }

        println("Enter a admin password: ")
        val password = readlnOrNull()?.trim()
        if (password.isNullOrEmpty()) {
            println("Invalid admin name, please enter a valid admin name")
            println()
            return adminLogin()
        }

        if (name == admin.getAdminName() && password == admin.getAdminPassword()) {
            println("Welcome back $name!")
            println()
            adminServices.choices()
        } else {
            println("Wrong admin name or password")

        }

    }


    private fun managerLogin() {
        println("Enter the manager name: ")
        val name = readlnOrNull()?.trim()
        if (name.isNullOrEmpty()) {
            println("Invalid manager name, please enter a valid manager name")
            println()
            return managerLogin()
        }
        println("Enter the manager password: ")
        val password = readlnOrNull()?.trim()
        if (password.isNullOrEmpty()) {
            println("Invalid manager password, please enter a valid manager password")
            println()
            return
        }

        val locateIndex = managerAccounts.indexOfFirst { it.getManagerName() == name }
        val locateIndex2 = managerAccounts.indexOfFirst { it.getManagerPassword() == password }
        val locateName = managerAccounts.find { it.getManagerName() == name }
        val locatePass = managerAccounts.find { it.getManagerPassword() == password }

        if (locateName == null || locatePass == null) {
            println("Wrong password or name.")
            println()
            return
        }
        if (locateIndex != locateIndex2) {
            println("Wrong password or name.")
            println()
            return
        }
        println("Successfully logged in")
        println()
        managerServices.choice()

    }

    fun managerLoginChoices() {
        print(
            """
            1. Login
            2. Back
            Enter a selection: 
        """.trimIndent()
        )
        val choice = readln().toIntOrNull()

        if (choice == null) {
            println("Invalid selection, please enter  a valid selection.")
            println()
            return managerLoginChoices()
        }

        when (choice) {
            1 -> {
                managerLogin()
            }

            2 -> {
                return
            }

            else -> {
                println("Invalid selection, please enter  a valid selection.")
                println()
                return managerLoginChoices()
            }
        }


    }

    fun staffLoginChoice() {
        print(
            """
            1. Login
            2. Back
            Enter a selection: 
        """.trimIndent()
        )
        val choice = readln().toIntOrNull()

        if (choice == null) {
            println("Invalid selection, please enter  a valid selection.")
            println()
            return staffLoginChoice()
        }

        when (choice) {
            1 -> {
                staffLogin()
            }

            2 -> {
                return
            }

            else -> {
                println("Invalid selection, please enter  a valid selection.")
                println()
                return staffLoginChoice()
            }


        }
    }


    private fun staffLogin() {
        println("Enter the staff name: ")
        val name = readlnOrNull()?.trim()
        if (name.isNullOrEmpty()) {
            println("Invalid staff name, please enter a valid staff name")
            println()
            return staffLogin()
        }
        println("Enter the staff password: ")
        val password = readlnOrNull()?.trim()
        if (password.isNullOrEmpty()) {
            println("Invalid staff password, please enter a valid staff password")
            println()
            return
        }

        val staffAccount = staffAccounts.find { it.getStaffName() == name && it.getStaffPassword() == password }
        val index = staffAccounts.indexOfFirst { it.getStaffName() == name }
        val index2 = staffAccounts.indexOfFirst { it.getStaffPassword() == password }
        if (staffAccount == null) {
            println("Wrong password or name.")
            println()
            return
        }

        if (index != index2) {
            println("Wrong password or name.")
            println()
            return
        }



        println("Successfully logged in")
        println()
        staffServices.staffChoices()
    }
}

