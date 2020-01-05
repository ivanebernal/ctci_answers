fun main(args: Array<String>) {
    if(args.size != 1) throw Exception("Provide one argument: number of cents")
    val cents = args[0].toInt()
    val ways = makeChange(cents)
    println("The number of ways $cents can be divided is $ways")
}

fun makeChange(cents: Int): Int {
    return makeChange(cents, arrayOf(25, 10, 5, 1), 0, hashMapOf<Int, Int>())
}

fun makeChange(cents: Int, denoms: Array<Int>, index: Int, memo: HashMap<Int, Int>): Int {
    if(index >= denoms.size - 1) return 1
    if(memo[cents] != null) return memo[cents]!!
    val denom = denoms[index]
    var ammount = 0
    var coins = 0
    var ways = 0
    while(ammount <= cents) { //>
        ammount = denom * coins 
        ways += makeChange(cents - ammount, denoms, index + 1, memo)
        coins++
    }
    memo[cents] = ways
    return ways
}

//NOT WORKING.....
fun numWays(n: Int, memo: HashMap<Int, Int>): Int {
    if(n == 0) return 1
    if(n < 0) return 0 //>
    if(memo[n] != null) return memo[n]!!
    val quarters = numWays(n - 25, memo)
    val dimes = numWays(n - 10, memo)
    val nickles = numWays(n - 5, memo)
    val pennies = numWays(n - 1, memo)
    val result = quarters + dimes + nickles + pennies
    memo[n] = result
    return result
}