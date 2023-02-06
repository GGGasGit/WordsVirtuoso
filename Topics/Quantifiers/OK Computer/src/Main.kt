fun main() {
    val text = readln()
    val regex = Regex(".*Computer.*")
    print(regex.matches(text))
}