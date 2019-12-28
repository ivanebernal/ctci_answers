fun main(args: Array<String>){
    val n = insert(1024, 19, 2, 6)
    println(Integer.toBinaryString(n))
}

fun insert(n: Int, m: Int, i: Int, j: Int): Int {
    val maskLeft = ((1 shl j + 1) - 1).inv()
    println(Integer.toBinaryString(maskLeft))
    val maskRight = (1 shl i) - 1
    println(Integer.toBinaryString(maskRight))
    val mask = maskLeft or maskRight
    println(Integer.toBinaryString(mask))
    val maskedN = n and mask
    println(Integer.toBinaryString(maskedN))
    return maskedN or m shl i
}