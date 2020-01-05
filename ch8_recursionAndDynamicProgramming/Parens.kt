fun main(args: Array<String>) {
    if(args.size != 1) throw Exception("Provide one argument 'n': number of parentheses pairs")
    val n = args[0].toInt()
    val parens = parens_app2(n)
    parens.forEach{ println(it) }
}

fun parens(n: Int): Set<String> {
    if(n == 0) return setOf("")
    val result = hashSetOf<String>()
    val sequences = parens(n - 1)
    sequences.forEach { sequence ->
        result.add("()" + sequence)
        result.add(sequence + "()")
        result.add("(" + sequence + ")")
    }
    return result
}

fun parens_app2(n: Int): Set<String> {
    return parensHelper("", n, n)
}

fun parensHelper(currentSequence: String, remainingLeft: Int, remainingRight: Int): Set<String> {
    if(remainingLeft == 0 && remainingRight == 0) return setOf(currentSequence)
    val result = hashSetOf<String>()
    if(remainingLeft > 0) {
        val next = parensHelper(currentSequence + "(", remainingLeft - 1, remainingRight)
        result.addAll(next)
    }
    if(remainingRight > 0 && remainingRight > remainingLeft) {
        val next = parensHelper(currentSequence + ")", remainingLeft, remainingRight - 1)
        result.addAll(next)
    }
    return result
}