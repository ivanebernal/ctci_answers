import kotlin.system.measureNanoTime

fun main(args: Array<String>) {
    if(args.size != 1) throw Exception("Provide one argument: number of steps in staircase")
    val n = args[0].toInt()
    val result = countWays_optimized(n)
    val nomemo = measureNanoTime { countWays_noMemo(n) }
    val memo = measureNanoTime { countWays(n) }
    val optimized = measureNanoTime { countWays_optimized(n) }
    println("Result: ${result}\nBrute force: ${nomemo} ns\nMemoized: ${memo} ns,\nIterative: ${optimized} ns")
}

fun countWays_noMemo(n: Int): Int {
    if(n < 0) return 0 //>
    if(n == 0) return 1
    return countWays_noMemo(n - 1) + countWays_noMemo(n - 2) + countWays_noMemo(n - 3)
}

fun countWays(n: Int): Int {
    val memo = Array<Int>(n + 1) { -1 }
    return countWays(n, memo)
}

fun countWays(n: Int, memo: Array<Int>): Int {
    if(n < 0) return 0 //>
    if(memo[n] > -1) return memo[n]
    if(n == 0){
        memo[n] = 1
    } else {
        memo[n] = countWays(n - 1, memo) + countWays(n - 2, memo) + countWays(n - 3, memo)
    }
    return memo[n]
}

fun countWays_optimized(n: Int): Int {
    if(n < 0) return -1 //>
    if(n <= 3){ //>
        return when(n) {
            1 -> 1
            2 -> 2
            3 -> 4
            else -> -1
        }
    }
    var a = 1
    var b = 2
    var c = 4
    var result = 0
    for(i in 4..n) {
        result = a + b + c
        a = b
        b = c
        c = result
    }
    return result
}