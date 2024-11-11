package main.kotlin.strat

import kotlinx.serialization.*
import main.kotlin.pattern.*
import java.io.File
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Student_list_json(students: MutableList<Student> = mutableListOf()) :
Student_base_list(students) {

    constructor() : this(mutableListOf())

    constructor(filePath: String) : this() {
        read_from_file(filePath)
    }

    override fun read_from_file(filePath: String) {
        val file = File(filePath)
        if (!file.exists() || !file.isFile) {
            throw IllegalArgumentException("Некорректный адрес файла: $filePath")
        }

        try {
            val gson = Gson()
            val studentListType = object : TypeToken<MutableList<Student>>() {}.type
            students = gson.fromJson(file.readText(), studentListType) ?: mutableListOf()
        } catch (e: Exception) {
            throw IllegalArgumentException("Ошибка при чтении файла JSON: ${e.message}", e)
        }
    }

    override fun write_to_file(directory: String, fileName: String) {
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