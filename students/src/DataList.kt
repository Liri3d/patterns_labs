class DataList<T : Comparable<T>>(private val items: List<T>) {
    // Приватное поле для хранения отсортированных элементов
    private val sortedItems: List<T> = items.sorted()

    // Метод для получения элемента по индексу
    operator fun get(index: Int): T {
        if (index < 0 || index >= sortedItems.size) {
            throw IndexOutOfBoundsException("Индекс выходит за пределы диапазона")
        }
        return sortedItems[index]
    }

    // Метод для получения размера списка
    fun size(): Int {
        return sortedItems.size
    }

    // Метод для отображения всех элементов
    fun display() {
        println(sortedItems.joinToString(", "))
    }
}

fun main() {
    val studentsArray = listOf(
        ("Иванов;Иван;Иванович;https://github.com/ivanov;Email;ivanov@example.com"),
        ("Сидоров;Сидор;Сидорович;https://github.com/sidorov;Телефон;1234567890"),
        ("Алексеева;Анна;Александровна;https://github.com/alekseeva;Email;anna@example.com"),
        ("Кузнецова;Мария;Сергеевна;https://github.com/kuznetsova;Телефон;0987654321")
    )

    // Создаем экземпляр DataList с массивом студентов
    val dataList = DataList(studentsArray)

    // Выводим упорядоченный список студентов
    println("Упорядоченный список студентов:")
    dataList.display()

    // Получаем элемент по индексу
    println("Элемент с индексом 0: ${dataList[0]}") // Ожидаем первый по алфавиту Student
    println("Размер списка: ${dataList.size()}") // Ожидаем 4
}