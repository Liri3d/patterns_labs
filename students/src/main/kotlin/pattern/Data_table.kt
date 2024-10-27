package main.kotlin.pattern

class Data_table<T> private constructor(private val data: List<List<T>>) {
    companion object {
        fun <T> create(data: List<List<T>>): Data_table<T> {
            return Data_table(data)
        }
    }

    fun getColumnCount(): Int = if (data.isNotEmpty()) data[0].size else 0
    fun getRowCount(): Int = data.size

    fun getElement(row: Int, column: Int): T {
        require(row in data.indices && column in data[0].indices) {
            "Индексы выходят за границы таблицы"
        }
        return data[row][column]
    }


}