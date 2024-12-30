package com.tejaskt.demotweets.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tejaskt.demotweets.ui.component.NoInternetDialog
import com.tejaskt.demotweets.ui.view.viewmodels.DetailViewModel
import com.tejaskt.demotweets.ui.view.viewmodels.MainViewModel

@Composable
fun DetailScreen() {
    val detailViewModel: DetailViewModel = hiltViewModel()
    val tweets =  detailViewModel.tweets.collectAsState()

    val mainViewModel: MainViewModel = hiltViewModel()
    val isConnected = mainViewModel.isConnected.collectAsState()

    if (!isConnected.value) {
        NoInternetDialog()
    }

    if (isConnected.value){
        detailViewModel.retryFetchingTweets()
    }

    if(tweets.value.isEmpty()){
        Box(
            modifier = Modifier.fillMaxSize(1f),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Loading...", style = MaterialTheme.typography.displayLarge)
        }
    }
    else{
        LazyColumn(content = {
            items(tweets.value){
                TweetListItem(tweet = it.text.toString())
            }
        })
    }


}

@Composable
fun TweetListItem(tweet: String) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        border = BorderStroke(1.dp, Color(0xFFCCCCCC)),
        content = {
            Text(
                text = tweet,
                modifier = Modifier.padding(8.dp), style = MaterialTheme.typography.bodyMedium
            )
        }
    )
}