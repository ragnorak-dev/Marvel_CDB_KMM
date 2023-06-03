package com.ragnorak.marvelcdb.android.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ragnorak.marvelcdb.domain.models.MarvelCardModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    val marvelListViewModel: MarvelCardListViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val marvelCardListState =
                        marvelListViewModel.marvelCardList.collectAsState()

                    when(marvelCardListState.value) {
                        ViewState.Idle -> {
                            marvelListViewModel.getMarvelCardList()
                        }
                        ViewState.Loading -> {
                            Box(modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center){
                                Text(text = "CARGANDO...")
                            }
                        }
                        is ViewState.Success -> {
                            val scrollBehavior =  rememberScrollState()
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .verticalScroll(scrollBehavior)) {
                                (marvelCardListState.value as ViewState.Success<List<MarvelCardModel>>)
                                    .data.forEach {
                                        Text(text = it.name)
                                }
                            }
                        }
                        is ViewState.Error -> {
                            Box(modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center){
                                Text(text = (marvelCardListState.value as ViewState.Error).errorMessage)
                            }
                        }
                    }
                   Column {

                   }
                }
            }
        }
    }
}

