import kotlin.math.abs

data class Tree(
    val height: Int,
    var visible: Boolean = false,
    var scenicScore: Int = 0
)

typealias Grid = List<List<Tree>>

fun main() {
    val input = readInput("Day08")
    val testInput = readInput("Day08_test")

    fun List<String>.toGrid() = this.map { row -> row.map { Tree(it.digitToInt()) } }

    fun Grid.isBorder(i: Int, j: Int) = (i == 0 || i == this.size - 1) || (j == 0 || j == this[i].size - 1)

    fun Grid.checkLeft(i: Int, j: Int) = (j - 1 downTo 0).map { k ->
        this[i][k].height < this[i][j].height
    }.all { it }

    fun Grid.scoreLeft(i: Int, j: Int): Int {
        var d = j
        for (k in j - 1 downTo 0) {
            if (this[i][k].height >= this[i][j].height) {
                d = j - k
                break
            }
        }
        return d
    }

    fun Grid.checkRight(i: Int, j: Int) = (j + 1 until this.size).map { k ->
        this[i][k].height < this[i][j].height
    }.all { it }

    fun Grid.scoreRight(i: Int, j: Int): Int {
        var d = this[i].size - j - 1
        for (k in j + 1 until this.size) {
            if (this[i][k].height >= this[i][j].height) {
                d = k - j
                break
            }
        }
        return d
    }

    fun Grid.checkUp(i: Int, j: Int) = (i - 1 downTo 0).map { k ->
        this[k][j].height < this[i][j].height
    }.all { it }

    fun Grid.scoreUp(i: Int, j: Int): Int {
        var d  = i
        for (k in i - 1 downTo 0) {
            if (this[k][j].height >= this[i][j].height) {
                d = i - k
                break
            }
        }
        return d
    }

    fun Grid.checkDown(i: Int, j: Int) = (i + 1 until this[i].size).map { k ->
        this[k][j].height < this[i][j].height
    }.all { it }

    fun Grid.scoreDown(i: Int, j: Int): Int {
        var d = this.size - i - 1
        for(k in i + 1 until this[i].size) {
            if(this[k][j].height >= this[i][j].height) {
                d = k - i
                break
            }
        }
        return d
    }

    fun Grid.expedition() {
        for (i in this.indices) {
            for (j in this[i].indices) {
                this[i][j].scenicScore = scoreLeft(i, j) * scoreRight(i, j) * scoreUp(i, j) * scoreDown(i, j)
                if (this.isBorder(i, j)) {
                    this[i][j].visible = true
                }
                if (this.checkLeft(i, j) || this.checkRight(i, j) || this.checkUp(i, j) || this.checkDown(i, j)) {
                    this[i][j].visible = true
                }
            }
        }
    }

    val grid = input.toGrid()
    val testGrid = testInput.toGrid()

    testGrid.expedition()
    grid.expedition()

    fun part1(test: Boolean = false): Int {
        return (if (test) testGrid else grid).flatten().count { it.visible }
    }

    fun part2(test: Boolean = false): Int {
        return (if (test) testGrid else grid).flatten().maxOf { it.scenicScore }
    }

    check(part1(true) == 21)
    println(part1())

    check(part2(true) == 8)
    println(part2())
}