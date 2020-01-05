fun main(args: Array<String>) {
    if(args.size != 1) throw Exception("Provide string to get permutations for")
    val s = args[0]
    val perms = getPerms_app2(s)
    perms.forEach{ println(it) }
}

fun getPermutations(s: String): Set<String> {
    if(s.length <= 1) return setOf(s) //>
    val prevPerms = getPermutations(s.substring(1))
    return weave(prevPerms, s.get(0))
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

fun getPerms_app2(s: String): Set<String> {
    if(s.length <= 1) return setOf(s) //>
    val result = hashSetOf<String>()
    for(i in 0 until s.length) {
        val newS = s.substring(0, i) + s.substring(i + 1)
        val perms = getPerms_app2(newS)
        val newPerms = perms.map { s.get(i) + it }
        result.addAll(newPerms)
    }
    return result
}