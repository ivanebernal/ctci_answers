fun main(args: Array<String>) {
    val grid = arrayOf(
        arrayOf(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true),
        arrayOf(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true),
        arrayOf(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true),
        arrayOf(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true),
        arrayOf(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true),
        arrayOf(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true),
        arrayOf(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true),
        arrayOf(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false),
        arrayOf(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true),
        arrayOf(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true),
        arrayOf(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true),
        arrayOf(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true),
        arrayOf(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true),
        arrayOf(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true),
        arrayOf(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true),
        arrayOf(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true)
    )
    val graphic = grid.map { it.map{ if(it) "O" else "X" }.toMutableList() }.toMutableList()
    val path = findPath(grid)
    if(path.isEmpty()) println("No path to the end of the grid")
    else printPath(path)
    for(point in path) {
        graphic[point.first][point.second] = "-"
    }
    printGraphic(graphic)
}

fun findPath(grid: Array<Array<Boolean>>): ArrayList<Pair<Int, Int>> {
    val paths = HashMap<Pair<Int, Int>, List<Pair<Int, Int>>>()
    val r = grid.size
    val c = grid[0].size
    findPath(grid, paths, r - 1, c - 1)
    return buildPath(paths, r, c)
}

fun findPath(grid: Array<Array<Boolean>>, memo: HashMap<Pair<Int, Int>, List<Pair<Int, Int>>>, row: Int, col: Int) {
    if(memo[Pair(row, col)] == null) {
        val paths = arrayListOf<Pair<Int, Int>>()
        memo[Pair(row, col)] = paths
        if(row - 1 >= 0 && grid[row - 1][col]) { //>
            paths.add(Pair(row - 1, col))
            findPath(grid, memo, row - 1, col)
        }
        if(col - 1 >= 0 && grid[row][col - 1]) { //>
            paths.add(Pair(row, col - 1))
            findPath(grid, memo, row, col - 1)
        }
    }
}

fun buildPath(memo: HashMap<Pair<Int, Int>, List<Pair<Int, Int>>>, r: Int, c: Int): ArrayList<Pair<Int, Int>> {
    var current: Pair<Int, Int>? = Pair(r - 1, c - 1)
    val result = arrayListOf<Pair<Int, Int>>().apply{ add(current!!) }
    while(current != null) {
        val next = memo[current]?.firstOrNull()
        next?.let { result.add(0, it) }
        current = next
    }
    return if(result.size < r + c - 1) arrayListOf() else result //>
}

fun printPath(path: ArrayList<Pair<Int, Int>>) {
    print("One path to the end of the grid is: ")
    for((i, point) in path.withIndex()) {
        if(i == path.size - 1) print("(${point.first}, ${point.second})")
        else print("(${point.first}, ${point.second}), ")
    }
    println()
}

fun printGraphic(graphic: List<List<String>>) {
    for(row in graphic) {
        for (col in row) {
            print(col)
        }
        println()
    }
}