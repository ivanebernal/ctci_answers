class TreeNode(val value: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
    var parent: TreeNode? = null
}

fun main(args: Array<String>) {
    val n0 = TreeNode(1)
    val n1 = TreeNode(2)
    val n2 = TreeNode(3)
    val n3 = TreeNode(4)
    val n4 = TreeNode(5)
    val n5 = TreeNode(6)
    val n6 = TreeNode(7)
    val n7 = TreeNode(8)
    val n8 = TreeNode(9)
    val n9 = TreeNode(10)
    val n10 = TreeNode(11)
    val n11 = TreeNode(12)
    val n12 = TreeNode(13)
    val n13 = TreeNode(14)
    val n14 = TreeNode(15)
    
    n2.left = n1
    n1.parent = n2
    n2.right = n3
    n3.parent = n2
    n4.left = n2
    n2.parent = n4
    n4.right = n5
    n5.parent = n4
    n6.left = n4
    n4.parent = n6
    n6.right = n8
    n8.parent = n6
    n8.left = n7
    n7.parent = n8
    n8.right = n9
    n9.parent = n8

    println(findSuccessor(n3))
}

fun findSuccessor(n: TreeNode): Int? {
    if(n.parent == null) return getRightSuccessor(n.right)
    if(n.parent!!.value >= n.value) return n.parent!!.value
    else return getLeftSuccessor(n.parent!!)
}

fun getLeftSuccessor(n: TreeNode): Int? {
    if(n.parent == null) return null
    if(n.parent!!.value >= n.value) return n.parent!!.value
    return getLeftSuccessor(n.parent!!)
}

fun getRightSuccessor(n: TreeNode?): Int? {
    if(n == null) return null
    if((n.left == null && n.right == null) || n.left == null) return n.value
    else return getRightSuccessor(n.left)
}