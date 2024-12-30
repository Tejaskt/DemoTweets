package com.tejaskt.demotweets.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import com.tejaskt.demotweets.api.TweetsyApi
import com.tejaskt.demotweets.models.TweetListItem
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TweetRepository  @Inject constructor(
    @ApplicationContext private val context: Context,
    private val tweetsyApi: TweetsyApi,
){
    // observable type variable declaration
    private val _categories = MutableStateFlow<List<String>>(emptyList())
    // _categories is private variable so we create one more variable to access it
    // it will define like this
    val categories: StateFlow<List<String>>
        get() = _categories

    private val _tweets = MutableStateFlow<List<TweetListItem>>(emptyList())
    val tweets: StateFlow<List<TweetListItem>>
        get() = _tweets

    suspend fun getCategories() {
        withContext(Dispatchers.IO) {
            try {
                val response = tweetsyApi.getCategories()
                if (response.isSuccessful && response.body() != null) {
                    _categories.emit(response.body()!!)
                }else{
                    // Handle API error (e.g., log or show a message)
                    Log.e("TweetRepository", "Error: ${response.errorBody()?.string()}")
                    Toast.makeText(context, "Error: ${response.errorBody()?.string()}", Toast.LENGTH_SHORT).show()
                }
            }catch (exception: Exception){
                // Handle exceptions (e.g., network issues, JSON parsing errors)
                Log.e("TweetRepository", "Exception: ${exception.message}")
                Toast.makeText(context, "Exception: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    suspend fun getTweets(category: String) {
        withContext(Dispatchers.IO) {
            try {
                val response = tweetsyApi.getTweets("tweets[?(@.category==\"$category\")]")
                if (response.isSuccessful && response.body() != null) {
                    _tweets.emit(response.body()!!)
                } else {
                    Log.e("TweetRepository", "Error: ${response.errorBody()?.string()}")
                    Toast.makeText(context, "Error: ${response.errorBody()?.string()}", Toast.LENGTH_SHORT).show()
                }
            } catch (exception: Exception) {
                Log.e("TweetRepository", "Exception: ${exception.message}")
                Toast.makeText(context, "Exception: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }


}