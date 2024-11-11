package main.kotlin.strat

import main.kotlin.pattern.Data_list
import main.kotlin.pattern.Data_list_student_short
import main.kotlin.pattern.Student
import main.kotlin.pattern.Student_short
import java.io.File
import java.io.FileNotFoundException

// Работа с коллекцией об-ов Student и получ их из файла
class Student_list_txt(students: MutableList<Student>) :
 Student_base_list(students) {
    constructor() : this(mutableListOf())

    constructor(filePath: String) : this(mutableListOf()) {
        read_from_file(filePath)
    }

    // Получить список студентов из файла
    override fun read_from_file(filePath: String) {
        val file = File(filePath)
        if (!file.exists() || !file.isFile) {
            throw IllegalArgumentException("Некорректный адрес файла: $filePath")
        }

        try {
            students = file.readLines().mapNotNull { line ->
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
    override fun write_to_file(directory: String, fileName: String) {
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

//    override  fun findById(id: Int): Student {
//        return students.first { it.id == id }
//    }

//
//
//    // k-ый список из n элементов
//    fun get_k_n_student_short_list(n: Int, k: Int): Data_list<Student_short> {
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
//    // Сортировка списка по фамилии
//    fun orderStudentsByLastNameInitials() {
//        orderStudents(compareBy { it.getLastNameWithInitials() }) // Вызов orderStudents для сортировки
//    }
//
//    // Сортировка
//    fun orderStudents(comparator: Comparator<Student>) {
//        students.sortWith(comparator) // Сортировка на месте
//    }
//
//    // Добавить объект
//    fun add(student: Student) {
//        val nextId = (students.maxByOrNull { it.id }?.id ?: 0) + 1
//        student.id = nextId
//
//        students.addLast(student)
//    }
//
//    // Замена объекта по id
//    fun replaceById(student: Student, id: Int) {
//        student.id = id
//        students.replaceAll { if (it.id == id) student else it }
//    }
//
//    // Удаление по id
//    fun removeById(id: Int) {
//        students.removeIf { it.id == id }
//    }
//
//    fun getStudents(): List<Student> {
//        return students.toList() // Возвращаем копию списка студентов
//    }
//
//    // Получение количества объектов в списке
//    fun get_student_short_count(): Int = students.count()
}