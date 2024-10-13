class DataTable(dataString: String) {

    // Приватное поле для хранения двумерного массива
    private val data: Array<Array<String>> = dataString
        .lines()
        .map { it.split(";").toTypedArray() }
        .toTypedArray()

    // Метод для получения элемента по индексам
    // Возвращает элемент по указанным индексам
    operator fun get(row: Int, col: Int): String {
        // Проверка на выход индексов за пределы массива
        if (row < 0 || row >= getRowCount() || col < 0 || col >= getColumnCount()) {
            throw IndexOutOfBoundsException("Индексы выходят за пределы массива")
        }
        return data[row][col]
    }

    // Метод для получения количества строк
    fun getRowCount(): Int {
        return data.size
    }

    // Метод для получения количества колонок
    fun getColumnCount(): Int {
        return if (data.isNotEmpty()) data[0].size else 0
    }

    // Метод для отображения содержимого таблицы
    fun printTable() {
        for (row in data) {
            println(row.joinToString(", "))
        }
    }
}

fun main() {
    // Пример входной строки
    val inputData = """
        Иванов;Иван;Иванович;https://github.com/ivanov;Email;ivanov@example.com
        Петров;Петр;Петрович;https://github.com/petrov;Email;petrov@example.com
        Сидоров;Сидор;Сидорович;https://github.com/sidorov;Email;sidorov@example.com
    """.trimIndent()

    // Создаем экземпляр DataTable с входной строкой
    val table = DataTable(inputData)

    // Печатаем таблицу
    println("Data Table:")
    table.printTable()

    // Пример получения элементов
    println("\nЭлемент [0,0]: ${table[0, 0]}") // Ожидаем "Иванов"
    println("Элемент [1,1]: ${table[1, 1]}") // Ожидаем "Петр"
    println("Кол-во строк: ${table.getRowCount()}") // Ожидаем 3
    println("Кол-во столбцов: ${table.getColumnCount()}") // Ожидаем 6
}