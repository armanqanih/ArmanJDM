package com.example.gradle.presentation.composable

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.size.Scale
import coil.size.Size
import com.example.gradle.R

@Composable
fun StandardImage(
    modifier: Modifier = Modifier,
    imageUrl: String = "",
    contentDescription: String = "",
) {



    Image(
        painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(R.drawable.assasin)
                .memoryCachePolicy(CachePolicy.ENABLED) // Enable caching
                .diskCachePolicy(CachePolicy.ENABLED) // Enable disk caching
                .crossfade(true)
                .error(android.R.drawable.ic_menu_report_image)
                .placeholder(android.R.drawable.ic_menu_gallery)
                .size(Size.ORIGINAL)
                .scale(Scale.FILL)
                .listener(
                    onStart = { Log.d("Coil", "Loading started for $imageUrl") },
                    onSuccess = { _, _ -> Log.d("Coil", "Successfully loaded $imageUrl") },
                    onError = { _, throwable -> Log.e("Coil", "Error loading $imageUrl: ${throwable.throwable}") }
                )
                .build()
        ),
        
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = ContentScale.Crop
    )
}
