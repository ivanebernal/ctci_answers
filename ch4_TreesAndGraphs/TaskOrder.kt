import java.util.concurrent.ConcurrentLinkedQueue

fun main(args: Array<String>) {
    if(args.size < 2) {
        println("Please provide 2 arguments: \nfirst one must be a comma separated list of task names\nsecond one must be a coma separated list of dependency pairs, each dependency pair must be inside parentheses and separated by a comma")
        return
    }
    val tasks = parseTasks(args[0])
    val dependencies = parseDependencies(args[1])
    val order = getProjectOrder(tasks, dependencies)
    order.forEach{ print("${it.name}, ") }
    println()
}

fun getProjectOrder(tasks: List<Task>, dependencies: List<Pair<Task, Task>>): List<Task> {
    val taskMap = hashMapOf<Task, GraphNode>()
    tasks.forEach { taskMap[it] = GraphNode(it) }
    dependencies.forEach{ dep ->
        val task = dep.second
        val parentTask = dep.first
        taskMap[task]?.isDependent = true
        taskMap[task]?.let{taskMap[parentTask]?.neighbors?.add(it)}
    }
    val result = arrayListOf<Task>()
    val entryNodes = taskMap.values.filter { !it.isDependent }
    if(entryNodes.isEmpty()) throw Error("All tasks are dependent of each other")
    entryNodes.forEach { taskNode ->
        visitBFS(taskNode, result)
    }
    return result
}

fun visitBFS(n: GraphNode, order: ArrayList<Task>) {
    val visitSet = hashSetOf<GraphNode>()
    val visitQueue = ConcurrentLinkedQueue<GraphNode>()
    visitQueue.add(n)
    visitSet.add(n)
    while(!visitQueue.isEmpty()) {
        val current = visitQueue.poll()
        current.neighbors.forEach { 
            if(!visitSet.contains(it)){
                visitQueue.add(it)
                visitSet.add(it)
            } 
        }
        order.add(current.value)
    }
}

fun parseTasks(listString: String): List<Task> {
    return listString.split(',').map { Task(it) }
}

fun parseDependencies(depString: String): List<Pair<Task, Task>> {
    val pairs = depString.split("),(")
    return pairs.map { 
        var dep = it
        if(dep.length > 3) {
            if(dep[0] == '(') {
                dep = dep.drop(1)
            } else if(dep[3] == ')') {
                dep = dep.dropLast(1)
            }
        }
        val elements = dep.split(',')
        Pair(Task(elements[0]), Task(elements[1]))
    }
}

class GraphNode(val value: Task) {
    val neighbors : ArrayList<GraphNode> = arrayListOf()
    var isDependent = false
}

data class Task(val name: String) 

