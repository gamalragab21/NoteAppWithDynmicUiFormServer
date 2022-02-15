package com.developers.todoappwithcompose.ui.screens

import android.annotation.SuppressLint
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.VectorConverter
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.developers.todoappwithcompose.R
import com.developers.todoappwithcompose.helpers.EventObserver
import com.developers.todoappwithcompose.navigation.MyNavigationScreens
import com.developers.todoappwithcompose.ui.viewmodels.SplashViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable

fun SplashScreen(
    navController: NavController,
    splashViewModel: SplashViewModel = hiltViewModel()
) {
    val snakBarState = remember { SnackbarHostState() }
    val animatableSize = remember { Animatable(Size.Zero, Size.VectorConverter) }
    val (containerSize, setContainerSize) = remember { mutableStateOf<Size?>(null) }
    val (imageSize, setImageSize) = remember { mutableStateOf<Size?>(null) }
    val density = LocalDensity.current
    val coroutineScope = rememberCoroutineScope()
    splashViewModel.startSplashScreen()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Loader()

            Text(
                text = "ToDo App",
                modifier = Modifier
                    .fillMaxWidth()
                    .align(CenterHorizontally)
                    .then(if (animatableSize.value != Size.Zero) {
                        animatableSize.value.run {
                            Modifier.size(
                                width = with(density) { width.toDp() },
                                height = with(density) { height.toDp() },
                            )
                        }
                    } else {
                        Modifier
                    })
                    .onSizeChanged { intSize ->
                        if (imageSize != null) return@onSizeChanged
                        val size = intSize.toSize()
                        setImageSize(size)
                        coroutineScope.launch {
                            animatableSize.snapTo(size)
                        }
                    },
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 30.sp,
               style = MaterialTheme.typography.h1
            )

        }


    }

    coroutineScope.launch {
        splashViewModel.splashStatus.collect(
            EventObserver(
                onError = {
                    showSnackbar(coroutineScope, it, snakBarState)
                },
                onLoading = {
                    coroutineScope.launch {
                        snakBarState.showSnackbar("Welcome in my app ")
                    }
                },
                onSuccess = {

                    navController.navigate(route = MyNavigationScreens.EditProfileScreen.route) {
                        popUpTo(MyNavigationScreens.SplashScreen.route) {
                            inclusive = true
                        }
                    }
                },
            ),
        )
    }

}

@Composable
fun Loader(
    modifier: Modifier = Modifier
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.notesplash))
    val progress by animateLottieCompositionAsState(composition)
    LottieAnimation(
        composition,
        progress,
        modifier = modifier
            .fillMaxWidth()
            .height(400.dp)
    )
}

@Composable
@Preview
fun PreviewSplashScreen() {

    SplashScreen(navController = rememberNavController())
}


fun showSnackbar(coroutineScope: CoroutineScope, message: String, snakBarState: SnackbarHostState) {
    coroutineScope.launch {
        snakBarState.showSnackbar(message, duration = SnackbarDuration.Long)
    }
}
