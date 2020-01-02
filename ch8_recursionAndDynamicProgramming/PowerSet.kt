fun main(args: Array<String>) {
    val set = listOf('a','b','c')
    val powerset = getPowerset_binary(set)
    for(subset in powerset) {
        println(subset)
    }
}

fun getPowerset(set: List<Char>): Set<List<Char>> {
    return getPowerset(set, set.size - 1)
}

fun getPowerset(set: List<Char>, index: Int): Set<List<Char>> {
    if(index < 0)/*> */ return setOf(listOf())
    val result = hashSetOf<List<Char>>()
    val previous = getPowerset(set, index - 1)
    result.addAll(previous)
    for(subset in previous) {
        val newSubset = arrayListOf<Char>()
        newSubset.addAll(subset)
        newSubset.add(set[index])
        result.add(newSubset)
    }
    return result
}

fun getPowerset_binary(set: List<Char>): Set<List<Char>> {
    val result = hashSetOf<List<Char>>()
    val max = 1 shl set.size
    for(i in 0 until max) {
        val subset = binaryToSet(i, set)
        result.add(subset)
    }
    return result
}

fun binaryToSet(n: Int, set: List<Char>): List<Char> {
    var b = n
    var index = 0
    val result = arrayListOf<Char>()
    while (b > 0) {
        if(b and 1 == 1) result.add(set[index])
        index++
        b = b shr 1
    }
    return result
}

/*
This solution is wrong because it calculates the subsets of CONSECUTIVE elements instead of all subsets
 */
fun getPowerset_wrong(set: List<Char>): Set<List<Char>> {
    val result = hashSetOf<List<Char>>()
    for((i, smallest) in set.withIndex()) {
        getPowerset_wrong(set, listOf(smallest), i + 1, result)
    }
    return result
}

fun getPowerset_wrong(set: List<Char>, current: List<Char>, nextIndex: Int, result: HashSet<List<Char>>) {
    result.add(current)
    if(nextIndex < set.size) { //>
        val nextSubset = arrayListOf<Char>().apply{
            addAll(current)
            add(set[nextIndex])
        }
        if(!result.contains(nextSubset)) {
            getPowerset_wrong(set, nextSubset, nextIndex + 1, result)
        }
    }
}