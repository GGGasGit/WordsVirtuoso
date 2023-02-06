fun main() {
    val text = readln()
    val regex = Regex("[aA]+")
    print(text.replace(regex, "a"))
}