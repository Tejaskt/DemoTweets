package com.tejaskt.demotweets.api

import com.tejaskt.demotweets.models.TweetListItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface TweetsyApi {

    @GET("/v3/b/67585792ad19ca34f8d8c968?meta=false") // get request with end point
    // dynamic header with its key.
    suspend fun getTweets(@Header("X-JSON-Path") category: String) : Response<List<TweetListItem>>

    @GET("/v3/b/67585792ad19ca34f8d8c968?meta=false")
    @Headers("X-JSON-Path: tweets..category") // static header
    suspend fun getCategories(): Response<List<String>>

}