import javax.swing.*

class UI : JFrame("To-do-List") {

    init {
        initUI()
        val mainPanel = JPanel()
        mainPanel.layout = BoxLayout(mainPanel, BoxLayout.Y_AXIS)
        contentPane.add(mainPanel)

        val addToDoPanel = createAddToDo()
        addToDoPanel.layout = BoxLayout(addToDoPanel, BoxLayout.Y_AXIS)
        val listPanel = createList()
        listPanel.layout = BoxLayout(listPanel, BoxLayout.Y_AXIS)



        repaint()
    }

    private fun initUI() {
        setSize(400, 300)
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        setLocationRelativeTo(null)
    }

    private fun createAddToDo(): JPanel {
        return JPanel()
    }

    private fun createList(): JPanel {
        val body = JPanel()
        for (task in Main.tasks)
            return body
    }
}


