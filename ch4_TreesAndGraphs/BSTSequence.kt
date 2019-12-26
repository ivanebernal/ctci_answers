import java.util.LinkedList

fun main(args: Array<String>) {
    val n1 = BSTNode(1)
    val n2 = BSTNode(2)
    val n3 = BSTNode(3)
    val n4 = BSTNode(4)
    val n5 = BSTNode(5)
    val n6 = BSTNode(6)
    val n7 = BSTNode(7)

    n2.left = n1
    n2.right = n3
    n6.left = n5
    n6.right = n7
    n4.left = n2
    n4.right = n6

    val sequences = findBSTSequence_book(n4)
    println("Total results: ${sequences.size}")
    sequences.forEach { sequence ->
        print("{")
        sequence.forEachIndexed { i, value -> 
            if(i < sequence.size - 1) print("$value, ") 
            else print("$value")
        }
        println("},")
    }
    println()
}

/*
This approach is wrong because it doesn't get all of the possible results, this assumes that the order
of the elementsin the array matter when the only thing that matters is that the root is at the beginning of
the array.

fun findBSTSequence(root: BSTNode?): Set<List<Int>> {
    if(root == null) return setOf(listOf<Int>())
    val left = findBSTSequence(root.left)
    val right = findBSTSequence(root.right)
    val result = hashSetOf<List<Int>>()
    left.forEach { l ->
        right.forEach { r ->
            result.add(getNewSequence(root, l, r)) 
        }
    }
    right.forEach { r ->
        left.forEach { l ->
            result.add(getNewSequence(root, r, l)) 
        }
        
    }
    return result
}

fun getNewSequence(root: BSTNode, seq1: List<Int>, seq2: List<Int>): List<Int> {
    val result = arrayListOf(root.value)
    result.addAll(seq1)
    result.addAll(seq2)
    return result
}
*/

fun findBSTSequence_book(root: BSTNode?): ArrayList<LinkedList<Int>> {
    val result = arrayListOf<LinkedList<Int>>()
    root?: run {
        result.add(LinkedList<Int>())
        return result
    }
    val prefix = LinkedList<Int>()
    prefix.add(root.value)
    
    val leftSeq = findBSTSequence_book(root.left)
    val rightSeq = findBSTSequence_book(root.right)

    leftSeq.forEach { left ->
        rightSeq.forEach { right ->
            val weaved = arrayListOf<LinkedList<Int>>()
            weaveLists(left, right, weaved, prefix)
            result.addAll(weaved)
        }
    }
    return result
}

fun weaveLists(first: LinkedList<Int>, second: LinkedList<Int>, 
    results: ArrayList<LinkedList<Int>>, prefix: LinkedList<Int>) {
        if(first.size == 0 || second.size == 0) {
            val result = prefix.clone() as LinkedList<Int>
            result.addAll(first)
            result.addAll(second)
            results.add(result)
            return
        }

        val headFirst = first.removeFirst()
        prefix.addLast(headFirst)
        weaveLists(first, second, results, prefix)
        prefix.removeLast()
        first.addFirst(headFirst)

        val headSecond = second.removeFirst()
        prefix.addLast(headSecond)
        weaveLists(first, second, results, prefix)
        prefix.removeLast()
        second.addFirst(headSecond)
}

fun findBSTHelper(root: BSTNode?, acc: ArrayList<Int>, result: HashSet<ArrayList<Int>>) {
    root ?: run { 
        result.add(acc)
        return
    }
    val rtl = ArrayList(acc)
    val ltr = ArrayList(acc)
    root.right?.let {rtl.add(it.value)}
    root.left?.let {rtl.add(it.value)}
    root.left?.let{ltr.add(it.value)}
    root.right?.let{ltr.add(it.value)}
    findBSTHelper(root.left, ltr, result)
    findBSTHelper(root.left, rtl, result)
    findBSTHelper(root.right, ltr, result)
    findBSTHelper(root.right, rtl, result)
}

class BSTNode(val value: Int) {
    var left: BSTNode? = null
    var right: BSTNode? = null
}