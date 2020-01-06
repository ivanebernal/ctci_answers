fun main(args: Array<String>) {
    if(args.size != 2) throw Exception("Provide two arguments: boolean expression (with 0,1,&,^,|) and boolean value (true|false)")
    val expression = args[0]
    val result = args[1].toBoolean()
    val count = countEval(expression, result)
    println("The number of ways $expression can be parenthesized to be $result is $count")
}

fun countEval(exp: String, desired: Boolean): Int {
    var count = 0
    eval(exp).forEach { if(it == desired) count++ }
    return count
}

fun eval(exp: String): List<Boolean> {
    if(exp.length == 1) {
        return listOf(exp == "1")
    }
    val result = arrayListOf<Boolean>()
    for(i in 0 until exp.length) {
        if(exp[i] != '1' || exp[i] != '0') {
            val left = eval(exp.substring(0, i))
            val right = eval(exp.substring(i + 1))
            left.forEach { l ->
                right.forEach { r -> 
                    when(exp[i]) {
                        '^' -> result.add(l xor r)
                        '&' -> result.add(l && r)
                        '|' -> result.add(l || r)
                    }
                }
            }
        }
    }
    return result
}


fun generateExpressionSet(exp: String): Set<String> {
    if(exp.isEmpty()) return setOf("")
    if(exp.length == 3) return setOf("($exp)")
    if(exp.length == 1) return setOf("$exp") 
    val result = hashSetOf<String>()
    generateExpressionSet(exp.drop(2)).forEach { tail ->
        val newExp = exp.substring(0,2) + tail
        result.add("($newExp)")
    }
    generateExpressionSet(exp.drop(4)).forEach { tail ->
        val newExp = "(${exp.substring(0,3)})${exp[3]}" + tail
        result.add("($newExp)")
    }
    return result
}