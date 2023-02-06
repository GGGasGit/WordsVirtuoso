fun parsingNickname(emailString: String): String {
    val symbolsForNickname = Regex("[._]") // fix this condition
    val nicknameString = emailString.split("@").first() // fix this condition
    val (firstName, lastName) = nicknameString.split(symbolsForNickname)
    return "${firstName.replaceFirstChar(Char::uppercase)} ${lastName.replaceFirstChar(Char::uppercase)}"
}