package com.example.neshop.frontend

import android.graphics.Outline
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text2.input.rememberTextFieldState
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.createFontFamilyResolver
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.neshop.R
import com.example.neshop.ui.theme.poppins
import java.time.format.TextStyle

@Composable
fun LoginForm(navController: NavController) {
    var username by remember{
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var passwordVisibilty by remember{
        mutableStateOf(false)
    }
    var DialogTextBlank by remember{
        mutableStateOf(false)
    }
    var dialogAuth by remember{
        mutableStateOf(false)
    }
    Surface(){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(text = "NeShop", fontFamily = poppins, fontWeight = FontWeight.SemiBold, fontSize = 32.sp)
            Text(text = "Welcome to Our App", fontFamily = poppins, fontWeight = FontWeight.Medium, fontStyle = FontStyle.Italic
            ,modifier = Modifier.padding(bottom = 15.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(bottom = 25.dp)
            ) {
                Image(painter = painterResource(id = R.drawable.logo), contentDescription = "LoginLogo"
                , modifier = Modifier.requiredSize(150.dp))
                OutlinedTextField(value = username, onValueChange = {
                    username = it
                }, label = {
                    Text(text = "Username", fontSize = 13.sp)
                },modifier = Modifier
                    .width(250.dp)
                    .height(60.dp),
                    textStyle = androidx.compose.ui.text.TextStyle(fontSize = 13.sp)
                )
                OutlinedTextField(value = password, onValueChange = {
                    password = it
                }, label = {
                    Text(text = "Password", fontSize = 13.sp)
                },modifier = Modifier
                    .width(250.dp)
                    .height(60.dp),
                    textStyle = androidx.compose.ui.text.TextStyle(fontSize = 13.sp),
                    visualTransformation = if(passwordVisibilty) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = {passwordVisibilty = !passwordVisibilty}) {
                            Icon(painter = painterResource(id = R.drawable.invisible),
                                contentDescription = "ShowPasswordIcon",
                                modifier = Modifier.size(20.dp))
                        }
                    }
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(bottom = 10.dp)
            ){
                IconButton(onClick = {if(username == "" || password == ""){
                    DialogTextBlank = true
                } else if(username == "admin" || password == "admin"){
                    navController.navigate("HomeScreen")
                }else{
                    dialogAuth = true
                }},
                    modifier = Modifier
                        .width(250.dp)
                        .clip(RoundedCornerShape(25.dp))
                        .background(color = colorResource(id = R.color.ThemeColor))) {
                    Text(text = "Login", fontFamily = poppins, fontWeight = FontWeight.Bold,color = Color.White)
                }
            }
            HyperlinkText(
                fullText = "Don't have an account? Click here to sign up.",
                hyperlinkedText = "Click here",
                onClick = { navController.navigate("Register") }
            )
            Column(
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier.padding(top = 150.dp)
            ) {
                Text(text = "copyright reserved - 2024", fontFamily = poppins, fontStyle = FontStyle.Italic)
            }
            if (DialogTextBlank) {
                AlertDialog(
                    onDismissRequest = { DialogTextBlank = false },
                    title = { Text("Peringatan") },
                    text = { Text("Tidak dapat mengirimkan formulir karena TextField kosong.") },
                    confirmButton = {
                        Button(
                            onClick = { DialogTextBlank = false }
                        ) {
                            Text("OK")
                        }
                    }
                )
            }
            if (dialogAuth) {
                AlertDialog(
                    onDismissRequest = { dialogAuth = false },
                    title = { Text("Peringatan") },
                    text = { Text("Username atau Password anda salah") },
                    confirmButton = {
                        Button(
                            onClick = { dialogAuth = false }
                        ) {
                            Text("OK")
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun HyperlinkText(fullText: String, hyperlinkedText: String, onClick: () -> Unit) {
    val annotatedString = buildAnnotatedString {
        val startIndex = fullText.indexOf(hyperlinkedText)
        val endIndex = startIndex + hyperlinkedText.length

        append(fullText)
        addStyle(
            style = SpanStyle(
                fontFamily = poppins,
                fontWeight = FontWeight.Medium,
                color = Color.Blue,
                textDecoration = TextDecoration.Underline
            ),
            start = startIndex,
            end = endIndex
        )
        addStringAnnotation(
            tag = "URL",
            annotation = "hyperlink",
            start = startIndex,
            end = endIndex
        )
    }

    ClickableText(
        text = annotatedString,
        onClick = { offset ->
            annotatedString
                .getStringAnnotations(tag = "URL", start = offset, end = offset)
                .firstOrNull()?.let {
                    onClick()
                }
        }
    )
}