import kotlinx.serialization.Serializable

@Serializable
data class Task(
    val id: Int,
    val description: String,
    // 0 -> completed
    // 1 -> pending
    val state: Int,
)

@Serializable
data class TaskList(val tasks: MutableList<Task>)