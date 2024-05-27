package io.github.josephgm.boxofficeapp

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val apiKey = BuildConfig.API_KEY
    
    private val _boxOfficeState = mutableStateOf(BoxOfficeState())
    val boxOfficeState: State<BoxOfficeState> = _boxOfficeState



    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        Log.d("jk-log", "fetchMoview in MainViewModel Called ")
        Log.d("jk-log","apk-key: $apiKey")
        viewModelScope.launch {
            Log.d("jk-log", "viewModelScope.launch before try")
            try {
               val response = apiService.getMovies(apiKey, "20240524")
                _boxOfficeState.value = _boxOfficeState.value.copy(
                    loading = false,
                    error = null,
                    list = response.boxOfficeResult.dailyBoxOfficeList
                )
                Log.d("jk-log", "response : $response")
            }catch (e: Exception){
                Log.d("jk-log", "Catch Error")
                _boxOfficeState.value = _boxOfficeState.value.copy(
                    loading = false,
                    error = "Error fetching Catefories ${e.message}"
                )
            }
        }
    }

}



data class BoxOfficeState(
    val loading: Boolean = true,
    val list: List<MovieData> = emptyList(),
    val error: String? = null
)