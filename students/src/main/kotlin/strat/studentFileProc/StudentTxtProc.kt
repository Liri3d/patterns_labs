package main.kotlin.strat.studentFileProc


import main.kotlin.strat.Student_list
import main.kotlin.pattern.Student

import java.io.File
import java.io.FileNotFoundException

// Работа с коллекцией об-ов Student и получ их из файла
class StudentTxtProc: StudentFileProc {
    override fun read_from_file(filePath: String): MutableList<Student> {
        val file = File(filePath)
        if (!file.exists() || !file.isFile) {
            throw IllegalArgumentException("Некорректный адрес файла: $filePath")
        }

        try {
            return file.readLines().mapNotNull { line ->
                try {
                    Student(line)
                } catch (e: IllegalArgumentException) {
                    println("Ошибка при разборе строки: $line")
                    println("Причина: ${e.message}")
                    null
                }
            }.toMutableList()
        } catch (e: FileNotFoundException) {
            throw IllegalArgumentException("Файл не найден: $filePath")
        } catch (e: Exception) {
            throw IllegalArgumentException("Ошибка при чтении файла: ${e.message}")
        }
    }

    // Список студентов в файл
    override fun write_to_file(students: MutableList<Student>, directory: String, fileName: String) {
        val file = File(directory, fileName)

        try {
            file.parentFile?.mkdirs()
            file.bufferedWriter().use { writer ->

                students.forEach { student ->
                    writer.write(student.toString())
                    writer.newLine()
                }
            }
        } catch (e: Exception) {
            throw IllegalArgumentException("Ошибка при записи в файл: ${e.message}")
        }
    }
}