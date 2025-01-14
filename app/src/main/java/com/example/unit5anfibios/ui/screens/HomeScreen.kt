package com.example.unit5anfibios.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.unit5anfibios.R
import com.example.unit5anfibios.model.AnfibiosFotos

@Composable
fun HomeScreen(
    anfibiosUiState: AnfibiosUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
){
    when (anfibiosUiState) {
        is AnfibiosUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is AnfibiosUiState.Success -> PhotosGridScreen(anfibiosUiState.fotos,contentPadding = contentPadding, modifier = modifier)
        is AnfibiosUiState.Error -> ErrorScreen(retryAction = retryAction, modifier = modifier.fillMaxSize())
    }

    /* Código inútil de test
    Box (modifier = modifier
        .padding(contentPadding)
    ){
        Text("Hola", modifier = modifier)
    }*/
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading_image)
    )
}

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = stringResource(R.string.error_de_carga), modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(text = stringResource(R.string.retry))
        }
    }
}

@Composable
fun PhotosGridScreen(
    photos: List<AnfibiosFotos>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        modifier = modifier.padding(horizontal = 4.dp),
        contentPadding = contentPadding,
    ) {

        items(items = photos, key = { photo -> photo.name }) { photo ->
            AnfibiosCard(
                photo,
                modifier = modifier
                    .padding(12.dp)
                    .fillMaxWidth()

            )
        }
    }
}

@Composable
fun AnfibiosCard
            (photo :AnfibiosFotos,
             modifier : Modifier
){
    Card(
        colors = CardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer, contentColor = MaterialTheme.colorScheme.primary, disabledContentColor = Color.Gray, disabledContainerColor = Color.Gray),
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {

        Text(
            //color = MaterialTheme.colorScheme.primary,
            fontSize = 20.sp,
            text = "${photo.name} (${photo.type})",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, bottom = 12.dp)
        )


        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(photo.imgSrc)
                .crossfade(true)
                .build(),
            contentDescription = stringResource(R.string.foto_Anfibio),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth(),
            error = painterResource(R.drawable.ic_broken_image),
            placeholder = painterResource(R.drawable.loading_img),


            )

        Text(
            //color = MaterialTheme.colorScheme.primary,
            fontSize = 16.sp,
            text = photo.description,
            textAlign = TextAlign.Justify,


            modifier = Modifier
                .fillMaxWidth()
                .padding(top=12.dp, bottom = 12.dp,start = 12.dp)
        )

    }
}