package com.developers.todoappwithcompose.ui.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import coil.size.OriginalSize
import com.developers.todoappwithcompose.R
import com.developers.todoappwithcompose.navigation.MyNavigationScreens
import com.developers.todoappwithcompose.ui.theme.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorPrimaryDark)
            .offset(y = 10.dp)
    ) {

        //FAB custom color
        val onClick = {
            navController.navigate(MyNavigationScreens.EditProfileScreen.route)
            //Toast.makeText(context, "Mai add", Toast.LENGTH_SHORT).show()
        }
        FloatingActionButton(
            onClick = {
                Log.i("GAMALRAGAB", "HomeScreen:onClick ")
                navController.navigate(MyNavigationScreens.EditProfileScreen.route)
            },
            backgroundColor = colorNote2,
            contentColor = Color.White,
            modifier = Modifier
                .padding(horizontal = 15.dp, vertical = 60.dp)
                .align(Alignment.BottomEnd)

        ) {
            Icon(Icons.Filled.Add, "")

        }

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start
        ) {

            HeaderPage()

            Spacer(modifier = Modifier.height(10.dp))

            val notes = listOf<String>("First ", "Secend", "Threads ")

            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp, vertical = (10).dp)
                    .weight(3f),
                cells = GridCells.Fixed(2)
            ) {

                itemsIndexed(notes) { index, item ->
                    ItemListNote(item)
                }
            }


            BottomSectionLayout(navController)


        }
    }
}

@Composable
fun BottomSectionLayout(navController: NavController) {
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.End) {


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(colorQuickActionBackground),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Box(
                modifier = Modifier
                    .padding(15.dp)
                    .clickable {
                        Toast
                            .makeText(context, "Add Note", Toast.LENGTH_SHORT)
                            .show()
                    }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add_circle_outline),
                    contentDescription = "emailIcon",
                    tint = Color.White,
                    modifier = Modifier
                        .width(30.dp)
                        .height(30.dp)
                        .clickable {
                            navController.navigate(MyNavigationScreens.EditProfileScreen.route)

                        }
                )
            }

            Box(
                modifier = Modifier
                    .padding(15.dp)
                    .clickable {
                        Toast
                            .makeText(context, "Add Image", Toast.LENGTH_SHORT)
                            .show()
                    }

            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add_image),
                    contentDescription = "emailIcon",
                    tint = Color.White,
                    modifier = Modifier
                        .width(30.dp)
                        .height(30.dp)

                )
            }

            Box(
                modifier = Modifier
                    .padding(15.dp)
                    .clickable {
                        Toast
                            .makeText(context, "Add Link", Toast.LENGTH_SHORT)
                            .show()
                    }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_web),
                    contentDescription = "emailIcon",
                    tint = Color.White,
                    modifier = Modifier
                        .width(30.dp)
                        .height(30.dp)

                )
            }


        }


    }
}

@Composable
fun HeaderPage() {
    var text by remember { mutableStateOf("") }

    Text(
        modifier = Modifier
            .fillMaxWidth()
            .offset(x = 15.dp),
        text = "My Notes", textAlign = TextAlign.Start,
        color = Color.White,
        style = MaterialTheme.typography.h1,
        fontSize = 25.sp
    )

    Spacer(modifier = Modifier.height(8.dp))

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("Search...", color = Color.White) },
        maxLines = 1,
        singleLine = true,
        textStyle = TextStyle(
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = (10).dp),
        shape = RoundedCornerShape(12.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "emailIcon",
                tint = Color.White
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = colorNote2,
            unfocusedBorderColor = Color.White
        )
    )
}

@Composable
fun ItemListNote(note: String) {
    Card(
        shape = RoundedCornerShape(10.dp),
        backgroundColor = colorDefaultNoteColor,
        elevation = 3.dp,
        modifier = Modifier.padding(3.dp)
    ) {
        Column(
            modifier = Modifier.padding(3.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberImagePainter(
                    data = "https://cdn.arabhardware.net/wp-content/uploads/2020/11/marvel-spider-man-miles-morales-iz-1920x1080-1.jpg",
                    builder = {
                        size(OriginalSize)
                    },
                ),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .background(Color.White)
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                text = note,
                textAlign = TextAlign.Start,
                color = Color.White,
                style = MaterialTheme.typography.h1,
                fontSize = 15.sp
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                text = "Sub Title",
                textAlign = TextAlign.Start,
                color = Color.White,
                fontSize = 14.sp,
                style = MaterialTheme.typography.h1,
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 2.dp),
                text = "www.google.com",
                textAlign = TextAlign.Start,
                color = Color.White,
                style = MaterialTheme.typography.h1,
                fontSize = 13.sp,
                textDecoration = TextDecoration.Underline
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                text = "12 may 2020",
                textAlign = TextAlign.Start,
                color = colorNote2,
                style = MaterialTheme.typography.h2,
                fontSize = 12.sp
            )
            Icon(painter = painterResource(id = R.drawable.ic_baseline_delete_forever_24),
                contentDescription = "emailIcon",
                tint = Color.Red,
                modifier = Modifier
                    .height(30.dp)
                    .width(30.dp)
                    .align(Alignment.End)
                    .clickable {

                    }
            )

        }
    }

}


@Composable
@Preview(showBackground = true)
fun PreviewHomeScreen() {
    HomeScreen(rememberNavController())
}