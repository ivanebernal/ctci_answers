fun main(args: Array<String>) {
    if(args.size > 1 || args.size == 0) {
        Exception("Double number argument must be provided")
    }
    val number = args[0].toDouble()
    println("$number represented in binary is ${number.toBinaryString()}")
}

fun Double.toBinaryString(): String {
    if(this <= 0 || this >= 1) {
        throw IllegalArgumentException("Value must be between 0 and 1")
    }
    val sb = StringBuilder("0.")
    var count = 0
    var n = this
    while(n != 0.0) {
        if(count > 32){
            throw Exception("Number cannot be represented in less than 32 digits.\nString so far: ${sb.toString()}")
        }
        n *= 2
        if(n >= 1) {
            sb.append("1")
            n -= 1
        } else {
            sb.append("0")
        }
        count++
    }
    return sb.toString()
}