import java.io.File
import java.io.FileNotFoundException
import java.io.FileWriter
import java.io.IOException

fun main() {
    val studentsArray = mutableListOf<Student>()

    // Пример использования класса Student
    val studentString = "Иванов;Иван;Иванович;https://github.com/ivanov;Email;ivanov@example.com"
    val student = Student.fromString(studentString)
    studentsArray.add(student)
    println("---Создание объекта Student----------")
    println(student.getInfo())

    // Обработка ошибок
    try {
        val invalidStudentString = "Иванов;Иван;Иванович;https://github.com/ivanov" // недостающие данные
        val invalidStudent = Student.fromString(invalidStudentString)
    } catch (e: IllegalArgumentException) {
        println("\n" + e.message + "\n") // Вывод ошибки
    }

    println("---Создание объекта Student_short из объекта Student----------")
    val studentShortFromStudent = Student_short(student)
    println(studentShortFromStudent.getInfo())

    println("---Создание объекта Student_short из ID и строки----------")
    val studentShortFromString = Student_short(1, "Петров;Петр;Петрович;https://github.com/petrov;Телефон: 123-456-7890")
    println(studentShortFromString.getInfo())

    println("---Чтение из файла----------")
    val filePath = "C:/Users/User/Documents/Patterns_labs/students.txt" // Укажите путь к вашему текстовому файлу
    try {
        val students = Student.readFromTxt(filePath) // Чтение студентов из файла

        // Создание объектов Student_short из объектов Student
        students.forEachIndexed { index, student ->
            val studentShort = Student_short(student) // Используем объект Student
            studentsArray.add(student)
            println(studentShort.getInfo())
        }
    } catch (e: IllegalArgumentException) {
        println(e.message)
    }

    println("----------------------------")
    for (student in studentsArray) {
        println(student.getInfo() + "\n")
    }

    // Запись массива студентов в файл
    val filePath_output = "C:/Users/User/Documents/Patterns_labs/students_output.txt"
    Student.write_to_txt(filePath_output, studentsArray)
}