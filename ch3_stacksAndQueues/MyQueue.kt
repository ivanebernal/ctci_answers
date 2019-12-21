fun main(args: Array<String>) {
    val queue = MyQueue<Int>()
    for(i in 0 until 30) queue.push(i)
    queue.pop()
    queue.pop()
    queue.pop()
    queue.push(1)
    queue.push(2)
    queue.push(3)
    queue.push(4)
    queue.push(5)
    queue.print()
}

class MyQueue<T>{
    val pushStack = Stack<T>()
    val popStack = Stack<T>()

    fun pop(): T {
        if(!pushStack.isEmpty()) {
            shiftStacks(pushStack, popStack)
        }
        return popStack.pop()
    }

    fun peek(): T {
        if(!pushStack.isEmpty()) {
            shiftStacks(pushStack, popStack)
        }
        return popStack.peek()
    }

    fun push(newElement: T) {
        if(!popStack.isEmpty()) {
            shiftStacks(popStack, pushStack)
        }
        pushStack.push(newElement)
    }

    fun shiftStacks(fromStack: Stack<T>, toStack: Stack<T>) {
        while(!fromStack.isEmpty()) {
            toStack.push(fromStack.pop())
        }
    }

    fun print() {
        if(!popStack.isEmpty()) {
            shiftStacks(popStack, pushStack)
        }
        printStack(pushStack)
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