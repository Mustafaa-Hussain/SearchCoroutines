package com.mustafa.searchcouritines

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mustafa.searchcouritines.ui.theme.SearchCouritinesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            SearchCouritinesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        Modifier
                            .padding(innerPadding),
                        viewModel()
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier, viewModel: AllNamesViewModel) {
    val namesList = viewModel.namesList.collectAsStateWithLifecycle(listOf())
    val searchFieldState = rememberSaveable { mutableStateOf("") }
    Column {
        TextField(
            modifier = modifier
                .fillMaxWidth(),
            value = searchFieldState.value,
            onValueChange = {
                searchFieldState.value = it
                //view model func
                viewModel.filterList(it)
            })
        LazyColumn(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(namesList.value.size) {
                NameRow(
                    modifier = Modifier.padding(8.dp),
                    name = namesList.value[it]
                )
            }
        }
    }
}

@Composable
fun NameRow(modifier: Modifier, name: String) {
    Text(
        modifier = modifier,
        text = name
    )
}