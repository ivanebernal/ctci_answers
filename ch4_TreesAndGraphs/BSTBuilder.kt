fun main(args: Array<String>) {
    val sortedArr = intArrayOf(1, 2, 3 , 4, 5 , 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)
    val bst = buildBSTRecursive(sortedArr)
    printDFS(bst!!)
    printLevels(bst)
    
}

class Node<T>(var value: T) {
    var left: Node<T>? = null
    var right: Node<T>? = null
}

fun buildBSTRecursive(arr: IntArray): Node<Int>? {
    return buildBSTRecursive(arr, 0, arr.size - 1)
}

fun buildBSTRecursive(arr: IntArray, start: Int, end: Int): Node<Int>? {
    if(end < start){
        return null
    } 
    val mid = (start + end)/2
    val n = Node<Int>(arr[mid])
    n.left = buildBSTRecursive(arr, start, mid - 1)
    n.right = buildBSTRecursive(arr, mid + 1, end)
    return n
}

fun buildBST(nums: IntArray): Node<Int> {
    val root = Node(nums[nums.size - 1])
    println(root.value)
    for((i, num) in nums.withIndex()) {
        if(i == nums.size - 1) continue
        val n = Node(num)
        insertNodeToBST(root, n)
    }
    return root
}

//TODO: I'm not getting the real root node because of value swapping
fun insertNodeToBST(root: Node<Int>, n: Node<Int>) {
    if(root.left != null && root.right != null) {
        val nextRoot = if(n.value > root.value) root.right else root.left
        insertNodeToBST(nextRoot!!, n)
    } else {
        if(n.value > root.value) {
            if(root.right == null) root.right = n
            else {
                swapValues(root, root.right!!)
                root.left = root.right
                root.right = n
            }
        } else {
            if(root.left == null) root.left = n
            else {
                swapValues(root, n)
                root.right = n
            }
        }
    }
}

fun <T> swapValues(nodeA: Node<T>, nodeB: Node<T>) {
    val temp = nodeA.value
    nodeA.value = nodeB.value
    nodeB.value = temp
}

fun <T> printDFS(root: Node<T>) {
    dfs(root)
    println()
}

fun <T> dfs(root: Node<T>?) {
    if(root == null) return
    dfs(root.left)
    print("${root.value} ")
    dfs(root.right)
}

fun <T> printLevels(root: Node<T>) {
    var n = root
    var levels = 0
    while (n.left != null) {
        levels += 1
        println(n.value)
        n = n.left!!
    }
    println("Levels: $levels")
}