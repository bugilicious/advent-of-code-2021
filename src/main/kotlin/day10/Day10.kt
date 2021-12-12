package day10

import PuzzleSolver
import readInput
import java.util.Stack

fun main() {
    with(Day10) {
        println(solvePart1()) // 415953
        // println(solvePart2())
    }
}

object Day10 : PuzzleSolver {

    private val lines = readInput("day10/Day10")

    override fun solvePart1() = lines
        .mapNotNull { it.getFirstIncorrectClosingCharacter() }
        .sumOf { it.points }

    override fun solvePart2(): Number {
        TODO("Not yet implemented")
    }

    private fun String.getFirstIncorrectClosingCharacter(): Char? {
        val stack = Stack<Char>()

        tailrec fun String.getRecursively(): Char? {
            val openingCharacters = takeWhile { it.isOpeningCharacter }.onEach { stack.push(it) }
            val closingCharacters = drop(openingCharacters.length)
                .takeWhile {
                    with(it == stack.peek().closingCharacter) {
                        if (this) stack.pop()
                        this
                    }
                }
            val next = drop(openingCharacters.length).drop(closingCharacters.length)
            return when {
                stack.isEmpty() and next.isEmpty() -> null
                next[0].isClosingCharacter and stack.isEmpty() -> next[0]
                next[0].isClosingCharacter and (next[0] != stack.peek().closingCharacter) -> next[0]
                else -> next.getRecursively()
            }
        }

        return runCatching { getRecursively() }.getOrNull()
    }

    private val Char.isOpeningCharacter
        get() =
            when (this) {
                '(', '[', '{', '<' -> true
                else -> false
            }
    private val Char.isClosingCharacter
        get() =
            when (this) {
                ')', ']', '}', '>' -> true
                else -> false
            }
    private val Char.closingCharacter
        get() =
            when (this) {
                '(' -> ')'
                '[' -> ']'
                '{' -> '}'
                '<' -> '>'
                else -> error("$this is not supported.")
            }
    private val Char.points
        get() =
            when (this) {
                ')' -> 3
                ']' -> 57
                '}' -> 1197
                '>' -> 25137
                else -> error("$this is not supported.")
            }
}
