package models

class Admin {
    private val adminName: String = "admin123"
    private val adminPassword: String = "admin123"


    fun getAdminName(): String{
        return adminName
    }

    fun getAdminPassword(): String{
        return adminPassword
    }

}