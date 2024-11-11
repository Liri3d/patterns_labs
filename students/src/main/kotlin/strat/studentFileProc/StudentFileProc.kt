package main.kotlin.strat.studentFileProc

import main.kotlin.pattern.Student

// Интерфейс для классов, предназначенных для реализации работы с разным видом записи и чтения из файлов
interface StudentFileProc {
    fun read_from_file(filePath: String): MutableList<Student>

    fun write_to_file(students: MutableList<Student>, directory: String, fileName: String)

}