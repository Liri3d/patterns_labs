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

//    override fun findById(id: Int): Student {
//        return students.first { it.id == id }
//    }
//
//    override fun get_k_n_student_short_list(n: Int, k: Int): Data_list<Student_short> {
//        require(n >= 0) { "Индекс n должен быть больше или равен 0." }
//        require(k > 0) { "Количество k должно быть больше 0." }
//
//        return Data_list_student_short(students
//            .drop(n)
//            .take(k)
//            .map { Student_short(it) }
//        )
//    }
//
//    // Сортировка списка по фамилии и инициалам
//    fun orderStudentsByLastNameInitials() {
//        orderStudents(compareBy { it.getLastNameWithInitials() })
//    }
//
//    // Сортировка списка
//    fun orderStudents(comparator: Comparator<Student>) {
//        students.sortedWith(comparator)
//    }
//
//    // Добавление объекта
//    fun add(student: Student) {
//        val nextId = (students.maxByOrNull { it.id }?.id ?: 0) + 1
//        student.id = nextId
//
//        students.addLast(student)
//    }
//
//    // Заменить объект по ID
//    fun replaceById(student: Student, id: Int) {
//        student.id = id
//        students.replaceAll { if (it.id == id) student else it }
//    }
//
//    // Удалить по ID
//    fun removeById(id: Int) {
//        students.removeIf { it.id == id }
//    }
//
//    // Получить количество объектов в списке
//    fun get_student_short_count(): Int = students.count()
}