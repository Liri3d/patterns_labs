package main.kotlin

import com.google.gson.Gson
import kotlinx.serialization.json.Json
import main.kotlin.pattern.Student
import main.kotlin.pattern.Student_short
import main.kotlin.strat.Student_list_json
import main.kotlin.strat.Student_list_txt
import main.kotlin.pattern.Data_list_student_short


fun main() {
    //DataTableGo();
    StudentsGo();
}

fun DataTableGo() {
    val students = listOf(
        Student_short(1, "Иванов И.И.", "https://github.com/ivanov", "Telegram: @ivan"),
        Student_short(2, "Петров П.П.", "https://github.com/petrov", "Email: ptr@gmail.com"),
        Student_short(3, "Сидоров С.С.", "https://github.com/sidorov", "Phone: +7 (999) 222-11-11")
    )

    val studentList = Data_list_student_short(students)

    println("Названия столбцов: ${studentList.getNames()}")

    studentList.select(0)
    studentList.select(2)
    println("Выбранные элементы: ${studentList.getSelected()}")

    val dataTable = studentList.getData()
    println("Количество строк: ${dataTable.getRowCount()}")
    println("Количество столбцов: ${dataTable.getColumnCount()}")

// Вывод данных таблицы
    for (i in 0 until dataTable.getRowCount()) {
        for (j in 0 until dataTable.getColumnCount()) {
            print("${dataTable.getElement(i, j)} ")
        }
        println()
    }


}

fun StudentsGo() {
    val inputFilePath = "src/files/students.txt"
    val studentList = Student_list_txt(inputFilePath)
    println("Студенты прочитаны из файла " + inputFilePath)
    studentList.get_student_short_count()

    // Добавить нового студента
    val newStudent = Student(id=4, firstName="Игнат", lastName="Прохоров", middleName="Рустамович", phone="+7 (374) 934-93-56", telegram="@rustam", email=null, git="rustam-vas-git")
    studentList.add(newStudent)
    println("Добавлен новый студент: $newStudent")

    // Сортировка студентов по фамилии
    studentList.orderStudentsByLastNameInitials()
    println("Список студентов отсортирован по фамилии:")
    studentList.getStudents().forEach { println(it) }

    // Поиск студента по ID
    val searchId = 1 // Замените на реальный ID
    try {
        val foundStudent = studentList.findById(searchId)
        println("Найден студент: $foundStudent")
    } catch (e: NoSuchElementException) {
        println("Студент с ID $searchId не найден.")
    }

    // Удаление студента по ID
    val removeId = 2 // Замените на реальный ID
    studentList.removeById(removeId)
    println("Студент с ID $removeId удален.")

    // Получить k элементов, начиная с n
    val n = 1 // Начальный индекс
    val k = 2 // Количество студентов для получения
    val shortList = studentList.get_k_n_student_short_list(n, k)

    println("k = $k студентов, начиная с n = $n:")
    shortList.getItems().forEach { studentShort ->
        println(studentShort)
    }

    // Записываем студентов в файл
    val outputDirectory = "src/files"
    val outputFileName = "output_students.txt"
    studentList.write_to_file(outputDirectory, outputFileName)
    println("Данные студентов записаны в $outputDirectory/$outputFileName")












    val filePathJson = "src/files/students.json"
    val studentListJson = Student_list_json(filePathJson)
    println("\n\n\nСтуденты прочитаны из файла " + filePathJson)

    // 1. Поиск студента по ID
    val studentIdToFind = 1
    try {
        val foundStudent = studentListJson.findById(studentIdToFind)
        println("Найден студент: $foundStudent")
    } catch (e: Exception) {
        println(e.message)
    }

    // 2. Получение короткого списка студентов
    val n1 = 0     // Начальный индекс
    val k1 = 5    // Количество студентов для получения
    val shortListJson = studentListJson.get_k_n_student_short_list(n, k)
    println("Список студентов (по $n1 -му индексу, $k1 студентов): $shortListJson")

    // 3. Сортировка студентов по фамилии и инициалам
    studentListJson.orderStudentsByLastNameInitials()
    println("Студенты отсортированы по фамилии и инициалам.")

    // 4. Добавление нового студента
    val newStudentJson = Student(id=4, firstName="Игнат", lastName="Прохоров", middleName="Рустамович", phone="+7 (374) 934-93-56", telegram="@rustam", email=null, git="rustam-vas-git")
    studentListJson.add(newStudent)
    println("Добавлен новый студент: $newStudent")

    // 5. Замена студента по ID
    val studentToReplace = Student(id=4, firstName="Игнат", lastName="Прохоров", middleName="Рустамович", phone="+7 (374) 934-93-56", telegram="@rustam", email=null, git="rustam-vas-git")
    try {
        studentListJson.replaceById(studentToReplace, studentIdToFind)
        println("Студент с ID $studentIdToFind заменен.")
    } catch (e: Exception) {
        println(e.message)
    }

    // 6. Удаление студента по ID
    try {
        studentListJson.removeById(studentIdToFind)
        println("Студент с ID $studentIdToFind удален.")
    } catch (e: Exception) {
        println(e.message)
    }

    // 7. Получение количества студентов
    val count = studentListJson.get_student_short_count()
    println("Количество студентов в списке: $count")


    val directory = "src/files"
    val fileName = "output_students.json"
    studentListJson.write_to_file(directory, fileName)
    println("Список студентов успешно записан в файл $fileName.")
}

fun checkValidStudent(student: Student) {
    val studentName = student.lastName
    if (student.validate()) {
        println("Student $studentName is valid");
    } else {
        println("Student $studentName is not valid");
    }
}