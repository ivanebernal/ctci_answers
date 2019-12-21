import java.util.concurrent.ConcurrentLinkedQueue
class Graph<T> {
    val roots = mutableListOf<Node<T>>()
}

class Node<T>(val value: T) {
    val neighbors = mutableListOf<Node<T>>()
}

fun main(args: Array<String>) {
    val graph = Graph<Int>()
    val node1 = Node(1)
    val node2 = Node(2)
    val node3 = Node(3)
    val node4 = Node(4)
    val node5 = Node(5)
    val node6 = Node(6)
    val node7 = Node(7)
    val node8 = Node(8)
    val node9 = Node(9)
    node1.neighbors.add(node2)
    node1.neighbors.add(node3)
    node2.neighbors.add(node4)
    node2.neighbors.add(node5)
    node2.neighbors.add(node1)
    node6.neighbors.add(node7)
    node6.neighbors.add(node8)
    node9.neighbors.add(node8)
    graph.roots.add(node1)
    graph.roots.add(node6)
    print(isDirectPath(node1, node5))
}

fun <T> isDirectPath(origin: Node<T>, dest: Node<T>): Boolean {
    val visited = hashSetOf<Node<T>>()
    val visitingQueue = ConcurrentLinkedQueue<Node<T>>()
    visitingQueue.add(origin)
    while(!visitingQueue.isEmpty()) {
        val current = visitingQueue.poll()
        for(neighbor in current.neighbors) {
            if(neighbor == dest) return true
            if(!visited.contains(neighbor)) visitingQueue.add(neighbor)
        }
        visited.add(current)
    }
    return false
}