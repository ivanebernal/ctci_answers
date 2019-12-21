
fun main() {
    val n = Node('h').apply {
        appendToTail('o')
        appendToTail('l')
        appendToTail('h')
        appendToTail('l')
        appendToTail('e')
		appendToTail('h')
    }
    print(isPalyndrome_recursive(n))
}

fun isPalyndrome_recursive(n: Node): Boolean {
    val length = getLength(n)
    return isPalyndromeRecurse(n, length).isPalyndrome
}

fun isPalyndromeRecurse(n: Node?, length: Int): PalyndromeResult {
    if (length == 0 || n == null) {
        return PalyndromeResult(n, true)
    } else if(length == 1) {
        return PalyndromeResult(n.next, true)
    }
    
    val result = isPalyndromeRecurse(n.next, length - 2)
    
    if(!result.isPalyndrome || result.result == null) return result
    
    result.isPalyndrome = n.d == result.result!!.d
    result.result = result.result!!.next
    return result
}


fun getLength(h: Node?): Int {
    var n = h
    var length = 0
    while(n != null) {
        length += 1
        n = n.next
    }
    return length
}


fun isPalyndrome(n: Node): Boolean {
    val reversed = copyReversed(n)
    printLinkedList(reversed)
    return compare(n, reversed)
}

fun compare(n1: Node, n2: Node): Boolean {
    var h1 = n1
    var h2 = n2
    while(h1.next != null && h2.next != null) {
        if(h1.d != h2.d) {
            return false
        }
        h1 = h1.next!!
        h2 = h2.next!!
    }
    return true
}

fun copyReversed(h: Node): Node {
    var n: Node? = h
    var result: Node? = null
    while(n != null) {
        if (result == null) result = Node(n.d)
        else {
            val newH = Node(n.d)
            newH.next = result
            result = newH
        }
        n = n.next;
    }
    return result!!
}

class Node(val d: Char) {
    var next: Node? = null
    fun appendToTail(c: Char) {
        var current = this
        while(current.next != null) {
            current = current.next!!
        }
        current.next = Node(c)
    }
}

data class PalyndromeResult(var result: Node?, var isPalyndrome: Boolean)

fun printLinkedList(h: Node) {
    var n = h
    while(n.next != null) {
        print("(${n.d})->")
        n = n.next!!
    }
    println("(${n.d})")
}
