fun main() {
    val upperCase = Regex("[A-Z]+")
    val lowerCase = Regex("[a-z]+")
    val number = Regex("[0-9]+")

    val password = readln()

    val conditionsMet = upperCase.find(password) != null &&
        lowerCase.find(password) != null &&
        number.find(password) != null

    print(if (conditionsMet) "Password saved" else "Password is too simple")
}