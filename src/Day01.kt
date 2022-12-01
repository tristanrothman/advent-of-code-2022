

fun main() {
    fun part1(input: List<String>): Int {
        return input.process().max()
    }

    fun part2(input: List<String>): Int {
        return input.process().sortedDescending().take(3).sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}

fun List<String>.process() = fold(mutableListOf(0)) { acc, item ->
    if (item.isEmpty()) acc.add(0)
    else acc[acc.lastIndex] = acc.last().plus(item.toInt())
    acc
}
