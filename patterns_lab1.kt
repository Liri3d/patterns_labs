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
        
    private var _phoneNumber: String = phone   
    
    var phone: String
    	get() = _phoneNumber
    	set(value) {  
            if (PhoneNumberValidator.isPhoneNumber(value)) {
                _phoneNumber = value 
                //println("Корректный номер студента $firstName: $_phoneNumber")
            }
            else {
                _phoneNumber = ""
                
                //println("Некорректный номер студента $firstName: $value")
            }
        }
        
    var telegram: String = telegram
        set(value) { field = value }
        get() { return field }

    var email: String = email
        set(value) { field = value }
        get() { return field }

    fun printInfo() {
        println("\nId: $id\n" +
                "Фамилия: $lastName\n" +
                "Имя: $firstName\n" + 
                "Отчество: $fatherName\n" +
                "Телефон: $phone\n" + 
                "Телеграм: $telegram\n" + 
                "Почта: $email")
    }
}

object PhoneNumberValidator {
        // Регулярное выражение для проверки телефонного номера
        private var VALID_PHONE_NUMBER_REGEX = Regex("^\\+?\\d{1,3}[- ]?\\(?\\d{1,3}\\)?[- ]?\\d{3,4}[- ]?\\d{4}\$")
        fun isPhoneNumber(phoneNumber: String): Boolean {
            return VALID_PHONE_NUMBER_REGEX.matches(phoneNumber)
        }    
    }

fun main() {

    var st1: Student = Student(
        id = 1, 
        lastName = "Петров",
        firstName = "Петр",
        fatherName = "Петрович"
    )
    st1.phone = "+7(99р9)999-9991"
    st1.telegram = "@petr"
    st1.email = "petr.gmail.com"      
    
    val st2 = Student(
        id = 2,
        lastName = "Семенов", 
        firstName = "Семен", 
        fatherName = "Семенович"
    )
    st2.phone = "+7(999)999-9992"
    st2.telegram = "@semen"
    
    val st3 = Student(
        id = 3, 
        lastName = "Иванов", 
        firstName = "Иван", 
        fatherName = "Иванович"
    )
    st3.phone = "+7(999)999-9993"
    
    val st4 = Student(
        id = 4, 
        lastName = "Егоров", 
        firstName = "Егор", 
        fatherName = "Егорович")

    st1.printInfo()
    st2.printInfo()
    st3.printInfo()
    st4.printInfo()
    
   
}


