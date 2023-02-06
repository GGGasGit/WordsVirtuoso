fun main() {
    val string = readLine()!!
    val n = readLine()!!.toInt()
    val regex = Regex("\\s+")
    val result = string.split(regex, n)
    result.forEach { println(it) }
}