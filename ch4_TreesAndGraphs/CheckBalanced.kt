import kotlin.math.max
import kotlin.math.abs
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
    
    n0.left = n1
    n0.right = n2
    n1.left = n3
    n1.right = n4
    n2.left = n5
    n2.right = n6
    n3.left = n7
    n3.right = n8
    n4.left = n9
    n4.right = n10
    n5.left = n11
    n5.right = n12
    n6.left = n13
    n6.right = n14

    println(isBalanced(n0))
}

class TreeNode(val value: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

data class Balance(val height: Int, val isBalanced: Boolean)

fun checkBalanced(root: TreeNode?): Balance {
    root ?: return Balance(0, true)
    val lbalance = checkBalanced(root.left)
    val rbalance = checkBalanced(root.right)
    val isBalanced = rbalance.isBalanced && lbalance.isBalanced && abs(rbalance.height - lbalance.height) < 2
    val height = max(rbalance.height, lbalance.height) + 1
    return Balance(height, isBalanced)
}

fun isBalanced(root: TreeNode): Boolean {
    return checkBalanced(root).isBalanced
}