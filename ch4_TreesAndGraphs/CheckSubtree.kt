fun main(args: Array<String>) {
    val n1 = TreeNode(1)
    val n2 = TreeNode(2)
    val n3 = TreeNode(3)
    val n4 = TreeNode(4)
    val n5 = TreeNode(5)
    val n6 = TreeNode(6)
    val n7 = TreeNode(7)
    val n8 = TreeNode(8)
    val n9 = TreeNode(9)
    val n10 = TreeNode(10)
    val n11 = TreeNode(11)
    val n12 = TreeNode(12)
    val n13 = TreeNode(13)
    val n14 = TreeNode(14)
    val n15 = TreeNode(15)
    val n16 = TreeNode(16)
    val n17 = TreeNode(17)
    val n18 = TreeNode(18)
    val n19 = TreeNode(19)
    val n20 = TreeNode(20)
    val n21 = TreeNode(21)
    val n22 = TreeNode(22)
    val n23 = TreeNode(23)
    val n24 = TreeNode(24)
    val n25 = TreeNode(25)
    val n26 = TreeNode(26)
    val n27 = TreeNode(27)
    val n28 = TreeNode(28)
    val n29 = TreeNode(29)
    val n30 = TreeNode(30)
    val n31 = TreeNode(31)

    n15.left = n30
    n15.right = n31
    n14.left = n28
    n14.right = n29
    n13.left = n26
    n13.right = n27
    n12.left = n24
    n12.right = n25
    n11.left = n22
    n11.right = n23
    n10.left = n20
    n10.right = n21
    n9.left = n18
    n9.right = n19
    n8.left = n16
    n8.right = n17
    n4.left = n8
    n4.right = n9
    n5.left = n10
    n5.right = n11
    n6.left = n12
    n6.right = n13
    n7.left = n14
    n7.right = n15
    n2.left = n4
    n2.right = n5
    n3.left = n6
    n3.right = n7
    n1.left = n2
    n1.right = n3

    val m1 = TreeNode(9)
    val m2 = TreeNode(18)
    val m3 = TreeNode(19)
    m1.left = m2
    m1.right = m3

    println(isSubTree_strings(n1, m1))
}

fun isSubTree(t1: TreeNode, t2: TreeNode): Boolean {
    if(t1.getSize() <= t2.getSize()) {
        throw IllegalArgumentException("T2 must be smaller than T1")
    }
    return isSubtreeHelper(t1, t2)
}

fun isSubtreeHelper(t1: TreeNode?, t2: TreeNode): Boolean {
    t1 ?: return false
    if(t1.getSize() < t2.getSize()) return false
    if(t2.data == t1.data && checkTreeEqual(t1, t2)) return true
    return isSubtreeHelper(t1.left, t2) || isSubtreeHelper(t1.right, t2)
}

fun checkTreeEqual(t1: TreeNode?, t2: TreeNode?): Boolean {
    if(!((t1 == null) xor (t2 == null))) {
        if(t1 == null || t2 == null) return true
        if(t1.getSize() != t2.getSize()) return false
        return t1.data == t2.data && checkTreeEqual(t1.left, t2.left) && checkTreeEqual(t1.right, t2.right)
    }
    return false
}

fun isSubTree_strings(t1: TreeNode, t2: TreeNode): Boolean {
    val sb1 = StringBuilder()
    val sb2 = StringBuilder()
    getPreOrderString(t1, sb1)
    getPreOrderString(t2, sb2)
    val preOrder1 = sb1.toString()
    val preOrder2 = sb2.toString()
    return preOrder1.indexOf(preOrder2) != -1
}

fun getPreOrderString(n: TreeNode?, sb: StringBuilder) {
    if(n == null) {
        sb.append("X")
        return
    }
    sb.append(n.data)
    getPreOrderString(n.left, sb)
    getPreOrderString(n.right, sb)
}

class TreeNode(val data: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
    private val size = 1

    fun getSize(): Int {
        return size + (left?.getSize() ?: 0) + (right?.getSize() ?: 0)
    }
}