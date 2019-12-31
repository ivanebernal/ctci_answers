fun main(args: Array<String>) {
    if (args.size != 1) throw Exception("One argument must be provided: an Integer to be swapped")
    val n = args[0].toLong()
    val swapped = pairwiseSwap(n)
    println("The number $n is $swapped when its pairs are swapped")
}

fun pairwiseSwap(n: Long): Long {
    val evenMask = 0x555555555
    val oddMask = 0xaaaaaaaa
    val even = n and evenMask
    val odd = n and oddMask
    return even shl 1 or odd shr 1
}