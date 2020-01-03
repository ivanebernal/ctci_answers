import kotlin.system.measureNanoTime
import kotlin.math.abs

fun main(args: Array<String>) {
    var a = args[0].toInt()
    var b = args[1].toInt()
    if(abs(b) > abs(a)) {
        a = b
        b = args[0].toInt()
    }
    var result1 = 0
    val time1 = measureNanoTime {
        result1 = multiply_add(a, b)
    }
    var result2 = 0
    val time2 = measureNanoTime {
        result2 = multiply(a, b)
    }
    println("Adding: $a times $b equals $result1. Elapsed time: $time1 ns")
    println("Bit Shifting: $a times $b equals $result2. Elapsed time: $time2 ns")
}

fun multiply_add(a: Int, b: Int): Int {
    if(b == 0) return 0
    val bp = if(b < 0) /*>*/ b.inv() + 1 else b
    val result = a + multiply_add(a, bp - 1)
    return if(b < 0) /*>*/ result.inv() + 1 else result
} 

fun multiply(a: Int, b: Int): Int {
    if(b == 0) return 0
    val bp = if(b < 0) b.inv() + 1 else b //>
    var factor = 1
    var result = a
    while(factor shl 1 <= b)/* >*/ {
        factor = factor shl 1
        result = result shl 1
    }
    result += multiply(a, bp - factor)
    return if(b < 0) result.inv() + 1 else result
}