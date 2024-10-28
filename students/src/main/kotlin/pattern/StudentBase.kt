package main.kotlin.pattern
import kotlinx.serialization.Serializable

@Serializable
abstract class StudentBase() {

    // Получить контакты
    abstract fun getContactInfo(): String

    abstract fun getGitInfo(): String?

    // Получить фамилию и инициалы
    abstract fun getLastNameWithInitials(): String

    // Получение краткой информации
    fun getInfo(): String {
        val lastNameWithInitials = getLastNameWithInitials()
        val contactInfo = getContactInfo()
        val git = getGitInfo()
        return "$lastNameWithInitials; $git; $contactInfo"
    }
}