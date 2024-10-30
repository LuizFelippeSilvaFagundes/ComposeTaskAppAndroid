import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.assertIsDisplayed
import com.example.composetaskappandroid.MockTaskViewModel
import com.example.composetaskappandroid.data.Task
import com.example.composetaskappandroid.ui.TaskListScreen
import org.junit.Rule
import org.junit.Test

class TaskListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testAddTask() {
        val mockViewModel = MockTaskViewModel()

        composeTestRule.setContent {
            TaskListScreen(viewModel = mockViewModel)
        }

        // Simule a adição de uma nova tarefa
        val newTask = Task(title = "Nova Tarefa", description = "Descrição da tarefa")
        mockViewModel.addTask(newTask)

        // Verifique se a nova tarefa aparece na tela
        composeTestRule.onNodeWithText("Nova Tarefa").assertIsDisplayed()
    }

    @Test
    fun testToggleTaskCompletion() {
        val mockViewModel = MockTaskViewModel()
        val task = Task(title = "Test Task", description = "Description")
        mockViewModel.addTask(task)

        composeTestRule.setContent {
            TaskListScreen(viewModel = mockViewModel)
        }

        // Clique na tarefa para alternar a conclusão
        composeTestRule.onNodeWithText("Test Task").performClick() // Clique na tarefa

        // Verifique se a tarefa foi marcada como concluída
        assert(mockViewModel.tasks[0].isCompleted) // Verifique se a tarefa está marcada como concluída
    }
}
