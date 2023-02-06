fun main() {
    val number = readln()
    val regex = Regex("[a-zA-Z]")
    print(number.replace(regex, ""))
}