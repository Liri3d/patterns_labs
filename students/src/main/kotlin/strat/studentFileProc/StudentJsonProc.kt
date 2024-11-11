package main.kotlin.strat.studentFileProc

import main.kotlin.pattern.*
import main.kotlin.strat.Student_list
import main.kotlin.strat.studentFileProc.*

import java.io.File
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlin.contracts.Returns

class StudentJsonProc: StudentFileProc {
    override fun read_from_file(filePath: String): MutableList<Student> {
        val file = File(filePath)
        val students: MutableList<Student>
        if (!file.exists() || !file.isFile) {
            throw IllegalArgumentException("Некорректный адрес файла: $filePath")
        }

        try {
            val gson = Gson()
            val studentListType = object : TypeToken<MutableList<Student>>() {}.type
            students = gson.fromJson(file.readText(), studentListType) ?: mutableListOf()
            return students
        } catch (e: Exception) {
            throw IllegalArgumentException("Ошибка при чтении файла JSON: ${e.message}", e)
        }
    }

    override fun write_to_file(students: MutableList<Student>, directory: String, fileName: String) {
        val file = File(directory, fileName)

        try {
            file.parentFile?.mkdirs() // Создание директорий, если их нет
            val gson = Gson()
            val json = gson.toJson(students) // Сериализация списка студентов в JSON
            file.writeText(json) // Запись JSON в файл
        } catch (e: Exception) {
            throw IllegalArgumentException("Ошибка при записи в файл JSON: ${e.message}", e)
        }
    }
}