import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composetaskappandroid.data.Task
import com.example.composetaskappandroid.data.TaskDao
import kotlinx.coroutines.launch

class TaskViewModel(private val taskDao: TaskDao) : ViewModel() {

    val tasks = mutableStateListOf<Task>()

    init {
        loadTasks()
    }

    private fun loadTasks() {
        viewModelScope.launch {
            tasks.clear()
            tasks.addAll(taskDao.getAllTasks())
            checkForOverdueTasks() // Verifique se as tarefas estão vencidas após carregá-las
        }
    }

    fun addTask(task: Task) {
        viewModelScope.launch {
            taskDao.insertTask(task)
            loadTasks()
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            taskDao.updateTask(task)
            loadTasks()
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            taskDao.deleteTask(task.id)
            loadTasks()
        }
    }

    fun toggleTaskCompletion(task: Task) {
        viewModelScope.launch {
            val updatedTask = task.copy(isCompleted = !task.isCompleted)
            taskDao.updateTask(updatedTask)
            loadTasks()
        }
    }

    private fun checkForOverdueTasks() {
        viewModelScope.launch {
            val currentTime = System.currentTimeMillis() // Tempo atual em milissegundos
            tasks.forEach { task ->
                if (task.dueDate != null && !task.isCompleted && task.dueDate < currentTime) {
                    // Se a tarefa está vencida, marque como concluída ou faça o que for necessário
                    toggleTaskCompletion(task) // Você pode optar por marcar como concluída
                    println("A tarefa '${task.title}' está vencida!") // Para fins de depuração, imprima uma mensagem
                }
            }
        }
    }
}
