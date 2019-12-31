fun main(args: Array<String>) {
    if(args.size != 2) throw Exception("Provide two arguments: Integer a and Integer b")
    val a = args[0].toInt()
    val b = args[1].toInt()
    val bits = getBitsToConvert(a, b)
    println("The number of bits required to convert $a (${Integer.toBinaryString(a)}) to $b (${Integer.toBinaryString(b)}) is $bits")
}

fun getBitsToConvert(a: Int, b: Int): Int {
    var r = a xor b
    var count = 0
    while(r > 0) {
        count++
        r = r and (r-1)
    }
    return count
}