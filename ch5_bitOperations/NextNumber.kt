fun main(args: Array<String>){
    if(args.size != 1) throw Exception("Must provide one argument: positive integer")
    val n = args[0].toInt()
    val prev = n.getPrev_book()
    val next = n.getNext_book()
    println("The previous number for $n (${Integer.toBinaryString(n)}) that has the same 1 bits is ${if(prev != -1) prev else "none"} (${if (prev != -1)Integer.toBinaryString(prev) else ""}), the next is ${if(next != -1) next else "none"} (${if(next != -1)Integer.toBinaryString(next) else ""})")
}

fun Int.getNext(): Int {
    var m = this
    var count = 0
    var trailing = true
    while(trailing && m > 0) {
        val current = m and 1
        if(current == 1 && (m and 2) == 0) {
            trailing = false
        }
        m = m shr 1
        count += 1
    }
    var shifted = this xor (1 shl count)
    val rmask = (1 shl count) - 1
    var right = shifted and rmask
    val lmask = rmask.inv()
    val left = shifted and lmask
    var toShift = count
    while(right and 1 == 0 && toShift > 0) {
        right = right shr 1
        toShift -= 1
    }
    right = right shr 1
    return right or left
}

fun Int.getPrev(): Int {
    var m = this
    var count = 0
    var trailing = true
    while(trailing && m > 0) {
        val current = m and 1
        if(current == 0 && (m and 2) != 0) {
            trailing = false
        }
        m = m shr 1
        count += 1
    }
    val shifted = this xor (1 shl (count))
    val rmask = (1 shl count) - 1
    var right = shifted and rmask
    val lmask = rmask.inv()
    if(lmask and this == 0) return -1 
    val left = shifted and lmask
    var toShift = count - 1
    val mask = 1 shl (count - 1)
    right = ((right shl 1) and rmask) + 1
    while((right and mask) != mask && toShift > 0) {
        right = ((right shl 1) and rmask)
        toShift -= 1
    }
    return right or left
}

fun Int.getNext_book(): Int {
    var c = this
    var c0 = 0
    var c1 = 0
    while((c and 1 == 0) && (c != 0)) {
        c0+=1
        c = c shr 1
    }
    while(c and 1 == 1) {
        c1+=1
        c = c shr 1
    }
    if(c0 + c1 == 31 || c0 + c1 == 0) {
        return -1
    }
    return this + (1 shl c0) + (1 shl (c1 - 1)) - 1
}

fun Int.getPrev_book(): Int {
    var c = this
    var c0 = 0
    var c1 = 0
    while(c and 1 == 1) {
        c1+=1
        c = c shr 1
    }
    if(c == 0) return -1
    while((c and 1 == 0) && (c != 0)) {
        c0 += 1
        c = c shr 1
    }
    return this - (1 shl c1) - (1 shl (c0 - 1)) + 1
}