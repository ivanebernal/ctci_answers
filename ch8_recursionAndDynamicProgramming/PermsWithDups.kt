fun main(args: Array<String>) {
    if(args.size != 1) throw Exception("Provide string to get permutations for")
    val s = args[0]
    val perms = getPermutations(s)
    perms.forEach{ println(it) }
}

fun getPermutations(s: String): Set<String> {
    if(s.length <= 1) return setOf(s) //>
    val charCount = hashMapOf<Char, Int>()
    for(i in 0 until s.length) {
        val char = s.get(i)
        if(charCount[char] == null) charCount[char] = 1
        else charCount[char] = charCount[char]!! + 1
    }
    val memo = hashSetOf<String>()
    return getPerms(charCount, memo)
}

fun getPerms(charCount: HashMap<Char, Int>, memo: HashSet<String>): Set<String> {
    if(hasAllSameChars(charCount)) return setOf(buildString(charCount))
    val perms = hashSetOf<String>()
    for(key in charCount.keys) {
        charCount[key] = charCount[key]!! - 1
        if(charCount[key]?:-1 >= 0){
            val current = buildCompressedString(charCount)
            if(!memo.contains(current)){
                val prevPerms = getPerms(charCount, memo)
                perms.addAll(weave(prevPerms, key))
                memo.add(current)
            }
        }
        charCount[key] = charCount[key]!! + 1
    }
    return perms
}

fun buildCompressedString(charCount: Map<Char, Int>): String {
    val sb = StringBuilder()
    for(key in charCount.keys) {
        sb.append(key)
        sb.append(charCount[key])
    }
    return sb.toString()
}

fun hasAllSameChars(charCount: Map<Char, Int>): Boolean {
    var charFound = false
    for(key in charCount.keys) {
        if(charCount[key]!! > 0) {
            if(charFound) return false
            charFound = true
        }
    }
    return true
}

fun buildString(charCount: HashMap<Char, Int>): String {
    val sb = StringBuilder()
    for(key in charCount.keys) {
        for(j in 0 until (charCount[key]?:0)) sb.append(key)
    }
    return sb.toString()
}

fun weave(perms: Set<String>, newChar: Char): Set<String> {
    val result = hashSetOf<String>()
    perms.forEach {perm ->
        for(i in 0..perm.length) {
            val newPerm = insertChar(perm, newChar, i)
            result.add(newPerm)
        }
    }
    return result
}

fun insertChar(s: String, newChar: Char, position: Int): String {
    val leftSubs = s.substring(0, position)
    val sb = StringBuilder(leftSubs)
    sb.append(newChar)
    sb.append(s.substring(position))
    return sb.toString()
}