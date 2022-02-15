package com.developers.todoappwithcompose.helpers.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.size.OriginalSize
import com.developers.todoappwithcompose.R
import com.developers.todoappwithcompose.ui.theme.colorNote2

@Composable
fun InputText(
    key: String,
    label: String,
    value: String,
    radius: Int,
    keyboardType: KeyboardType,
    itemSelected: (key: String, text: String) -> Unit,
) {

    var text by remember { mutableStateOf(value) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily.Serif,
            text = label,
            fontSize = 16.sp,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp)

        )
        OutlinedTextField(
            label = { Text(text = label, fontSize = 14.sp, color = colorNote2) },
            placeholder = { Text(text = label,color=Color.White) },
            value = text,
            maxLines = 1,
            textStyle = TextStyle(
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp
            ),
            isError = true,
            shape = RoundedCornerShape(radius.dp),
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = ImeAction.Next
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = colorNote2,
                unfocusedBorderColor = Color.White
            ),
            onValueChange = {
                itemSelected(key, it)
                text=it
            }
        )
    }

}

@Composable
fun GButton(
    modifier: Modifier = Modifier,
    text: String,
    backgroundColor: Color,
    onButtonClicked: () -> Unit,
    textSize: Int = 18,
    roundCornerValue: Int = 8,
) {
    Button(
        onClick = {
            onButtonClicked()
        },
        modifier = modifier,
        shape = RoundedCornerShape(roundCornerValue.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor)
    ) {
        Text(
            text = text,
            fontSize = textSize.sp
        )
    }

}


@Composable
fun ProfileImage(data:String, radius: Int ) {
    Image(
        painter =  rememberImagePainter(
            data = data,
            builder = {
                size(OriginalSize)
            },
        ),
        contentDescription = "avatar",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(100.dp)
            .clip(RoundedCornerShape(radius))
            .border(2.dp, Color.Gray,RoundedCornerShape(radius))
    )
}