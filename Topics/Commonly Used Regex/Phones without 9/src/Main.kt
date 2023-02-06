fun main() {
    val input = readln()
    val regex = Regex("\\(?[0-8]{3}\\)?-?[0-8]{3}-?[0-8]{4}")
    val result = regex.findAll(input)
    result.forEach { println(it.value) }
}