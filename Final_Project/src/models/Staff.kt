package models

data class Staff(
    private val staffName: String?,
    private val staffPassword: String?

){

    fun getStaffName(): String?{
        return staffName
    }

    fun getStaffPassword(): String?{
        return staffPassword
    }



}
