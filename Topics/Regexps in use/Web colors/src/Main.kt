fun main() {
    val text = readLine()!!
    val regexColors = "#[0-9a-fA-F]{6}\\b".toRegex()
    val colors = regexColors.findAll(text)
    colors.forEach { println(it.value) }
}