fun main() {
    val text = readln()
    val regex = Regex("(Am|A|Em|E|Dm|D|G|C) ")
    print(text.replace(regex, ""))
}