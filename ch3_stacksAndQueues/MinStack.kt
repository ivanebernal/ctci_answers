fun main(args: Array<String>) {
    val stack = MinStack_MemEfficient(Node(6))
    stack.push(Node(6))
    stack.push(Node(3))
    stack.push(Node(2))
    stack.push(Node(5))
    stack.push(Node(7))
    stack.push(Node(1))
    stack.push(Node(9))
    printStack(stack)
    println("Current stack min: ${stack.min()}")
    stack.pop()
    stack.pop()
    stack.pop()
    stack.pop()
    printStack(stack)
    println("Current stack min: ${stack.min()}")
}

class MinStack(var head: NodeWithMin? = null) {

    fun pop(): NodeWithMin {
        if(head == null) {
            throw Exception("Stack empty")
        }
        var result = head!!
        head = head!!.next
        result.next = null
        return result
    }

    fun min(): NodeWithMin = head?.next ?: throw Exception("Stack empty")

    fun add(n: NodeWithMin) {
        if(head == null) {
            head = n
        } else {
            n.next = head
            head = n
            if(head!!.min.d > head!!.next!!.min.d) head!!.min = head!!.next!!.min
            println("Current value:${head!!.d} Current min: ${head!!.min.d}")
        }
    }
}

class NodeWithMin(val d: Int) {
    var next: NodeWithMin? = null
    var min: NodeWithMin = this

    fun appendToTail(d: Int) {
        var n = this
        while(n.next != null) {
            n = n.next!!
        }
        n.next = NodeWithMin(d)
    }
}

//MEMORY EFICIENT
open class Stack(var head: Node? = null) {
    open fun pop(): Node {
        if(head == null) {
            throw Exception("Stack empty")
        }
        var result = head!!
        head = head!!.next
        result.next = null
        return result
    }

    fun peek(): Int {
        if(head == null) throw Exception("Stack empty")
        return head!!.d
    }

    open fun push(n: Node) {
        if(head == null) {
            head = n
        } else {
            n.next = head
            head = n
        }
    }
}

class MinStack_MemEfficient: Stack {
    val mins = Stack()

    constructor(head: Node): super(head) {
        mins.push(Node(head.d))
    }

    fun min(): Int = mins.peek()

    override fun pop(): Node {
        val result = super.pop()
        println("Popped: ${result.d}, Current min: ${min()}")
        if(result.d == min()) mins.pop()
        return result
    }

    override fun push(n: Node) {
        if(head == null) mins.push(n)
        else if(n.d <= min()) mins.push(Node(n.d))    
        super.push(n)
    }
}

class Node(val d: Int) {
    var next: Node? = null
}

fun printStack(stack: MinStack) {
    printLinkedList(stack.head)
}

fun printStack(stack: MinStack_MemEfficient) {
    printLinkedList(stack.head)
}

fun printLinkedList(h: Node?) {
    if(h == null) {
        println("Stack empty")
        return
    }
    var n = h!!
    while(n.next != null) {
        print("(${n.d})->")
        n = n.next!!
    }
    println("(${n.d})")
}

fun printLinkedList(h: NodeWithMin?) {
    if(h == null) {
        println("Stack empty")
        return
    }
    var n = h!!
    while(n.next != null) {
        print("(${n.d})->")
        n = n.next!!
    }
    println("(${n.d})")
}
