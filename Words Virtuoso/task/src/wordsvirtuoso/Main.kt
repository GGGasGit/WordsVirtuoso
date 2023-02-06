package wordsvirtuoso

import java.io.File
import kotlin.system.exitProcess
import kotlin.random.Random

fun main(args: Array<String>) {
    if (args.size != 2) {
        println("Error: Wrong number of arguments.")
    } else {
        val separator = File.separator
        val workingDirectory = System.getProperty ("user.dir")
        val fileCompleteName = args[0]
        val fileCandidatesName = args[1]
        val fileComplete = File("${workingDirectory}${separator}$fileCompleteName" )
        val fileCandidates = File("${workingDirectory}${separator}$fileCandidatesName" )

        if (!fileComplete.exists()) {
            println("Error: The words file ${args[0]} doesn't exist.")
        } else if (!fileCandidates.exists()) {
            println("Error: The candidate words file ${args[1]} doesn't exist.")
        } else {
            val validCharacters = Regex("[a-zA-Z]{5}")

            var invalidWordsCount = 0
            fileComplete.forEachLine {
                if (!validCharacters.matches(it) || it.toSet().size != 5) invalidWordsCount++
            }
            if (invalidWordsCount != 0) {
                println("Error: $invalidWordsCount invalid words were found in the $fileCompleteName file.")
                exitProcess(0)
            }

            invalidWordsCount = 0
            fileCandidates.forEachLine {
                if (!validCharacters.matches(it) || it.toSet().size != 5) invalidWordsCount++
            }
            if (invalidWordsCount != 0) {
                println("Error: $invalidWordsCount invalid words were found in the $fileCandidatesName file.")
                exitProcess(0)
            }

            var missingWordsCount = 0
            val completeDictionary = fileComplete.readLines().map { it.lowercase() }
            fileCandidates.forEachLine {
                if (it.lowercase() !in completeDictionary) missingWordsCount++
            }
            if (missingWordsCount != 0) {
                println("Error: $missingWordsCount candidate words are not included in the $fileCompleteName file.")
                exitProcess(0)
            }

            println("Words Virtuoso")

            val candidateDictionary = fileCandidates.readLines().map { it.lowercase() }
            val randGen = Random.Default
            val secretWord = candidateDictionary[randGen.nextInt(candidateDictionary.size)]

            val wrongCharacters = mutableSetOf<String>()

            fun clueString(guess: String): String {
                var clueString = ""
                for (i in 0..guess.lastIndex) {
                    clueString += if (guess[i] == secretWord[i]) {
                        "\u001B[48:5:10m${guess[i].uppercase()}\u001B[0m"
                    } else if (guess[i] in secretWord) {
                        "\u001B[48:5:11m${guess[i].uppercase()}\u001B[0m"
                    } else {
                        wrongCharacters.add(guess[i].uppercase())
                        "\u001B[48:5:7m${guess[i].uppercase()}\u001B[0m"
                    }
                }

                return clueString
            }

            var clueList = emptyArray<String>()

            val start = System.currentTimeMillis()
            var numberOfGuesses = 0
            while (true) {
                println("\nInput a 5-letter word:")
                val guess = readln().lowercase()
                numberOfGuesses++

                if (guess == "exit") {
                    println("\nThe game is over.")
                    break
                } else if (guess == secretWord) {
                    val end = System.currentTimeMillis()

                    clueList += clueString(guess)
                    println("")
                    clueList.forEach { println(it) }

                    println("\nCorrect!")
                    println(
                        if (numberOfGuesses == 1) {
                            "Amazing luck! The solution was found at once."
                        } else {
                            "The solution was found after $numberOfGuesses tries in ${(end - start) / 1000} seconds."
                        }
                    )
                    break
                } else if (guess.length != 5) {
                    println("The input isn't a 5-letter word.")
                } else if (!validCharacters.matches(guess)) {
                    println("One or more letters of the input aren't valid.")
                } else if (guess.toSet().size != 5) {
                    println("The input has duplicate letters.")
                } else if (guess !in completeDictionary) {
                    println("The input word isn't included in my words list.")
                } else {
                    clueList += clueString(guess)
                    println("")
                    clueList.forEach { println(it) }
                    println("")
                    println("\u001B[48:5:14m${wrongCharacters.sorted().joinToString("")}\u001B[0m")
                }
            }

        }
    }
}