package main.kotlin.strat.studentFileProc

import main.kotlin.strat.studentFileProc.*
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.DumperOptions
import main.kotlin.pattern.*
import main.kotlin.strat.Student_list
import java.io.File

class StudentYamlProc: StudentFileProc {
    override fun read_from_file(filePath: String): MutableList<Student> {
        val file = File(filePath)
        val students: MutableList<Student> = mutableListOf()
        if (!file.exists() || !file.isFile) {
            throw IllegalArgumentException("Некорректный адрес файла: $filePath")
        }

        try {
            val yaml = Yaml()
            val inputStream = file.inputStream()
            val studentsList: List<Student> = yaml.load(inputStream) // Загружаем данные из YAML файла

            students.clear()
            students.addAll(studentsList) // Добавляем студентов в коллекцию
            return students
        } catch (e: Exception) {
            throw IllegalArgumentException("Ошибка при чтении файла: ${e.message}")
        }
    }

    override fun write_to_file(students: MutableList<Student>, directory: String, fileName: String) {
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