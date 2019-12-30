import kotlin.math.max

fun main(args: Array<String>) {
    if(args.size != 1) throw Exception("Provide exactly one argument: an int number representing the input binary sequence")
    val n = args[0].toInt()
    println("Largest 1's sequence for $n (${Integer.toBinaryString(n)}) when one bit is flipped from 0 to 1: ${n.getLargestSequenceOfOnes_book()}")
}

fun Int.getLargestSequenceOfOnes(): Int {
    var m = this
    var maxCount = 0
    var count1 = 0
    var count2 = 0
    var zeroFound1 = false
    var zeroFound2 = false
    var overlap = false
    do {
        val digit = m and 1
        if(digit == 1) {
            if(zeroFound1 && count1 > 0) overlap = true
            count1 += 1
            if(overlap) {
                count2 += 1
            }
        } else if(digit == 0) {
            if(zeroFound1 || overlap) {
                maxCount = max(count1, maxCount)
                count1 = 0
            } else if(!zeroFound1) {
                count1 += 1
            }
            zeroFound1 = !zeroFound1
            if(overlap) {
                if(zeroFound2) {
                    overlap = false
                    maxCount = max(count2, maxCount)
                    count2 = 0
                } else {
                    count2 += 1
                }
                zeroFound2 = !zeroFound2
            }
        }
        m = m shr 1
    } while(m > 0)
    maxCount = max(count1, max(count2, maxCount))
    return maxCount
}

fun Int.getLargestSequenceOfOnes_book(): Int {
    var m = this
    if(m.inv() == 0) return Integer.BYTES * 8
    var currentLength = 0
    var previousLength = 0
    var maxLength = 1
    do{
        if((m and 1) == 1) {
            currentLength += 1
        } else if((m and 1 == 0)) {
            previousLength = if((m and 2) == 0) 0 else currentLength
            currentLength = 0
        }
        maxLength = max(previousLength + currentLength + 1, maxLength)
        m = m shr 1
    } while(m != 0)
    return maxLength
}