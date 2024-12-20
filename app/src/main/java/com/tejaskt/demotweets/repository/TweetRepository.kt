package com.tejaskt.demotweets.repository

import com.tejaskt.demotweets.api.TweetsyApi
import com.tejaskt.demotweets.models.TweetListItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class TweetRepository  @Inject constructor(private val tweetsyApi: TweetsyApi){

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
        val response = tweetsyApi.getCategories()
        if(response.isSuccessful && response.body() != null){
            _categories.emit(response.body()!!)
        }
    }

    suspend fun getTweets(category: String) {
        val response = tweetsyApi.getTweets("tweets[?(@.category==\"$category\")]")
        if(response.isSuccessful && response.body() != null){
            _tweets.emit(response.body()!!)
        }
    }

}