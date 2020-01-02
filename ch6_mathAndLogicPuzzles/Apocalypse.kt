import kotlin.random.Random

fun main(args: Array<String>) {
    if(args.size != 1) throw Exception("Provide one argument: number of families")
    val n = args[0].toInt()
    val girls = simulatePopulation(n)
    val boys = 1 - girls
    println("Population ratio\n Boys: ${boys * 100}%\tGirls: ${girls * 100}%")
}

fun simulatePopulation(n: Int): Float {
    var count = 0
    var boys = 0
    var girls = 0
    while(count < n) { //>
        while(true){
            if(Random.nextInt(2) == 0){
                boys++
            } else {
                girls++
                break
            }
        }
        count++
    }
    return girls.toFloat()/(boys + girls)
}

