package main.kotlin

import main.kotlin.pattern.Student
import main.kotlin.pattern.Student_short
import main.kotlin.pattern.Data_list_student_short


fun main() {
    DataTableGo();
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
    val student1 = Student(
        1,
        "Иванов",
        "Иван",
        "Иванович",
        email = "ivan.ivan@example.com",
        git = "ivan"
    )

    // Создаем Map с данными студента
    val student2 = Student(mapOf(
        "id" to 1,
        "lastName" to "Иванов",
        "firstName" to "Иван",
        "middleName" to "Иванович",
        "phone" to "+1 (234) 567890",
        "telegram" to "@ivanov",
        "email" to "ivan@example.com",
        "git" to "github.com/ivanov"
    )
    )
    println(student2.getInfo())


    val studentFromString = Student("Student(id=2, firstName=Петр, lastName=Иванов, middleName=Иванович, phone=+7 (123) 456-78-90, telegram=null, email=ivan@example.com, git=ivan_ggit)");
    println(studentFromString.getInfo())

    val studentForShort = Student(
        id = 3,
        lastName = "Иванов",
        firstName = "Василий",
        middleName = "Иванович",
        telegram = "@valis",
        git = "ivanov-vas-git"
    )

//    Student.write_to_txt("src/files", "students.txt", listOf(student1, studentForShort, studentFromString))
//    val students = Student.read_from_txt("src/files/students.txt")
//    println("Студенты из файла:")
//    students.forEach { println(it.getInfo()) }
}


fun isValidStudent(student: Student) {
    val studentName = student.lastName
    if (student.validate()) {
        println("Даннные студента $studentName корректны и полны");
    } else {
        println("Даннные студента $studentName некорректные либо неполные");
    }
}