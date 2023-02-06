fun getCamelStyleString(inputString: String): String {
    val listOfWords = inputString.split("_")
    return listOfWords.joinToString("") { it.replaceFirstChar(Char::uppercaseChar) }
}