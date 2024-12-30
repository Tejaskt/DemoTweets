package com.tejaskt.demotweets.ui.view.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tejaskt.demotweets.models.TweetListItem
import com.tejaskt.demotweets.repository.TweetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: TweetRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _retryState: MutableStateFlow<Boolean> = MutableStateFlow(false) // State to track retries
    val tweets: StateFlow<List<TweetListItem>> = repository.tweets

    init {
        fetchTweets() // Initial fetch
    }

    // Function to fetch tweets
    private fun fetchTweets() {
        viewModelScope.launch {
            val category = savedStateHandle.get<String>("category") ?: "motivation"
            try {
                repository.getTweets(category)
            } catch (e: Exception) {
                // Handle exceptions (e.g., log errors or show messages)
            }
        }
    }

    // Function to trigger a retry
    fun retryFetchingTweets() {
        _retryState.value = true
        fetchTweets()
        _retryState.value = false
    }
}
