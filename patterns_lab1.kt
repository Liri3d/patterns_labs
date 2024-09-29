class Student(
    id : Int,
    lastName: String,
    firstName : String,
    fatherName: String,
    phone: String = "",
    telegram: String = "",
    email: String = ""
) 
{
    init {
        require(lastName.isNotBlank()) { "Фамилия не должна быть пустой" }
        require(firstName.isNotBlank()) { "Имя не должно быть пустым" }
        require(fatherName.isNotBlank()) { "Отчество не должно быть пустым" }
    }
    
    var id: Int = id
        set(value) {
            field = value
        }
        get() {
            return field
        }

    
    
    var lastName: String = lastName
    	set(value) {
        	field = value  
        }    
        get() {       
             return field
        }

    var firstName: String = firstName
        set(value) {
            field = value
        }
        get() {       
             return field
        }
    
    var fatherName: String = fatherName
        set(value) {
            field = value
        }
        get() {       
             return field
        }
        
    var phone: String = phone
        set(value) {
            field = value
        }
        get() {
            return field
        }

    var telegram: String = telegram
        set(value) {
            field = value
        }
        get() {
            return field
        }

    var email: String = email
        set(value) {
            field = value
        }
        get() {
            return field
        }

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
    val st2 = Student(2, "Иван", "Иванов", "Иванович", "+79999999992", "@ivan", "ivan.gmail.com")
    val st3 = Student(2, "Егор", "Егоров", "Егорович", "+79999999993", "@egor", "egor.gmail.com")

    st1.printInfo()
    st2.printInfo()
    st3.printInfo()
}
