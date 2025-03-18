package viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deepseektraining.data.Photo
import com.example.deepseektraining.repository.PhotoRepository
import kotlinx.coroutines.launch

class PhotoViewModel : ViewModel() {
    private val repository = PhotoRepository()

    var photos by mutableStateOf<List<Photo>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    fun loadPhotos() {
        viewModelScope.launch {
            isLoading = true
            photos = repository.getPhotos()
            isLoading = false
        }
    }
}