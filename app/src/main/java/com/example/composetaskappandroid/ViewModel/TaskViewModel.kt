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
            taskDao.deleteTask(task.id) // Assumindo que `id` é do tipo Long
            loadTasks()
        }
    }


    fun toggleTaskCompletion(task: Task) {
        viewModelScope.launch {
            // Altera o status da tarefa
            val updatedTask = task.copy(isCompleted = !task.isCompleted) // Inverte o status
            taskDao.updateTask(updatedTask) // Atualiza a tarefa no banco
            loadTasks() // Recarrega a lista de tarefas
        }
    }



}
