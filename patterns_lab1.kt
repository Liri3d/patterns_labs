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
         // println("Создан студент с id=$id, ФИО: $lastName $firstName $fatherName")
    }
    
    constructor(id: Int, lastName: String, firstName: String, fatherName: String, phone: String) : this(id, lastName, firstName, fatherName, phone, "", "") {
        // println("Создан студент с id=$id, ФИО: $lastName $firstName $fatherName, телефон: $phone")
    }
    
    constructor(id: Int, lastName: String, firstName: String, fatherName: String, phone: String, telegram: String) : this(id, lastName, firstName, fatherName, phone, telegram, "") {
        // println("Создан студент с id=$id, ФИО: $lastName $firstName $fatherName, телефон: $phone, телеграм: $telegram")
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
        println("\nId: $id\n" +
                "Фамилия: $firstName\n" +
                "Имя: $lastName\n" + 
                "Отчество: $fatherName\n" +
                "Телефон: $phone\n" + 
                "Телеграм: $telegram\n" + 
                "Почта: $email")
    }
}

fun main() {
    val st1 = Student(
        id = 1, 
        firstName = "Петр",
        lastName = "Петров",
        fatherName = "Петрович",
        phone = "+7(999kр)999-9991",
        telegram = "@petr",
        email = "petr.gmail.com"
    )
    
    val st2 = Student(
        id = 2,
        firstName = "Семен", 
        lastName = "Семенов", 
        fatherName = "Семенович", 
        phone = "+7(999)999-9992", 
        telegram = "@semen")
    
    val st3 = Student(
        id = 3, 
        firstName = "Иван", 
        lastName = "Иванов", 
        fatherName = "Иванович", 
        phone = "+7(999)999-9993")
    
    val st4 = Student(
        id = 4, 
        firstName = "Егор", 
        lastName = "Егоров", 
        fatherName = "Егорович")

    st1.printInfo()
    st2.printInfo()
    st3.printInfo()
    st4.printInfo()
}
