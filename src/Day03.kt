fun main() {
    fun Char.priority(): Int {
        return if (this.isLowerCase()) {
            this - 'a' + 1
        } else {
            this - 'A' + 27
        }
    }

    fun part1(input: List<String>): Int {
        return input.map { rucksack ->
            val compartmentSize = rucksack.length / 2
            rucksack.substring(0, compartmentSize).toCharArray() to rucksack.substring(compartmentSize).toCharArray()
        }.flatMap {
            it.first intersect it.second.toSet()
        }.sumOf { it.priority() }
    }


    fun part2(input: List<String>): Int {
        return input.map { it.toCharArray() }
            .chunked(3)
            .flatMap {
                it.component1() intersect it.component2().toSet() intersect it.component3().toSet()
            }.sumOf { it.priority() }
    }

    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}