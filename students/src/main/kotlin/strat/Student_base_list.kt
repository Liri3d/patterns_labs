package main.kotlin.strat

import main.kotlin.pattern.Data_list
import main.kotlin.pattern.Data_list_student_short
import main.kotlin.pattern.Student
import main.kotlin.pattern.Student_short

abstract class Student_base_list(protected var students: MutableList<Student>) {
    constructor() : this(mutableListOf())

    constructor(filePath: String) : this(mutableListOf()) {
        read_from_file(filePath)
    }

    abstract fun read_from_file(filePath: String)

    abstract fun write_to_file(directory: String, fileName: String)

    fun findById(id: Int): Student {
        return students.first { it.id == id }
    }

    // Получить список k по счету n объектов класса Student_short
    fun get_k_n_student_short_list(n: Int, k: Int): Data_list<Student_short> {
        require(n >= 0) { "Индекс n должен быть больше или равен 0." }
        require(k > 0) { "Количество k должно быть больше 0." }

        return Data_list_student_short(students
            .drop(n)
            .take(k)
            .map { Student_short(it) }
        )
    }

    // Сортировка списка по фамилии и инициалам
    fun orderStudentsByLastNameInitials() {
        orderStudents(compareBy { it.getLastNameWithInitials() })
    }

    // Сортировка списка
    fun orderStudents(comparator: Comparator<Student>) {
        students.sortedWith(comparator)
    }

    // Добавление объекта
    fun add(student: Student) {
        val nextId = (students.maxByOrNull { it.id }?.id ?: 0) + 1
        student.id = nextId

        students.addLast(student)
    }

    // Заменить объект по ID
    fun replaceById(student: Student, id: Int) {
        student.id = id
        students.replaceAll { if (it.id == id) student else it }
    }

    // Удалить по ID
    fun removeById(id: Int) {
        students.removeIf { it.id == id }
    }

    // Получить количество объектов в списке
    fun get_student_short_count(): Int = students.count()
}
