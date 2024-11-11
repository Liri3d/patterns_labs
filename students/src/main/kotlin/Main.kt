package main.kotlin

import com.google.gson.Gson
import kotlinx.serialization.json.Json
import main.kotlin.pattern.Student
import main.kotlin.pattern.Student_short
import main.kotlin.strat.Student_list_json
import main.kotlin.strat.Student_list_txt
import main.kotlin.pattern.Data_list_student_short
import main.kotlin.strat.Student_list_yaml

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

    val outputFileName = "output_students.txt"
    studentList.write_to_file("src/files", outputFileName)
    println("Данные студентов записаны в $outputFileName")





    val filePathJson = "src/files/students.json"
    val studentListJson = Student_list_json(filePathJson)
    println("Студенты прочитаны из файла " + filePathJson)

    println("Список 3 из 1: " + studentListJson.get_k_n_student_short_list(3, 1))

    // Заменим студента с ID 2 на нового студента
    val newStudent = Student("Student(id=2, firstName=Abram, lastName=Abramov, middleName=Abramovich, phone=+7 (123) 456-78-90, telegram=null, email=abram.abram@example.com, git=abramabram)");
    studentListJson.replaceById(newStudent, 2)
    println("\nЗаменен студент с id 2 ")

    println("Удален студент с id 1 ")
    studentListJson.removeById(1)

    val n = studentListJson.get_student_short_count()
    println("Кол-во студентов = " + n)

    val fileNameJson = "output_students.json"
    studentListJson.write_to_file("src/files", fileNameJson)
    println("Список студентов успешно записан в файл $fileNameJson.")





    val filePathYaml = "src/files/students.yaml"
    val studentListYaml = Student_list_yaml(filePathYaml)
    println("\nСтуденты прочитаны из файла " + filePathYaml)

    val nyaml = studentListYaml.get_student_short_count()
    println("Кол-во студентов = " + nyaml)

    val fileNameYaml = "output_students.yaml"
    studentListYaml.write_to_file("src/files", fileNameYaml)
    println("Список студентов успешно записан в файл $fileNameYaml")
}

fun checkValidStudent(student: Student) {
    val studentName = student.lastName
    if (student.validate()) {
        println("Student $studentName is valid");
    } else {
        println("Student $studentName is not valid");
    }
}