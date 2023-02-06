fun main() {
    val input = readln()
    val regex = Regex("[A-Z][a-z]+( [A-Z][a-z]+)?")
    val names = regex.findAll(input)
    names.forEach { println(it.value) }
}