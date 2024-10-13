import java.io.File
import java.io.FileNotFoundException

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

data class Student(
    override val lastName: String,
    override val firstName: String,
    override val middleName: String,
    override val gitLink: String,
    override val contactMethod: String,
    override val contactValue: String
) : BaseStudent(lastName, firstName, middleName, gitLink, contactMethod, contactValue) {
    companion object {
        private const val DELIMITER = ";"

        // Метод для создания объекта Student из строки
        fun fromString(str: String): Student {
            val parts = str.split(DELIMITER)
            if (parts.size != 6) {
                throw IllegalArgumentException("Неверная входная строка: $str")
            }

            return Student(
                lastName = parts[0],
                firstName = parts[1],
                middleName = parts[2],
                gitLink = parts[3],
                contactMethod = parts[4],
                contactValue = parts[5]
            )
        }

        // Метод для чтения студентов из текстового файла
        fun readFromTxt(filePath: String): Array<Student> {
            // Проверка корректности пути
            val file = File(filePath)
            if (!file.exists() || !file.isFile) {
                throw IllegalArgumentException("Некорректный адрес файла: $filePath")
            }

            val students = mutableListOf<Student>()

            try {
                file.forEachLine { line ->
                    // Создаем объект Student из строки
                    val student = fromString(line) // Используем метод fromString
                    students.add(student) // Добавляем студента в список
                }
            } catch (e: FileNotFoundException) {
                throw IllegalArgumentException("Файл не найден: $filePath")
            } catch (e: IllegalArgumentException) {
                throw IllegalArgumentException("Ошибка в данных: ${e.message}")
            }

            return students.toTypedArray() // Возвращаем массив
        }
    }
}

class Student_short(
    val id: Int,
    lastName: String,
    firstName: String,
    middleName: String,
    gitLink: String,
    contactMethod: String,
    contactValue: String
) : BaseStudent(lastName, firstName, middleName, gitLink, contactMethod, contactValue) {

    // Конструктор, принимающий объект Student
    constructor(student: Student) : this(
        id = 0, // Задаём ID по умолчанию
        lastName = student.lastName,
        firstName = student.firstName,
        middleName = student.middleName,
        gitLink = student.gitLink,
        contactMethod = student.contactMethod,
        contactValue = student.contactValue
    )

    // Конструктор, принимающий ID и строку
    constructor(id: Int, info: String) : this(
        id = id,
        lastName = info.substringBefore(";"),
        firstName = info.substringAfter(";").substringBefore(";"),
        middleName = info.substringAfter(";").substringAfter(";").substringBefore(";"),
        gitLink = info.substringAfter(";").substringAfter(";").substringAfter(";").substringBefore(";"),
        contactMethod = "",
        contactValue = info.substringAfter(";").substringAfter(";").substringAfter(";").substringAfter(";")
    )



    override fun getInfo(): String {
        return (//"ID: $id\n" +
                "ФИО: ${getFullName()}\n" +
                "Гит: ${getGitInfo()}\n" +
                "${getContactInfo()}\n")
    }
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
        println(e.message + "\n") // Вывод ошибки
    }

    // Создание объекта Student_short из объекта Student
    val studentShortFromStudent = Student_short(student)
    println(studentShortFromStudent.getInfo())

    // Создание объекта Student_short из ID и строки
    val studentShortFromString = Student_short(1, "Петров;Петр;Петрович;https://github.com/petrov;Телефон: 123-456-7890")
    println(studentShortFromString.getInfo())

    // Тестирование метода
    val filePath = "C:/Users/User/Documents/Patterns_labs/students.txt" // Укажите путь к вашему текстовому файлу
    try {
        val students = Student.readFromTxt(filePath) // Чтение студентов из файла

        // Создание объектов Student_short из объектов Student
        students.forEachIndexed { index, student ->
            val studentShort = Student_short(student) // Используем объект Student
            println(studentShort.getInfo())
        }
    } catch (e: IllegalArgumentException) {
        println(e.message)
    }
}
