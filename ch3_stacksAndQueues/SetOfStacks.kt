fun main(args: Array<String>) {
    val stacks = SetOfStacks<Int>(10)
    for(i in 0 until 30) stacks.push(i)
    for(i in 0 until 11) stacks.pop()
    for(i in 0 until 5) stacks.popAt(0)
    printSetOfStacks(stacks)
}

class SetOfStacks<T>(val maxCapacity: Int) {
    val stacks = mutableListOf<Stack<T>>()

    fun push(newValue: T) {
        if(stacks.size == 0 || stacks.last().size == maxCapacity) {
            val stack = Stack<T>()
            stacks.add(stack)
        }
        var stack = stacks.last()
        stack.push(newValue)
    }

    fun pop(): T {
        if(stacks.size == 0) {
            throw Exception("Empty stack")
        }
        val result = stacks.last().pop()
        if(stacks.last().size == 0) {
            stacks.removeAt(stacks.size - 1)
            println("Dropped stack, number of stacks: ${stacks.size}")
        }
        return result
    }
    
    fun popAt(index: Int): T {
        if(stacks.size <= index) {
            throw IndexOutOfBoundsException("Size: ${stacks.size}, Index: $index")
        }
        val result = stacks[index].pop()
        if(stacks[index].size == 0) {
            stacks.removeAt(index)
            println("Dropped stack, number of stacks: ${stacks.size}")
        }
        return result
    }

    fun peek(): T {
        if(stacks.size == 0) {
            throw Exception("Empty stack")
        }
        return stacks.last().peek()
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
}

class Node<T>(val d: T) {
    var next: Node<T>? = null
}

fun <T> printSetOfStacks(stacks: SetOfStacks<T>) {
    for(stack in stacks.stacks) {
        printStack(stack)
    }
}

fun <T> printStack(stack: Stack<T>) {
    var h = stack.head
    while(h != null && h.next != null) {
        print("(${h!!.d})->")
        h = h!!.next
    }
    println("(${h!!.d})")

}