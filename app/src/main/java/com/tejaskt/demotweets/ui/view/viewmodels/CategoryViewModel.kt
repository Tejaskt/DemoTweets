package com.tejaskt.demotweets.ui.view.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tejaskt.demotweets.repository.TweetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val repository: TweetRepository) : ViewModel() {

    private val _retryState: MutableStateFlow<Boolean> = MutableStateFlow(false) // State to track retries
    val categories: StateFlow<List<String>> = repository.categories

    init {
        fetchCategories()
    }

    // Function to fetch categories
    private fun fetchCategories() {
        viewModelScope.launch {
            try {
                repository.getCategories()
            } catch (e: Exception) {
                Log.d("CategoryViewModel", "Error fetching categories: ${e.message}")
                // Handle exceptions (e.g., log errors or show messages)
            }
        }
    }

    // Function to trigger a retry
    fun retryFetchingCategories() {
        _retryState.value = true
        fetchCategories()
        _retryState.value = false
    }
}



