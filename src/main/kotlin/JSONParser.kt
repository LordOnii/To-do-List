import java.io.InputStream
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

public class JSONParser {
    companion object {
        fun readFile(filePath: String): String {
            val inputStream: InputStream = UI::class.java.classLoader.getResourceAsStream(filePath)
            return inputStream.bufferedReader().use { it.readText() }
        }

        fun removeTask(task: Task, taskList: TaskList) {
            // Find the index of the task to remove
            val index = taskList.tasks.indexOfFirst { it.id == task.id }

            // If the task is found, remove it
            if (index != -1) {
                taskList.tasks.removeAt(index)

                val updatedTask = task.copy(state = 0)
                taskList.tasks.add(updatedTask)

                saveTasksToFile(taskList)
            } else {
                println("Task not found in the list.")
            }
        }

        private fun saveTasksToFile(taskList: TaskList) {
            val jsonString = Json.encodeToString(taskList)
            val file = File("src/main/resources/tasks.json")
            file.writeText(jsonString)
            sortFile(file)
            println("File updated")
        }

        private fun sortFile(file: File) {
            // Step 1: Read the JSON file and parse it into a list of Task objects
            val jsonString = file.readText()
            val tasks: TaskList = Json.decodeFromString(jsonString)

            // Step 2: Sort the list of Task objects based on the "id" field
            val sortedTasks = tasks.tasks.sortedBy { it.id }

            // Step 3: Create a new TaskList with the sorted tasks
            val sortedTaskList = TaskList(sortedTasks.toMutableList())

            // Step 4: Write the sorted list back to a new JSON file
            val outputJsonString = Json.encodeToString(sortedTaskList)
            file.writeText(outputJsonString)
        }
    }
}