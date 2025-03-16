package models

data class Manager(
    private val managerName: String?,
    private val managerPassword: String?,

){

    fun getManagerName(): String?{
        return managerName
    }

    fun getManagerPassword(): String?{
        return managerPassword
    }


}
