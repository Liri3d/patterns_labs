package main.kotlin.pattern

abstract class StudentBase(
    open val id: Int,
    open var git: String? = null
) {

    // Получить контакты
    abstract fun getContactInfo(): String

    // Получить фамилию и инициалы
    abstract fun getLastNameWithInitials(): String

    // Получение краткой информации
    fun getInfo(): String {
        val lastNameWithInitials = getLastNameWithInitials()
        val contactInfo = getContactInfo()
        return "$lastNameWithInitials; $git; $contactInfo"
    }
}