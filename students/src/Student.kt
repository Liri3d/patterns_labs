import java.io.File
import java.io.FileNotFoundException
import java.io.FileWriter
import java.io.IOException

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

        fun write_to_txt(filePath: String, students: List<Student>) {
            try {
                val fileWriter = FileWriter(filePath)
                students.forEach { student ->
                    val line = "${student.lastName};${student.firstName};${student.middleName};${student.gitLink};${student.contactMethod};${student.contactValue}"
                    fileWriter.write(line + "\n")
                }
                fileWriter.close()
            } catch (e: IOException) {
                throw IllegalArgumentException("Error writing to file: $filePath")
            }
        }
    }

}