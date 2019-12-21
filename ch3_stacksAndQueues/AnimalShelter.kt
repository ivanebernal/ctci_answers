fun main(args: Array<String>) {
    val shelter = AnimalShelter()
    shelter.enqueue(Dog())
    shelter.enqueue(Dog())
    shelter.enqueue(Cat())
    shelter.enqueue(Dog())
    shelter.enqueue(Cat())
    shelter.enqueue(Cat())
    shelter.enqueue(Cat())
    shelter.enqueue(Cat())
    shelter.enqueue(Dog())
    shelter.enqueue(Dog())
    shelter.enqueue(Cat())
    shelter.enqueue(Dog())
    shelter.enqueue(Cat())
    shelter.enqueue(Dog())
    printAnimalShelter(shelter)
    shelter.dequeueAny()
    shelter.dequeueAny()
    shelter.dequeueAny()
    shelter.dequeueAny()
    printAnimalShelter(shelter)
    shelter.dequeueCat()
    shelter.dequeueCat()
    shelter.dequeueAny()
    shelter.dequeueAny()
    printAnimalShelter(shelter)
}

interface Animal {
    var entryN: Int
}
class Cat: Animal {
    override var entryN = 0
}
class Dog: Animal {
    override var entryN = 0
}
class AnimalShelter {
    val catQueue = Queue<Cat>()
    val dogQueue = Queue<Dog>()
    var totalAnimals = 0

    fun enqueue(animal: Animal) {
        animal.entryN = totalAnimals
        totalAnimals += 1
        if(animal is Dog) {
            dogQueue.enqueue(animal)
        } else if(animal is Cat){
            catQueue.enqueue(animal)
        } else {
            throw IllegalArgumentException("No animal species specified")
        }
    }

    fun dequeueDog(): Dog {
        if(dogQueue.isEmpty()) {
            throw Exception("No more dogs left!")
        }
        return dogQueue.dequeue()
    }

    fun dequeueCat(): Cat {
        if(catQueue.isEmpty()) {
            throw Exception("No more cats left!")
        }
        return catQueue.dequeue()
    }

    fun dequeueAny(): Animal {
        if(catQueue.isEmpty()) {
            return dogQueue.dequeue()
        }
        if(dogQueue.isEmpty()) {
            return catQueue.dequeue()
        }
        val cat = catQueue.peek()
        val dog = dogQueue.peek()
        return if(dog.entryN < cat.entryN){ 
            dogQueue.dequeue()
        } else { 
            catQueue.dequeue()
        }
    }
}

class Queue<T> {
    var head: Node<T>? = null
    var tail = head

    fun enqueue(newValue: T) {
        if(head == null) {
            head = Node(newValue)
            tail = head
            return
        }
        val n = Node(newValue)
        n.next = head
        n.next?.prev = n
        head = n
    }

    fun dequeue(): T {
        if(tail == null) {
            throw Exception("Empty queue")
        }
        if(tail == head) {
            val result = tail!!.d
            tail == null
            head == null
            return result
        }
        val result = tail!!.d
        tail?.prev?.next = null
        tail = tail?.prev
        return result
    }

    fun peek(): T {
        if(tail == null) {
            throw Exception("Empty queue")
        }
        return tail!!.d
    }

    fun isEmpty(): Boolean = tail == null
}

class Node<T>(val d: T) {
    var next: Node<T>? = null
    var prev: Node<T>? = null
}

fun printAnimalShelter(shelter: AnimalShelter) {
    print("Dogs: ")
    printDogs(shelter.dogQueue.head)
    print("Cats: ")
    printCats(shelter.catQueue.head)
    
}

fun printDogs(h: Node<Dog>?) {
    var n = h
    while(n != null && n.next != null) {
        print("🐶(${n!!.d.entryN})->")
        n = n.next
    }
    if(n != null) println("🐶(${n!!.d.entryN})")
}

fun printCats(h: Node<Cat>?) {
    var n = h
    while(n != null && n.next != null) {
        print("🐱(${n!!.d.entryN})->")
        n = n.next
    }
    if(n != null) println("🐱(${n!!.d.entryN})")
}