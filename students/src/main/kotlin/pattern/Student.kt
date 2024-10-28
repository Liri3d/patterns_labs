package main.kotlin.pattern

import kotlinx.serialization.*

@Serializable
class Student(
    var id: Int = 0,
    var lastName: String,
    var firstName: String,
    var middleName: String,
    var telegram: String? = null,
    var git: String? = null
): StudentBase() {
    var phone: String? = null
        get() {
            return field
        }
        set(value) {
            validatePhone(value)
            field = value
        }

    var email: String? = null
        get() {
            return field
        }
        set(value) {
            validateEmail(value)
            field = value
        }

    companion object {
        private val PHONE_REGEX = Regex("(?:\\+|\\d)[\\d\\-\\(\\) ]{9,}\\d")
        private val EMAIL_REGEX = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\$")

        fun validatePhone(phoneNumber: String?) {
            if (!(phoneNumber == null || PHONE_REGEX.matches(phoneNumber))) {
                throw IllegalArgumentException("Неверный формат номера телефона: $phoneNumber")
            }
        }

        fun validateEmail(email: String?) {
            if (!(email == null || EMAIL_REGEX.matches(email))) {
                throw IllegalArgumentException("Неверный формат электронной почты: $email")
            }
        }
    }

    constructor(
        id: Int = 0,
        lastName: String,
        firstName: String,
        middleName: String,
        phone: String? = null,
        telegram: String? = null,
        email: String? = null,
        git: String? = null
    ) : this(id, lastName, firstName, middleName, telegram, git) {
        this.email = email
        this.phone = phone
    }
    constructor(
        info: Map<String, Any?>
    ) : this(
        info.getOrDefault("id", 0) as Int,
        info["lastName"].toString(),
        info["firstName"].toString(),
        info["middleName"].toString(),
        info.getOrDefault("phone", null) as String?,
        info.getOrDefault("telegram", null) as String?,
        info.getOrDefault("email", null) as String?,
        info.getOrDefault("git", null) as String?
    )

    constructor(
        serializedString: String
    ) : this(0, "", "", "") {
        val regex =
            Regex("Student\\(id=([^,]+), firstName=([^,]+), lastName=([^,]+), middleName=([^,]+), phone=([^,]+), telegram=([^,]+), email=([^,]+), git=([^)]*)\\)")
        val matchResult = regex.find(serializedString)

        if (matchResult != null) {
            this.id = matchResult.groups[1]?.value?.toInt() ?: 0
            this.firstName = matchResult.groups[2]?.value ?: ""
            this.lastName = matchResult.groups[3]?.value ?: ""
            this.middleName = matchResult.groups[4]?.value ?: ""

            this.phone = matchResult.groups[5]?.value.let { if (it == null || it == "null") null else it }
            this.telegram = matchResult.groups[6]?.value.let { if (it == null || it == "null") null else it }
            this.email = matchResult.groups[7]?.value.let { if (it == null || it == "null") null else it }
            this.git = matchResult.groups[8]?.value.let { if (it == null || it == "null") null else it }

            if (firstName.isEmpty()) {
                throw IllegalArgumentException("Неверные данные: firstName незаполненно!")
            }
            if (lastName.isEmpty()) {
                throw IllegalArgumentException("Неверные данные: lastName незаполненно!")
            }
            if (middleName.isEmpty()) {
                throw IllegalArgumentException("Неверные данные: middleName незаполненно!")
            }

            if (!validate()) {
                throw IllegalArgumentException("Неверные данные: гит или контакт пуст")
            }
        } else {
            throw IllegalArgumentException("Неверные данные: $serializedString")
        }
    }

    override fun toString(): String {
        return "Student(id=$id, firstName=$firstName, lastName=$lastName, middleName=$middleName, phone=$phone, telegram=$telegram, email=$email, git=$git)"
    }

    fun validate(): Boolean {
        return this.git?.isNotEmpty() ?: false &&
                (
                        this.email?.isNotEmpty() ?: false ||
                                this.telegram?.isNotEmpty() ?: false ||
                                this.phone?.isNotEmpty() ?: false
                        )
    }

    fun set_contacts(email: String?, telegram: String?, phone: String?) {

        if (email != null) {
            this.email = email;
        }

        if (telegram != null) {
            this.telegram = telegram;
        }

        if (phone != null) {
            this.phone = phone;
        }
    }

    // Получить информацию о контактах
    override fun getContactInfo(): String {
        val telegramContact = if (telegram != null) "Telegram: $telegram;" else ""
        val phoneContact = if (phone != null) "Phone: $phone;" else ""
        val emailContact = if (email != null) "Email: $email;" else ""

        return listOf(telegramContact, phoneContact, emailContact).first { it.isNotEmpty() }
    }

    override fun getLastNameWithInitials(): String = "$lastName ${firstName.first()}. ${middleName.first()}."

    override fun getGitInfo(): String? = git

}