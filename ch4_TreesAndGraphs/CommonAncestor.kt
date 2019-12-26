fun main(args: Array<String>) {
    val n0 = TreeNode(0)
    val n1 = TreeNode(1)
    val n2 = TreeNode(2)
    val n3 = TreeNode(3)
    val n4 = TreeNode(4)
    val n5 = TreeNode(5)
    val n6 = TreeNode(6)

    n0.left = n1
    n0.right = n2
    n1.left = n3
    n1.right = n4
    n2.left = n5
    n2.right = n6

    val commonAncestor = findCommonAncestor(n6, n3, n0)
    println(commonAncestor?.value)
}

fun findCommonAncestor(a: TreeNode, b: TreeNode, root: TreeNode?): TreeNode? {
    if(root == null) return null
    if(root == a || root == b) return root
    val left = findCommonAncestor(a, b, root.left)
    val right = findCommonAncestor(a, b, root.right)
    if(left == null && right != null) return right
    if(right == null && left != null) return left
    if(left != null && right != null) return root
    return null
}

class TreeNode(val value: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}