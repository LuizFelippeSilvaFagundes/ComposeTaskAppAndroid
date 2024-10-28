import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun registerUser(
        email: String,
        password: String,
        onResult: (Boolean, String?) -> Unit // Modificação para retornar uma mensagem de erro opcional
    ) {
        viewModelScope.launch {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        onResult(true, null) // Sucesso no cadastro
                    } else {
                        // Verifica se o erro é de e-mail já registrado
                        if (task.exception is FirebaseAuthUserCollisionException) {
                            onResult(false, "Este e-mail já está registrado.")
                        } else {
                            onResult(false, "Erro ao registrar. Tente novamente.")
                        }
                    }
                }
        }
    }

    fun loginUser(
        email: String,
        password: String,
        onResult: (Boolean) -> Unit
    ) {
        viewModelScope.launch {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    onResult(task.isSuccessful)
                }
        }
    }

    fun isUserLoggedIn(): Boolean {
        return auth.currentUser != null
    }

    fun logout() {
        auth.signOut()
    }
}