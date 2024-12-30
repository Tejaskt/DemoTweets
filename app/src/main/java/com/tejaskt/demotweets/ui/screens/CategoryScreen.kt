package com.tejaskt.demotweets.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tejaskt.demotweets.ui.view.viewmodels.CategoryViewModel
import com.tejaskt.demotweets.R
import com.tejaskt.demotweets.ui.component.NoInternetDialog
import com.tejaskt.demotweets.ui.view.viewmodels.MainViewModel


@Composable
fun CategoryScreen(onClick: (category: String) -> Unit) {

    val categoryViewModel: CategoryViewModel = hiltViewModel()
    var categories: State<List<String>> = categoryViewModel.categories.collectAsState()

    val mainViewModel: MainViewModel = hiltViewModel()
    val isConnected = mainViewModel.isConnected.collectAsState()

    if (!isConnected.value) {
        NoInternetDialog()

    }
    if (isConnected.value){
        categoryViewModel.retryFetchingCategories()
    }

    if (categories.value.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(1f),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Loading...", style = MaterialTheme.typography.displayLarge)
        }
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.SpaceAround,
        ) {
            items(categories.value.distinct()) {
                CategoryItem(category = it, onClick)
            }
        }
    }


}

@Composable
fun CategoryItem(category: String, onClick: (category: String) -> Unit) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .clickable {
                onClick(category)
            }
            .size(120.dp)
            .clip(RoundedCornerShape(10.dp))
            .paint(
                painter = painterResource(id = R.drawable.polygon_scatter_haikei),
                contentScale = ContentScale.Crop
            )
            .border(1.dp, Color(0xFFEEEEEE)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = category,
            fontSize = 22.sp,
            color = Color.White,
            modifier = Modifier.padding(0.dp, 20.dp),
            style = MaterialTheme.typography.bodyMedium,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif
        )
    }
}