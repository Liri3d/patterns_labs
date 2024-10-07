data class MyClass(
    val field1: Int,
    val field2: String,
    val field3: Double
) {
    companion object {
        private const val DELIMITER = ","

        // Функция создания объекта MyClass из строки
        fun fromString(str: String): MyClass {
            val parts = str.split(DELIMITER)
            if (parts.size != 3) {
                throw IllegalArgumentException("Неверная входная строка: $str")
            }

            val field1 = parts[0].toIntOrNull() ?: throw IllegalArgumentException("Неверное значение field1: ${parts[0]}")
            val field2 = parts[1]
            val field3 = parts[2].toDoubleOrNull() ?: throw IllegalArgumentException("Неверное значение field3: ${parts[2]}")
            
            return MyClass(field1, field2, field3)
        }
    }
    
    // Новый конструктор, принимающий строку
    constructor(str: String) : this(fromString(str).field1, fromString(str).field2, fromString(str).field3)

    override fun toString(): String {
        return "$field1,$field2,$field3"
    }
}

fun main() {
    // Пример с валидной строкой с использованием нового конструктора
    val validString = MyClass("42,hello,3.14")
    println(validString) // Вывод: MyClass(field1=42, field2=hello, field3=3.14)

    // Пример с невалидной строкой 1
    try {
        val invalidString1 = MyClass("42,hello")
    } catch (e: IllegalArgumentException) {
        println(e.message) // Вывод ошибки
    }

    // Пример с невалидной строкой 2
    try {
        val invalidString2 = MyClass("42,hello,abc")
    } catch (e: IllegalArgumentException) {
        println(e.message) // Вывод ошибки
    }

    // Пример с валидной строкой 2
    val validString2 = MyClass("123,string,45.05")
    println(validString2) // Вывод: MyClass(field1=123, field2=string, field3=45.05)
}