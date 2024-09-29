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