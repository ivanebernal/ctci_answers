import kotlin.math.max
import kotlin.math.min

fun main(args: Array<String>) {
    if(args.size != 1) throw Exception("Provide a sorted list of numbers to find the magic index in\n(Please provide them in comma separated format)")
    val numbers = args[0].split(",").map { it.toInt() }
    val magicIndex = findMagicIndex_repeat(numbers)
    magicIndex?.let { println("The magic index is $magicIndex") } ?: run { println("There's no magic index") }
}

fun findMagicIndex(arr: List<Int>): Int? {
    return findMagicIndex(arr, 0)
}

fun findMagicIndex(arr: List<Int>, index: Int): Int? {
    var i = index
    while(arr[i] < 0) { //>
        i++
        if(i == arr.size) return null
    }
    if(arr[i] == i) return i
    i = if(i > arr[i]) i + 1 else arr[i]
    if(i >= arr.size) return null
    return findMagicIndex(arr, i)
}

fun findMagicIndex_bins(arr: List<Int>): Int? {
    return findMagicIndex_bins(arr, 0, arr.size - 1)
}

fun findMagicIndex_bins(arr: List<Int>, lower: Int, upper: Int): Int? {
    if(lower >= upper) return null
    val i = (upper + lower)/2
    if(arr[i] == i) return i
    else if(arr[i] < i)/*> */ return findMagicIndex_bins(arr, i + 1, upper)
    else return findMagicIndex_bins(arr, lower, i - 1)
}



fun findMagicIndex_repeat(arr: List<Int>): Int? {
    return findMagicIndex_repeat(arr, 0, arr.size - 1)
}

fun findMagicIndex_repeat(arr: List<Int>, lower: Int, upper: Int): Int? {
    if(upper < lower) /*> */ return null
    val midIndex = (upper + lower)/2
    val midValue = arr[midIndex]
    if(midValue == midIndex) return midIndex
    
    val leftIndex = min(midIndex - 1, midValue)
    val left = findMagicIndex_repeat(arr, lower, leftIndex)
    if(left != null) return left

    val rightIndex = max(midIndex + 1, midValue)
    val right = findMagicIndex_repeat(arr, rightIndex, upper)
    return right
}

