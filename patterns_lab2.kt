data class Student(
    val lastName: String,
    val firstName: String,
    val middleName: String,
    val gitLink: String,
    val contactMethod: String,
    val contactValue: String
) {
    companion object {
        private const val DELIMITER = ";"

        // Метод для создания объекта Student из строки
        fun fromString(str: String): Student {
            val parts = str.split(DELIMITER)
            if (parts.size != 6) {
                throw IllegalArgumentException("Неверная входная строка: $str")
            }

            val lastName = parts[0]
            val firstName = parts[1]
            val middleName = parts[2]
            val gitLink = parts[3]
            val contactMethod = parts[4]
            val contactValue = parts[5]

            return Student(lastName, firstName, middleName, gitLink, contactMethod, contactValue)
        }
    }

    // Метод для получения информации о студенте
    fun getInfo(): String {
        return "${getFullName()}\n" +
               "${getGitInfo()}\n" +
               "${getContactInfo()}\n"
    }

    // Метод для получения фамилии и инициалов
    private fun getFullName(): String {
        return "$lastName ${firstName.first()}.${middleName.first()}."
    }

    // Метод для получения информации о гите
    private fun getGitInfo(): String {
        return "Git: $gitLink"
    }

    // Метод для получения контактной информации
    private fun getContactInfo(): String {
        return "Контакт: $contactMethod: $contactValue"
    }
}

fun main() {
    // Пример с валидной строкой
    val studentString = "Иванов;Иван;Иванович;https://github.com/ivanov;Email;ivanov@example.com"
    val student = Student.fromString(studentString)

    println(student.getInfo()) // Вывод: Иванов И.И. | Git: https://github.com/ivanov | Контакт: Email: ivanov@example.com

    // Обработка ошибок
    try {
        val invalidStudentString = "Иванов;Иван;Иванович;https://github.com/ivanov" // недостающие данные
        val invalidStudent = Student.fromString(invalidStudentString)
    } catch (e: IllegalArgumentException) {
        println(e.message) // Вывод ошибки
    }
}
