package com.developers.todoappwithcompose.ui.screens


import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.developers.todoappwithcompose.data.entites.DynmicTestItem
import com.developers.todoappwithcompose.data.entites.FormattingType.BUTTON
import com.developers.todoappwithcompose.data.entites.FormattingType.EMAIL
import com.developers.todoappwithcompose.data.entites.FormattingType.IMAGE
import com.developers.todoappwithcompose.data.entites.FormattingType.PASSWORD
import com.developers.todoappwithcompose.data.entites.FormattingType.PHONE
import com.developers.todoappwithcompose.data.entites.FormattingType.USERNAME
import com.developers.todoappwithcompose.helpers.EventObserver
import com.developers.todoappwithcompose.helpers.utils.GButton
import com.developers.todoappwithcompose.helpers.utils.InputText
import com.developers.todoappwithcompose.helpers.utils.ProfileImage
import com.developers.todoappwithcompose.ui.dialogs.LoadingDialog
import com.developers.todoappwithcompose.ui.theme.colorNote2
import com.developers.todoappwithcompose.ui.theme.colorPrimary
import com.developers.todoappwithcompose.ui.viewmodels.EditViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.launch


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun EditProfileScreen(
    navController: NavController,
    editViewModel: EditViewModel = hiltViewModel(),
) {
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState() // this contains the `SnackbarHostState`
    val isRefreshing = editViewModel.isRefreshing

    //editViewModel.getMyControls()
    val countriesList = mutableListOf<String>("+965", "+20", "+1", "+40", "+33")
    Scaffold(
        modifier = Modifier
            .padding(bottom = 50.dp)
            .fillMaxSize(),
        scaffoldState = scaffoldState // attaching `scaffoldState` to the `Scaffold`
    ) {

        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing),
            onRefresh = { editViewModel.getMyControls() },
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 12.dp, vertical = 8.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                var name by remember { mutableStateOf("") }
                var email by remember { mutableStateOf("") }
                var phone by remember { mutableStateOf("") }
                var password by remember { mutableStateOf("") }

                var list: List<DynmicTestItem> by remember { mutableStateOf(listOf()) }
                var showDialog by remember { mutableStateOf(false) }
                val context = LocalContext.current


                coroutineScope.launch {
                    editViewModel.controlsStatus.collect(EventObserver(
                        onLoading = {
                            showDialog = true
                            //   LoadingDialog(showDialog)
                        },
                        onError = {
                            showDialog = false

                            coroutineScope.launch {
                                scaffoldState.snackbarHostState.showSnackbar(it)
                            }
                        },
                        onSuccess = {
                            Log.i("GAMALRAGAB", "EditProfileScreen: onSuccess")
                            showDialog = false
                            list = it

                        }
                    ))
                }
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.9f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item {
                        LoadingDialog(showDialog = showDialog)

                    }
                    item {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Bottom,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            HeaderEditProfile()
                        }

                        Spacer(modifier = Modifier.height(15.dp))
                    }
                    itemsIndexed(list) { index, current ->


                        Log.i("GAMALRAGAB", "EditProfileScreen: ${current.toString()}")

                        SetupView(current) { type, value ->


                        }



                        Spacer(modifier = Modifier.height(5.dp))

                    }


                }


            }
        }
    }

}

@Composable
fun SetupView(current: DynmicTestItem, onItemSelected: (String, String) -> Unit) {

    if (current.type == USERNAME) {

        InputText(key = USERNAME, label = current.placeHolder,
            value = current.value, radius = current.radius, keyboardType = KeyboardType.Text,
            itemSelected = { key, value ->
                onItemSelected(key, value)
            })
    } else if (current.type == EMAIL) {
        InputText(key = EMAIL, label = current.placeHolder,
            value = current.value, radius = current.radius, keyboardType = KeyboardType.Email,
            itemSelected = { key, value ->
                onItemSelected(key, value)
            })
    } else if (current.type == PHONE) {
        InputText(key = PHONE, label = current.placeHolder,
            value = current.value, radius = current.radius, keyboardType = KeyboardType.Phone,
            itemSelected = { key, value ->
                onItemSelected(key, value)
            })
    } else if (current.type == PASSWORD) {
        InputText(key = "PASSWORD_FORM", label = current.placeHolder,
            value = current.value, radius = current.radius, keyboardType = KeyboardType.Email,
            itemSelected = { key, value ->
                onItemSelected(key, value)
            })
    } else if (current.type == BUTTON) {
        GButton(text = current.placeHolder,
            backgroundColor = colorNote2, roundCornerValue = current.radius,
            onButtonClicked = {

            })

    } else if (current.type == IMAGE) {
        ProfileImage(current.value, current.radius)
    }

}

@Composable
fun HeaderEditProfile() {
    Spacer(modifier = Modifier.height(5.dp))
    Text(
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.SansSerif,
        text = "Edit Profile",
        fontSize = 20.sp,
        textAlign = TextAlign.Center,
        color = colorNote2
    )
}


@Composable
fun ItemTextFiled(
    placeHolder: String,
    value: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    onClicked: () -> Unit,
    radius: Int,
    type: Int,
    onValueChange: (value: String) -> Unit,
) {

    Text(
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.SemiBold,
        fontFamily = FontFamily.Serif,
        text = placeHolder,
        fontSize = 16.sp,
        color = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp)

    )
    OutlinedTextField(
        value = value,
        maxLines = 1,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                onClicked()
            },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = colorNote2,
            unfocusedBorderColor = Color.White
        ),
        shape = RoundedCornerShape(radius.dp),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = ImeAction.Go
        ),
        label = { Text(text = placeHolder, fontSize = 12.sp, color = colorNote2) },
        onValueChange = {
            onValueChange(it)
        }
    )
}


@Preview
@Composable
fun PreviewEditProfileScreen() {
    EditProfileScreen(rememberNavController())
}