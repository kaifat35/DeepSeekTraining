package viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deepseektraining.data.Photo
import com.example.deepseektraining.repository.PhotoRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PhotoViewModel : ViewModel() {
    private val repository = PhotoRepository()

    private val _photos = MutableStateFlow<List<Photo>>(emptyList())
    val photos: StateFlow<List<Photo>> get() = _photos

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        // Логируем ошибку
        println("Ошибка при загрузке фотографий: ${throwable.message}")
        _isLoading.update { false } // Останавливаем загрузку
    }

    fun loadPhotos() {
        viewModelScope.launch(exceptionHandler) {
            _isLoading.update { true }
            try {
                val loadedPhotos = repository.getPhotos()
                _photos.update { loadedPhotos }
            } catch (e: Exception) {
                println("Ошибка: ${e.message}")
            } finally {
                _isLoading.update { false }
            }
        }
    }
}