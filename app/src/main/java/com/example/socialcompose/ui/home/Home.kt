package com.example.socialcompose.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.socialcompose.di.Injection
import com.example.socialcompose.model.Social
import com.example.socialcompose.ui.ViewModelFactory
import com.example.socialcompose.ui.common.UiState


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Int) -> Unit,
) {
    val query by viewModel.query.collectAsState()

    val filteredSocials by viewModel.uiState.collectAsState(initial = UiState.Loading)
    LaunchedEffect(query) {
        if (query.isNotBlank()) {
            viewModel.search(query)
        } else {
            viewModel.getAllSocials()
        }
    }

    when (filteredSocials) {
        is UiState.Loading -> {}
        is UiState.Success -> {
            val socials = (filteredSocials as UiState.Success<List<Social>>).data
            HomeContent(
                socials = socials,
                modifier = modifier,
                navigateToDetail = navigateToDetail,
                query = query,
                onQueryChange = { newQuery -> viewModel.search(newQuery) }
            )
        }
        is UiState.Error -> {}
    }
}

@Composable
fun HomeContent(
    socials: List<Social>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Int) -> Unit,
    query: String,
    onQueryChange: (String) -> Unit
) {
    Column(modifier) {
        TextField(
            value = query,
            onValueChange = onQueryChange,
            label = { Text("Search") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )
        LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
            items(socials) { data ->
                SocialItem(
                    image = data.image,
                    name = data.name,
                    modifier = Modifier
                        .clickable { navigateToDetail(data.id) }
                        .padding(vertical = 8.dp)
                )
            }
        }
    }
}

@Composable
fun SocialItem(
    image: Int,
    name: String,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .padding(5.dp),
        shape = RoundedCornerShape(5.dp),
    ) {
        Row(
            modifier = Modifier
                .background(
                    Brush.verticalGradient(
                    listOf(
                        Color.Transparent,
                        Color.LightGray,
                    ),
                ))
        ) {
            Image(
                painter = painterResource(image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(8.dp)
                    .size(70.dp)
                    .clip(CircleShape)
            )
            Text(
                text = name,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .paddingFromBaseline(top = 50.dp)
                    .padding(start = 16.dp)
            )
        }
    }
}