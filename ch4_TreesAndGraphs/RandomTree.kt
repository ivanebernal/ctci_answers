import kotlin.random.Random

fun main(args: Array<String>) {
    val tree = RandomTree()
    for(i in 0 until 10) {
        tree.insertInOrder(Random.nextInt(30))
    }
    val rand = tree.getRandomNode()
    println("Random node: ${rand?.data}")

}

class RandomTree {
    var root: TreeNode? = null

    fun size(): Int = root?.size ?: 0

    fun getRandomNode(): TreeNode? {
        root ?: return null
        val i = Random.nextInt(size())
        println("Size = ${size()}")
        println("Random index = $i")
        return root!!.getIthNode(i)
    }

    fun insertInOrder(data: Int) {
        root?.insertInOrder(data) ?: run { root = TreeNode(data) }
    }

}

class TreeNode(val data: Int, var size: Int = 0) {
    var left: TreeNode? = null
    var right: TreeNode? = null

    fun getIthNode(i: Int): TreeNode? {
        val leftSize = left?.size?:0
        if(i < leftSize) {
            return left?.getIthNode(i)
        } else if(i == leftSize) {
            return this
        } else {
            return right?.getIthNode(i - (leftSize + 1))
        }
    }

    fun insertInOrder(d: Int) {
        if(d <= data) {
            left?.insertInOrder(d) ?: run { left = TreeNode(d) }
        } else {
            right?.insertInOrder(d) ?: run { right = TreeNode(d) }
        }
        size += 1
    }

    fun find(d: Int): TreeNode? {
        if(d == data) return this
        if(d < data) return left?.find(d)
        if(d > data) return right?.find(d)
        return null
    }
}