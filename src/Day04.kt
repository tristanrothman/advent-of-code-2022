fun main() {
    fun String.toIntRange() = this.substringBefore("-").toInt()..this.substringAfter("-").toInt()

    fun String.toIntRanges() = this.substringBefore(",").toIntRange() to this.substringAfter(",").toIntRange()

    infix fun IntRange.contains(other: IntRange) = first <= other.first && last >= other.last

    infix fun IntRange.intersect(other: IntRange) = first <= other.last && other.first <= last

    fun part1(input: List<String>): Int {
        return input.map { it.toIntRanges() }.count {
            it.first contains it.second || it.second contains it.first
        }
    }

    fun part2(input: List<String>): Int {
        return input.map { it.toIntRanges() }.count {
            it.first intersect it.second
        }
    }

    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}