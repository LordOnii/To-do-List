import javax.swing.*
import kotlinx.serialization.json.Json
import java.awt.event.ItemEvent
import java.io.InputStream

class UI : JFrame("To-do-List") {
    private val json = JSONParser.readFile("tasks.json")
    private val tasks: TaskList = Json.decodeFromString(json)
    private val mainPanel = JPanel()
    private val showCompleted = JCheckBox()

    init {
        initUI()
        mainPanel.layout = BoxLayout(mainPanel, BoxLayout.Y_AXIS)
        contentPane.add(mainPanel)

        add(mainPanel)
        updateDisplay()
    }

    private fun initUI() {
        setSize(400, 300)
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        setLocationRelativeTo(null)

        showCompleted.addItemListener {
            updateDisplay()
        }
    }

    private fun createAddToDo(): JPanel {
        val a = JPanel()
        a.add(JLabel("TODO"))
        return a
    }

    private fun createList(): JPanel {
        val body = JPanel()

        for (task: Task in tasks.tasks) {
            // Show completed tasks only if showCompleted is selected or if they aren't completed
            if (task.state != 0 || showCompleted.isSelected) {
                val taskContainer = JPanel()
                taskContainer.layout = BoxLayout(taskContainer, BoxLayout.X_AXIS)
                // Allow to complete a task
                val checkBox = JCheckBox()
                checkBox.addItemListener { e -> // Check if the checkbox is selected
                    if (e.stateChange == ItemEvent.SELECTED) {
                        JSONParser.removeTask(task, tasks)
                        tasks.tasks.sortedBy { it.id }
                        updateDisplay()

                        for (task: Task in tasks.tasks) {
                            println("> ${task.description} at ${task.state}")
                        }
                    }
                }
                taskContainer.add(checkBox)
                taskContainer.add(Box.createVerticalGlue())
                taskContainer.revalidate()
                taskContainer.add(JLabel(task.description))
                when (task.state) {
                    0 -> taskContainer.add(JLabel("Completed"))
                    1 -> taskContainer.add(JLabel("Pending"))
                }
                body.add(taskContainer)
            }


        }
        return body
    }

    private fun updateDisplay() {
        val addToDoPanel = createAddToDo()
        addToDoPanel.layout = BoxLayout(addToDoPanel, BoxLayout.Y_AXIS)
        val listPanel = createList()
        listPanel.layout = BoxLayout(listPanel, BoxLayout.Y_AXIS)

        mainPanel.removeAll()
        mainPanel.add(addToDoPanel)
        mainPanel.add(listPanel)
        mainPanel.add(showCompleted)
        mainPanel.revalidate()
        repaint()
    }

}


