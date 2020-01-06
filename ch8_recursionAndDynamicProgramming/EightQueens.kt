fun main(args: Array<String>) {
    val arrangements = getQueenPlaces(8)
    arrangements.forEach { arr ->
        arr.forEach { position ->
            print(position)
        }
        println()
    }
}

fun getQueenPlaces(n: Int): List<List<Pair<Int,Int>>> {
    val board = arrayListOf<List<Boolean>>()
    for(i in 0 until n) {
        val row = arrayListOf<Boolean>()
        for (j in 0 until n) {
            row.add(true)
        }
        board.add(row)
    }
    val result = arrayListOf<List<Pair<Int,Int>>>()
    getQueens(n, 0, board, arrayListOf<Pair<Int, Int>>(), result)
    return result
}

fun getQueens(n: Int, row: Int, board: List<List<Boolean>>, 
            prevQueens: List<Pair<Int, Int>>, result: ArrayList<List<Pair<Int, Int>>>) {
    if(row >= n) return
    for(i in 0 until n) {
        if(board[row][i]) {
            val pos = Pair(i, row)
            val queens = ArrayList(prevQueens)
            queens.add(pos)
            if(row == board.size - 1) {
                result.add(queens)
            } else {
                val newBoard = updateBoard(board, pos)
                getQueens(n, row + 1, newBoard, queens, result)
            }
        }
    }
}

fun updateBoard(board: List<List<Boolean>>, pos: Pair<Int, Int>): List<List<Boolean>> {
    val nb = mutableListOf<MutableList<Boolean>>()
    board.forEach{ nb.add(it.toMutableList()) }
    val n = nb.size
    val row = pos.second
    val col = pos.first
    for(i in 0 until n) {
        nb[row][i] = false
        nb[i][col] = false
        if(row + i < n && col + i < n) nb[row + i][col + i] = false //>>
        if(row - i >= 0 && col - i >= 0) nb[row - i][col - i] = false
        if(row + i < n && col - i >= 0) nb[row + i][col - i] = false
        if(col + i < n && row - i >= 0) nb[row - i][col + i] = false
    }
    return nb
}
