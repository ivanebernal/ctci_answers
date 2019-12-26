import java.util.concurrent.ConcurrentLinkedQueue
/*
Compile: 
kotlinc TaskOrder.kt -include-runtime -d taskorder.jar
Then try this example:
java -jar taskorder.jar "a,b,c,d,e,f,g" "(f,c),(f,b),(f,a),(c,a),(b,a),(a,e),(b,e),(d,g)"

 */ 
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
    val project = getProject(tasks, dependencies)
    val result = arrayListOf<Task>()
    while(!project.entryPoints.isEmpty()) {
        val next = arrayListOf<Task>()
        project.entryPoints.forEach { task ->
            result.add(task)
            task.neighbors.forEach { neighbor ->
                neighbor.dependencyCount -= 1
                if(neighbor.dependencyCount == 0) next.add(neighbor)
            }
            project.tasks.remove(task)
        }
        project.entryPoints = next
    }
    if(!project.tasks.isEmpty()) throw Exception("Project cannot be built")
    return result
}

fun getProject(tasks: List<Task>, dependencies: List<Pair<Task, Task>>): Project {
    val project = Project()
    project.tasks.addAll(tasks)
    dependencies.forEach{ dep ->
        val parent = project.getTask(dep.first)
        val child = project.getTask(dep.second)
        parent.neighbors.add(child)
        child.dependencyCount += 1
    }
    project.entryPoints = tasks.filter { it.dependencyCount == 0 }
    return project
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

data class Task(val name: String) {
    val neighbors: ArrayList<Task> = arrayListOf()
    var dependencyCount = 0
}

class Project {
    val tasks = arrayListOf<Task>()
    var entryPoints = listOf<Task>()

    fun getTask(task: Task): Task {
        return tasks.first{ task.name == it.name }
    }
}

