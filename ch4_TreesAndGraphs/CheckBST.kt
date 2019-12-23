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
    n2.right = n3
    n4.left = n2
    n4.right = n5
    n6.left = n4
    n6.right = n8
    n8.left = n7
    n8.right = n9

    println(isBST_constSpace(n6))
}

fun isBST(root: TreeNode): Boolean {
    val elements = arrayListOf<Int>()
    inOrder(root, elements)
    return checkOrder(elements)
}

fun inOrder(root: TreeNode?, elements: ArrayList<Int>) {
    root ?: return
    inOrder(root.left, elements)
    elements.add(root.value)
    inOrder(root.right, elements)
}

fun checkOrder(elements: ArrayList<Int>): Boolean {
    for(i in 1 until elements.size) {
        if(elements[i] < elements[i - 1]) {
            return false
        }
    }
    return true
}
//Book solutions

fun isBST_constSpace(root: TreeNode?): Boolean {
    return checkBST_constSpace(root, null, null)
}

fun checkBST_constSpace(root: TreeNode?, min: Int?, max: Int?): Boolean {
    if(root == null) return true
    if(min != null && root.value <= min || max != null && root.value > max) {
        return false
    }

    if(!checkBST_constSpace(root.left, min, root.value) || !checkBST_constSpace(root.right, root.value, max)) {
        return false
    }

    return true
}



/////////////////////////////////////////////////////////////////////////////////////////////////////////////

class TreeNode(val value: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}