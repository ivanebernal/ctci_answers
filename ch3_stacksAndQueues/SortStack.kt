fun main(args: Array<String>) {
    val stack = Stack<Int>()
    stack.push(1)
    stack.push(3)
    stack.push(8)
    stack.push(5)
    stack.push(6)
    stack.push(0)
    stack.push(2)
    stack.push(4)
    printStack(stack)
    sortStack(stack)
    printStack(stack)
}

fun sortStack(stack: Stack<Int>) {
    val hStack = Stack<Int>()
    while(!stack.isEmpty()) {
        val top = stack.pop()
        if(hStack.isEmpty()) {
            hStack.push(top)
        } else {
            while(!hStack.isEmpty() && hStack.peek() > top) {
                stack.push(hStack.pop())
            }
            hStack.push(top)
        }
    }
    shiftStacks(hStack, stack)
}

fun <T> shiftStacks(fromStack: Stack<T>, toStack: Stack<T>) {
    while(!fromStack.isEmpty()) {
        toStack.push(fromStack.pop())
    }
}

class Stack<T> {
    var size: Int = 0
    var head: Node<T>? = null

    fun pop(): T {
        if(head == null) {
            throw Exception("Stack empty")
        }
        var result = head!!.d
        head = head!!.next
        size -= 1
        return result
    }

    fun peek(): T {
        if(head == null) throw Exception("Stack empty")
        return head!!.d
    }

    fun push(value: T) {
        val n = Node(value)
        if(head == null) {
            head = n
        } else {
            n.next = head
            head = n
        }
        size += 1
    }
    
    fun isEmpty(): Boolean {
        return size == 0
    }
}

class Node<T>(val d: T) {
    var next: Node<T>? = null
}

fun <T> printStack(stack: Stack<T>) {
    var h = stack.head
    while(h != null && h.next != null) {
        print("(${h!!.d})->")
        h = h!!.next
    }
    println("(${h!!.d})")
}