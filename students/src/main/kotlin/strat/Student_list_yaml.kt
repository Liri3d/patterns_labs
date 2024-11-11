package main.kotlin.strat

import main.kotlin.pattern.*
import java.io.File

class Student_list_yaml(private var students: MutableList<Student> = mutableListOf()) {

    constructor() : this(mutableListOf())

    constructor(filePath: String) : this() {
        read_from_file(filePath)
    }

    fun read_from_file(filePath: String) {
        val file = File(filePath)
        if (!file.exists() || !file.isFile) {
            throw IllegalArgumentException("Некорректный адрес файла: $filePath")
        }

        try {
            val lines = file.readLines()
            students.clear()
            for (line in lines) {
                val studentData = line.split(", ")
                if (studentData.size >= 5) {  // Убедитесь, что есть хотя бы 5 элементов
                    val id = studentData[0].trim().toInt()
                    val lastName = studentData[1].trim()
                    val firstName = studentData[2].trim()
                    val middleName = studentData[3].trim()
                    val telegram = if (studentData.size > 4) studentData[4].trim() else null
                    val git = if (studentData.size > 5) studentData[5].trim() else null
                    students.add(Student(id, lastName, firstName, middleName, telegram, git))
                }
            }
        } catch (e: Exception) {
            throw IllegalArgumentException("Ошибка при чтении файла: ${e.message}")
        }
    }

//    fun write_to_file(directory: String, fileName: String) {
//        val file = File(directory, fileName)
//
//        try {
//            file.parentFile?.mkdirs()
//            val yamlString = buildString {
//                for (student in students) {
//                    append("${student.name}: ${student.age}\n")
//                }
//            }
//            file.writeText(yamlString)
//        } catch (e: Exception) {
//            throw IllegalArgumentException("Ошибка при записи в файл: ${e.message}")
//        }
//    }
}