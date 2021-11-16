package com.redrock.learndemo.mviPattern.ui.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.redrock.learndemo.mviPattern.network.ApiService
import com.redrock.learndemo.mviPattern.repository.MainRepository
import com.redrock.learndemo.mviPattern.ui.intent.MainIntent
import com.redrock.learndemo.mviPattern.ui.state.MainState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

/**
 * Author by OkAndGreat
 * Date on 2021/11/16 8:38.
 *
 */
class MainViewModel(
    private val repository: MainRepository
) : ViewModel() {

    val userIntent = Channel<MainIntent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<MainState>(MainState.Idle)
    val state: StateFlow<MainState>
        get() = _state

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect {
                when (it) {
                    is MainIntent.FetchUser -> fetchUser()
                }
            }
        }
    }

    private fun fetchUser() {
        viewModelScope.launch {
            _state.value = MainState.Loading
            _state.value = try {
                MainState.Users(repository.getUsers())
            } catch (e: Exception) {
                MainState.Error(e.localizedMessage)
            }
        }
    }
}

//MainViewModel带参，而ViewModelProvider默认生成不带参数的ViewModel，通过这种方法生成带参的
class ViewModelFactory(private val apiService: ApiService) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MainRepository(apiService)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}
