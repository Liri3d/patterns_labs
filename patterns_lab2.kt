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



class Student_short(
    val id: Int,
    val fullName: String,
    val git: String,
    val contact: String
) {
    // Конструктор, принимающий объект Student
    constructor(student: Student) : this(
        id = 0, // Можно указать 0 или любое другое значение при необходимости
        fullName = "${student.lastName} ${student.firstName.first()}.${student.middleName.first()}.",
        git = student.gitLink,
        contact = "${student.contactMethod}: ${student.contactValue}"
    )

    // Конструктор, принимающий ID и строку
    constructor(id: Int, info: String) : this(
        id = id,
        fullName = info.substringBefore(";"),
        git = info.substringAfter(";").substringBefore(";"),
        contact = info.substringAfter(";").substringAfter(";")
    )
}




fun main() {
    // Пример использования класса Student
    val studentString = "Иванов;Иван;Иванович;https://github.com/ivanov;Email;ivanov@example.com"
    val student = Student.fromString(studentString)

    // Обработка ошибок
    try {
        val invalidStudentString = "Иванов;Иван;Иванович;https://github.com/ivanov" // недостающие данные
        val invalidStudent = Student.fromString(invalidStudentString)
    } catch (e: IllegalArgumentException) {
        println(e.message) // Вывод ошибки
    }
    
    // Создание объекта Student_short из объекта Student
    val studentShortFromStudent = Student_short(student)
    println("ID: ${studentShortFromStudent.id}, ФИО: ${studentShortFromStudent.fullName}, Git: ${studentShortFromStudent.git}, Контакт: ${studentShortFromStudent.contact}")

    // Создание объекта Student_short из ID и строки
    val studentShortFromString = Student_short(1, "Петров П.П.;https://github.com/petrov;Телефон: 123-456-7890")
    println("ID: ${studentShortFromString.id}, ФИО: ${studentShortFromString.fullName}, Git: ${studentShortFromString.git}, Контакт: ${studentShortFromString.contact}")
}
