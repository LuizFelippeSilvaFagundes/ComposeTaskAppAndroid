import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composetaskappandroid.Retrofit.RetrofitInstance
import com.example.composetaskappandroid.data.Task
import com.example.composetaskappandroid.data.TaskDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TaskViewModel(private val taskDao: TaskDao, context: Context) : ViewModel() {
    val tasks = mutableStateListOf<Task>()

    // Variáveis para armazenar informações do conselho
    var advice = mutableStateOf("") // Usando mutableStateOf para reatividade

    init {
        loadTasks()
        fetchAdvice() // Chama a função para buscar conselho ao iniciar
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
                    toggleTaskCompletion(task) // Marque como concluída
                    println("A tarefa '${task.title}' está vencida!") // Para fins de depuração
                }
            }
        }
    }

    // Função para buscar conselho
    fun fetchAdvice() {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    RetrofitInstance.api.getAdvice() // Chama a API de conselhos
                }
                advice.value = response.slip.advice // Armazena o conselho obtido

            } catch (e: Exception) {
                advice.value = "Erro ao obter conselho" // Lida com qualquer erro que possa ocorrer
                println("Erro ao buscar conselho: ${e.message}") // Para fins de depuração
            }
        }
    }
}
