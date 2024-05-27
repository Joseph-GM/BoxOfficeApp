package io.github.josephgm.boxofficeapp

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun BoxOfficeScrteeen(){
    val boxOfficeViewModel : MainViewModel = viewModel()
    val viewstate by boxOfficeViewModel.boxOfficeState

    Log.d("jk-log", "BoxOfficeScreen called")
    Log.d("jk-log", "viewstate loading: ${viewstate.loading}")

    when {
        viewstate.loading -> {
            Log.d("jk-log", "BoxOfficeLoading Calling")
            BoxOfficeLoading()
        }
        viewstate.error != null -> {
            Log.d("jk-log", "BoxOfficeError Calling")
            BoxOfficeError(viewstate.error)
        }
        else -> {
            Log.d("jk-log", "BoxOfficeList Calling")
            BoxOfficeList(viewstate.list)
        }
    }
}

@Composable
fun BoxOfficeLoading() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun BoxOfficeError(message: String?) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Error: $message")
    }
}

@Composable
fun BoxOfficeList(movies: List<MovieData>) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(movies) { movie ->
            MovieItem(movie)
        }
    }
}
@Composable
fun MovieItem(boxOffice: MovieData) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = "Rank: ${boxOffice.rank}")
        Text(text = "Movie: ${boxOffice.movieNm}")
        Text(text = "Release Date: ${boxOffice.openDt}")
        Text(text = "Audience: ${boxOffice.audiCnt}")
    }
}