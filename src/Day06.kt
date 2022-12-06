import java.io.File


fun main() {
    fun String.isUnique() = toSet().size == length

    fun String.findMarker(size: Int) = windowed(size).indexOfFirst { it.isUnique() } + size

    fun part1(input: String): Int {
        return input.findMarker(4)
    }

    fun part2(input: String): Int {
        return input.findMarker(14)
    }

    val testInput = File("src", "Day06_test.txt").readText()
    check(part1(testInput) == 11)
    check(part2(testInput) == 26)

    val input = File("src", "Day06.txt").readText()
    println(part1(input))
    println(part2(input))
}