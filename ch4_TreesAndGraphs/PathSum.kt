/*
Try:
kotlinc PathSum.kt -include-runtime -d pathsum.jar
java -jar pathsum.jar "1,-1,4,5,2,3,-1,-1,null,1,-2" 4
 */
fun main(args: Array<String>) {
    if(args.size != 2) throw IllegalArgumentException("Wrong number of arguments")
    val values = parseArray(args[0])
    val n = args[1].toInt()
    val tree = Tree()
    tree.insertLevelOrder(values, tree.root, 0)
    val paths = pathSums(tree.root, n)
    println("Number of paths that sum $n: $paths")
}

fun pathSums(n: TreeNode?, sum: Int): Int {
    n?:return 0
    // return getPathSums(n, sum, 0)
    return getPathSums_optimized(n, sum, 0, hashMapOf<Int, Int>())
}

fun getPathSums(n: TreeNode?, sum: Int, acc: Int): Int {
    n ?: return 0
    val newAcc = acc + n.d
    val isSum = if(newAcc == sum) 1 else 0
    val pathsLeft = getPathSums(n.left, sum, newAcc)
    val pathsRight = getPathSums(n.right, sum, newAcc)
    val pathsFromLeft = if(n.left?.counted == false) {
        n.left?.counted = true
        getPathSums(n.left, sum, 0)
    } else 0
    val pathsFromRight = if(n.right?.counted == false){
        n.right?.counted = true
        getPathSums(n.right, sum, 0)
    } else 0
    return isSum + pathsLeft + pathsRight + pathsFromLeft + pathsFromRight
}

fun getPathSums_optimized(n: TreeNode?, target: Int, acc: Int, map: HashMap<Int, Int>): Int{
    n ?: return 0
    val newAcc = acc + n.d
    val sum = newAcc - target
    var totalPaths = map[sum]?:0
    if(newAcc == target) totalPaths += 1
    incrementHashTable(map, newAcc, 1)
    totalPaths += getPathSums_optimized(n.left, target, newAcc, map)
    totalPaths += getPathSums_optimized(n.right, target, newAcc, map)
    incrementHashTable(map, newAcc, -1)
    return totalPaths
}

fun incrementHashTable(map: HashMap<Int, Int>, key: Int, delta: Int) {
    val newCount = map[key]?:0 + delta
    if(newCount == 0) map.remove(key)
    else map[key] = newCount
}

data class PathCount(var count: Int = 0)

class TreeNode(val d: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
    var counted = false
}

class Tree {
    var root: TreeNode? = null

    fun insertLevelOrder(arr: Array<Int?>, r: TreeNode?, i: Int): TreeNode? {
        var root = r
        if(i < arr.size) {
            val temp = if(arr[i] != null) TreeNode(arr[i]!!) else null
            if(this.root == null) this.root = temp
            root = temp
            root?.left = insertLevelOrder(arr, root?.left, 2*i + 1)
            root?.right = insertLevelOrder(arr, root?.right, 2*i + 2)
        }
        return root
    }
}

fun parseArray(s: String): Array<Int?> {
    val pattern = Regex("(-\\d+|\\d+|null)[,]*")
    val found = pattern.findAll(s)
    val values = found.map { 
        val value = it.groupValues[1]
        if(value == "null") null else value.toInt() 
    }.toMutableList()
    return Array(values.size) { values[it] }
}