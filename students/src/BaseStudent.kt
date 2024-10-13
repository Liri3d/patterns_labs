// Суперкласс
open class BaseStudent(
    open val lastName: String,
    open val firstName: String,
    open val middleName: String,
    open val gitLink: String,
    open val contactMethod: String,
    open val contactValue: String
) {
    // Метод для получения фамилии и инициалов
    fun getFullName(): String {
        val initials = if (firstName.isNotEmpty()) "${firstName.first()}." else "."
        val middleInitials = if (middleName.isNotEmpty()) "${middleName.first()}." else "."
        return "$lastName $initials$middleInitials"
    }

    // Метод для получения информации о гите
    fun getGitInfo(): String {
        return "Git: $gitLink"
    }

    // Метод для получения контактной информации
    fun getContactInfo(): String {
        return "Контакт $contactMethod: $contactValue"
    }

    // Метод для получения полной информации
    open fun getInfo(): String {
        return "${getFullName()}\n" +
                "${getGitInfo()}\n" +
                "${getContactInfo()}"
    }
}
