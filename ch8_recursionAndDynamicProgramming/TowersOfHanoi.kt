fun main(args: Array<String>) {
    if(args.size != 1) throw Exception("Provide one argument: Number of towers")
    val n = args[0].toInt()
    val p1 = Pole()
    val p2 = Pole()
    val p3 = Pole()
    for(i in n downTo 1) {
        p1.push(i)
    }
    printPole(p3)
    hanoi(n, p1, p2, p3)
    // p3.moveTowers(n, p3, p2)
    printPole(p3)
}


//My solution
fun hanoi(n: Int, p1: Pole, p2: Pole, p3: Pole) {
    if(n == 1) {
        p3.push(p1.pop())
    } else if(n == 2) {
        p2.push(p1.pop())
        p3.push(p1.pop())
        p3.push(p2.pop())
    } else if(n > 2){
        hanoi(n - 1, p1, p2, p3)
        p2.push(p1.pop())
        hanoi(n - 1, p3, p2, p1)
        p3.push(p2.pop())
        hanoi(n - 1, p1, p2, p3)
    }
}

fun printPole(pole: Pole) {
    if(pole.top == null) {
        println("Empty pole")
        return
    }
    while (pole.top != null && pole.top?.next != null) {
        val t = pole.pop()
        print("($t) -> ")
    }
    println("(${pole.pop()})")
}

class Pole {
    var top: Tower? = null

    fun push(size: Int) {
        if(top == null) top = Tower(size)
        else {
            val newTower = Tower(size)
            newTower.next = top
            top = newTower
        }
    }

    fun pop(): Int {
        val result = top?.size ?: throw Exception("Empty pole")
        top = top!!.next
        return result
    }

    //Book solution
    //TODO: This is throwing an empty pole exception.....
    fun moveTowers(n: Int, destination: Pole, buffer: Pole) {
        if(n > 0) {
            moveTowers(n - 1, buffer, destination)
            moveTopTo(destination)
            buffer.moveTowers(n - 1, destination, this)
        }
    }

    fun moveTopTo(pole: Pole) {
        val tower = pop()
        pole.push(tower)
    }
}

class Tower(val size: Int) {
    var next: Tower? = null
}