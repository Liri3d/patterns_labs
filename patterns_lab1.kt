class Student(
    // Первичный конструктор
    id : Int,
    lastName: String,
    firstName : String,
    fatherName: String,
    phone: String = "",
    telegram: String = "",
    email: String = ""
) 
{
    // Вторичные конструкторы
    constructor(id: Int, lastName: String, firstName: String, fatherName: String) : this(id, lastName, firstName, fatherName, "", "", "") {
         println("Создан студент с id=$id, ФИО: $lastName $firstName $fatherName")
    }
    
    constructor(id: Int, lastName: String, firstName: String, fatherName: String, phone: String) : this(id, lastName, firstName, fatherName, phone, "", "") {
        println("Создан студент с id=$id, ФИО: $lastName $firstName $fatherName, телефон: $phone")
    }
    
    constructor(id: Int, lastName: String, firstName: String, fatherName: String, phone: String, telegram: String) : this(id, lastName, firstName, fatherName, phone, telegram, "") {
        println("Создан студент с id=$id, ФИО: $lastName $firstName $fatherName, телефон: $phone, телеграм: $telegram")
    }
    
    init {
        require(lastName.isNotBlank()) { "Фамилия не должна быть пустой" }
        require(firstName.isNotBlank()) { "Имя не должно быть пустым" }
        require(fatherName.isNotBlank()) { "Отчество не должно быть пустым" }
    }
    
    var id: Int = id
        set(value) { field = value }
        get() { return field}
    
    var lastName: String = lastName
    	set(value) { field = value   }    
        get() { return field }

    var firstName: String = firstName
        set(value) { field = value }
        get() { return field }
    
    var fatherName: String = fatherName
        set(value) { field = value }
        get() { return field }
        
    var phone: String = phone
        set(value) { field = value }
        get() { return field }

    var telegram: String = telegram
        set(value) { field = value }
        get() { return field }

    var email: String = email
        set(value) { field = value }
        get() { return field }

    fun printInfo() {
        println("Id: $id | " +
                "Фамилия: $firstName | " +
                "Имя: $lastName | " + 
                "Отчество: $fatherName | " +
                "Телефон: $phone | " + 
                "Телеграм: $telegram | " + 
                "Почта: $email")
    }
}

fun main() {
    val st1 = Student(1, "Петр", "Петров", "Петрович", "+79999999991", "@petr", "petr.gmail.com")
    val st2 = Student(2, "Семен", "Семенов", "Семенович", "+79999999992", "@semen")
    val st3 = Student(3, "Иван", "Иванов", "Иванович", "+79999999993")
    val st4 = Student(4, "Егор", "Егоров", "Егорович")

    st1.printInfo()
    st2.printInfo()
    st3.printInfo()
}
