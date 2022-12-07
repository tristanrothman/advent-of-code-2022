data class Directory(
    val path: String,
) {
    fun back(): Directory {
        return Directory(path.substringBeforeLast('/'))
    }
}

data class FileSystem(
    var pwd: Directory,
    val sizes: MutableMap<Directory, Int> = mutableMapOf(pwd to 0)
) {
    fun cd(dir: String) {
        pwd = when (dir) {
            ".." -> pwd.back()
            else -> Directory("${pwd.path}/$dir")
        }
    }
}

fun main() {
    fun List<String>.isCd() = this.component2() == "cd"
    fun List<String>.hasSize() = this.component1().all { it.isDigit() }
    fun List<String>.getSize() = this.component1().toInt()

    val root = Directory("")

    val testInput = readInput("Day07_test")

    val input = readInput("Day07")

    fun List<String>.fileSystem() = this.drop(1).fold(FileSystem(root)) { fileSystem, item ->
        val command = item.split(' ')
        if (command.isCd()) {
            fileSystem.cd(command.component3())
        } else if (command.hasSize()) {
            fileSystem.pwd.path.split('/').fold(fileSystem.pwd) { dir, _ ->
                fileSystem.sizes[dir] = fileSystem.sizes.getOrElse(dir) { 0 } + command.getSize()
                dir.back()
            }
        }
        fileSystem
    }

    fun FileSystem.part1() = this.sizes.values.filter { it <= 100000 }.sum()

    fun FileSystem.part2() = this.sizes.values.filter {
        70000000 - (this.sizes.getValue(root) - it) >= 30000000
    }.min()


    check(testInput.fileSystem().part1() == 95437)
    check(testInput.fileSystem().part2() == 24933642)

    println(input.fileSystem().part1())
    println(input.fileSystem().part2())
}