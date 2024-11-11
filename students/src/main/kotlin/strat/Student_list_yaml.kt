package main.kotlin.strat

import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.DumperOptions
import main.kotlin.pattern.*
import java.io.File

class Student_list_yaml(students: MutableList<Student> = mutableListOf()) : Student_base_list(students) {
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
            val yaml = Yaml()
            val inputStream = file.inputStream()
            val studentsList: List<Student> = yaml.load(inputStream) // Загружаем данные из YAML файла

            students.clear()
            students.addAll(studentsList) // Добавляем студентов в коллекцию
        } catch (e: Exception) {
            throw IllegalArgumentException("Ошибка при чтении файла: ${e.message}")
        }
    }


//
//    override fun read_from_file(filePath: String) {
//        val file = File(filePath)
//        if (!file.exists() || !file.isFile) {
//            throw IllegalArgumentException("Некорректный адрес файла: $filePath")
//        }
//
//        try {
//            val lines = file.readLines()
//            students.clear()
//            for (line in lines) {
//                val studentData = line.split(", ")
//                if (studentData.size >= 5) {  // Убедитесь, что есть хотя бы 5 элементов
//                    val id = studentData[0].trim().toInt()
//                    val lastName = studentData[1].trim()
//                    val firstName = studentData[2].trim()
//                    val middleName = studentData[3].trim()
//                    val telegram = if (studentData.size > 4) studentData[4].trim() else null
//                    val git = if (studentData.size > 5) studentData[5].trim() else null
//                    students.add(Student(id, lastName, firstName, middleName, telegram, git))
//
//                }
//            }
//        } catch (e: Exception) {
//            throw IllegalArgumentException("Ошибка при чтении файла: ${e.message}")
//        }
//    }


//
//    override fun write_to_file(directory: String, fileName: String) {
//        val file = File(directory, fileName)
//
//        try {
//
//            // Убедимся, что папка создается
//            if (file.parentFile?.mkdirs() == true) {
//                println("Создана директория: ${file.parentFile.absolutePath}")
//            }
//
//            // Генерация строки в формате YAML
//            val yamlString = buildString {
//                for (student in students) {
//                    append("- id: ${student.id}\n")
//                    append("  lastName: ${student.lastName}\n")
//                    append("  firstName: ${student.firstName}\n")
//                    append("  middleName: ${student.middleName}\n")
//                    student.telegram?.let { append("  telegram: $it\n") }
//                    student.git?.let { append("  git: $it\n") }
//                    student.phone?.let { append("  phone: $it\n") }
//                    student.email?.let { append(" email: $it\n") }
//                }
//            }
//
//
//            // Запись в файл
//            file.writeText(yamlString.trim())
//            println("Данные успешно записаны в файл: ${file.absolutePath}")
//        } catch (e: Exception) {
//            throw IllegalArgumentException("Ошибка при записи в файл: ${e.message}")
//
//
//        }
//    }




    override fun write_to_file(directory: String, fileName: String) {
        val file = File(directory, fileName)

        try {
            // Убедимся, что папка создается
            if (file.parentFile?.mkdirs() == true) {
                println("Создана директория: ${file.parentFile.absolutePath}")
            }

            // Настройки для читаемого YAML
            val options = DumperOptions().apply {
                // Установите желаемые параметры форматирования
                defaultFlowStyle = DumperOptions.FlowStyle.BLOCK
            }

            val yaml = Yaml(options)

            // Запись в файл в формате YAML
            file.writeText(yaml.dump(students))
        } catch (e: Exception) {
            throw IllegalArgumentException("Ошибка при записи в файл: ${e.message}")
        }
    }
}