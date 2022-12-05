import java.io.File

fun main() {

    fun solve(input: String, multipleCrates: Boolean): String {
        val inputs = input.split("\n\n")
        val size = inputs.component1().split("\n").last().trim().last().toString().toInt()
        val stacks = Array(size) {
            ArrayDeque<Char>()
        }

        inputs.component1().split("\n").dropLast(1).map { line ->
            line.chunked(4).map { it.trim() }.forEachIndexed { stack, crate ->
                if (crate.isNotEmpty()) {
                    stacks[stack].addFirst(crate[1])
                }
            }
        }

        inputs.component2().split("\n").map { line ->
            Regex("move (\\d+) from (\\d+) to (\\d+)").matchEntire(line)?.let { regex ->
                val numCrates = regex.groupValues[1].toInt()
                val from = regex.groupValues[2].toInt() - 1
                val to = regex.groupValues[3].toInt() - 1
                if (multipleCrates) {
                    stacks[to] += stacks[from].takeLast(numCrates)
                    repeat(numCrates) {
                        stacks[from].removeLast()
                    }
                } else {
                    repeat(regex.groupValues[1].toInt()) {
                        stacks[to].addLast(stacks[from].removeLast())
                    }
                }
            }
        }

        return stacks.joinToString("") { it.last().toString() }
    }

    val testInput = File("src", "Day05_test.txt").readText()
    check(solve(testInput, false) == "CMZ")
    check(solve(testInput, true) == "MCD")

    val input = File("src", "Day05.txt").readText()
    println(solve(input, false))
    println(solve(input, true))
}