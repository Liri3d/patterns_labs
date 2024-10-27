package main.kotlin.pattern

class Data_list_student_short(students: List<Student_short>) : Data_list<Student_short>(students) {
    override fun getEntityFields(): List<String> {
        return listOf("Номер", "Имя", "Гит", "Контакт")
    }

    override fun getDataRow(entity: Student_short): List<Any> {
        return listOf(entity.lastNameInitials, entity.git, entity.contact) as List<Any>
    }
}