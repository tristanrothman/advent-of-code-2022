fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf {
            val moveScore = (it.toCharArray().last() - 'X') + 1
            val roundScore = when(it) {
                "A X", "B Y", "C Z" -> 3
                "A Y", "B Z", "C X" -> 6
                "A Z", "B X", "C Y" -> 0
                else -> error("Input error")
            }
            moveScore + roundScore
        }
    }

    fun part2(input: List<String>): Int {
        return input.sumOf {
            val roundScore = (it.toCharArray().last() - 'X') * 3
            val moveScore = when(it) {
                "A Y", "B X", "C Z" -> 1
                "A Z", "B Y", "C X" -> 2
                "A X", "B Z", "C Y" -> 3
                else -> error("Input error")
            }
            moveScore + roundScore
        }
    }

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}