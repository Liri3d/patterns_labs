
class Student_short(
    val id: Int,
    lastName: String,
    firstName: String,
    middleName: String,
    gitLink: String,
    contactMethod: String,
    contactValue: String
) : BaseStudent(lastName, firstName, middleName, gitLink, contactMethod, contactValue) {

    // Конструктор, принимающий объект Student
    constructor(student: Student) : this(
        id = 0, // Задаём ID по умолчанию
        lastName = student.lastName,
        firstName = student.firstName,
        middleName = student.middleName,
        gitLink = student.gitLink,
        contactMethod = student.contactMethod,
        contactValue = student.contactValue
    )

    // Конструктор, принимающий ID и строку
    constructor(id: Int, info: String) : this(
        id = id,
        lastName = info.substringBefore(";"),
        firstName = info.substringAfter(";").substringBefore(";"),
        middleName = info.substringAfter(";").substringAfter(";").substringBefore(";"),
        gitLink = info.substringAfter(";").substringAfter(";").substringAfter(";").substringBefore(";"),
        contactMethod = "",
        contactValue = info.substringAfter(";").substringAfter(";").substringAfter(";").substringAfter(";")
    )

    override fun getInfo(): String {
        return (//"ID: $id\n" +
                "ФИО: ${getFullName()}\n" +
                        "Гит: ${getGitInfo()}\n" +
                        "${getContactInfo()}\n")
    }
}