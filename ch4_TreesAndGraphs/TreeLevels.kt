import java.util.concurrent.ConcurrentLinkedQueue
import kotlin.math.max

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
    val lls = getLevels(n0)
    for(ll in lls) {
        printLinkedList(ll)
    }
}

class LinkedListNode(val value: Int) {
    var next: LinkedListNode? = null
}

class TreeNode(val value: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
    var level: Int = 0
}

fun getLevels(root: TreeNode): Array<LinkedListNode?> {
    val depth = getDepth(root)
    val result = Array<LinkedListNode?>(depth) { null }
    val visitQueue = ConcurrentLinkedQueue<TreeNode>()
    visitQueue.add(root)
    while(!visitQueue.isEmpty()){
        val current = visitQueue.poll()
        val nextLevel = current.level + 1
        current.left?.level = nextLevel
        current.right?.level = nextLevel
        current.left?.let{visitQueue.add(it)}
        current.right?.let{visitQueue.add(it)}
        result[current.level]?.let{
            it.addToTail(current.toLinkedListNode())
        } ?: run {
            result[current.level] = current.toLinkedListNode()
        }
    }
    return result
}

fun LinkedListNode.addToTail(node: LinkedListNode) {
    var c = this
    while(c.next != null) {
        c = c.next!!
    }
    c.next = node
}

fun getDepth(root: TreeNode): Int {
    return getDepth(root, 1)
}

fun getDepth(root: TreeNode?, level: Int): Int {
    if(root == null) return level - 1
    if(root.left == null && root.right == null) return level
    else return max(getDepth(root.left, level + 1), getDepth(root.right, level + 1))
}

fun TreeNode.toLinkedListNode(): LinkedListNode {
    return LinkedListNode(this.value)
}

fun printLinkedList(h: LinkedListNode?) {
    var n = h
    while(n != null && n.next != null) {
        print("(${n.value})->")
        n = n.next
    }
    if(n != null) println("(${n.value})")
}