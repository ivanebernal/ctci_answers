import kotlin.math.max

fun main(args: Array<String>) {
    val boxes = listOf(
        Box(1, 1, 1),
        Box(8, 5, 7),
        Box(7, 7, 7),
        Box(4, 3, 4),
        Box(2, 2, 2),
        Box(2, 1, 2),
        Box(7, 6, 6)
    )
    val highest = getHighestStack(boxes)
    println("The highest stack height is $highest")
}

fun getHighestStack(boxes: List<Box>): Int {
    val sorted = boxes.sortedByDescending { it.height }
    println("Boxes: ${sorted.toString()}")
    return getHighestStack(sorted, null)
}

fun getHighestStack(boxes: List<Box>, top: Box?, index: Int = 0, acc: Int = 0, memo: HashMap<Box, Int> = hashMapOf()): Int {
    if(index >= boxes.size) {
        return acc
    }
    if(top != null && memo[top] != null) return memo[top]!!
    var highest = 0
    for(i in index until boxes.size) {
        val box = boxes[i]
        if(box.isSmallerThan(top)) {
            val height = getHighestStack(boxes, box, i + 1, acc + box.height)
            top?.let{ memo[top] = height }
            highest = max(highest, height)
        }
    }
    highest = max(highest, acc)
    return highest
}

data class Box(val width: Int, val height: Int, val depth: Int) {
    fun isSmallerThan(box: Box?): Boolean {
        box ?: return true
        return box.height > this.height 
            && box.width > this.width 
            && box.depth > this.depth
    }
}