package com.binh.hellocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.sharp.AccountBox
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.binh.hellocompose.ui.theme.HelloComposeTheme

val normalSpacing = 32.dp

class MainActivity : ComponentActivity() {
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainActivityApp { MainActivityScreen(viewModel = viewModel) }
        }
    }
}

@Composable
fun MainActivityApp(content: @Composable () -> Unit) {
    HelloComposeTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}

@Composable
fun MainActivityScreen(viewModel: MainActivityViewModel) {
    val usrName by viewModel.userName.observeAsState()
    val pwd by viewModel.password.observeAsState()
    val userName = remember {
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
    ) {
        Text(text = "Sign Up", fontSize = 28.sp)
        Spacer(modifier = Modifier.height(24.dp))
        SignInWithServiceButton(title = "Login with Facebook", icon = 0, fontColor = Color(78, 159, 206)) {

        }
        Spacer(modifier = Modifier.height(16.dp))
        SignInWithServiceButton(title = "Login with Google", icon = 0, fontColor = Color.Red) {

        }
        Spacer(modifier = Modifier.height(16.dp))
        SignInWithServiceButton(title = "Login with Appple", icon = 0, fontColor = Color.Black) {

        }
        Spacer(modifier = Modifier.padding(8.dp))
        Text(
            text = "We will not use your personal data without your permission.",
            fontSize = 14.sp,
            color = Color.DarkGray,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = normalSpacing, end = normalSpacing)
        )
        Spacer(modifier = Modifier.padding(16.dp))
        Row {
            Divider(modifier = Modifier.weight(1f).align(CenterVertically))
            Text(text = "OR", textAlign = TextAlign.Center)
            Divider(modifier = Modifier.weight(1f).align(CenterVertically))
        }
        Spacer(modifier = Modifier.padding(16.dp))
        InputTextField(
            value = userName.value,
            label = "User Name",
        ) { newValue ->
            userName.value = newValue
        }
        Spacer(modifier = Modifier.height(16.dp))
        InputTextField(
            value = password.value,
            label = "Password",
            isPasswordField = true
        ) { newValue ->
            password.value = newValue
        }
        Spacer(modifier = Modifier.height(16.dp))
        SignUpButton(buttonTitle = "SIGN UP", backgroundColor = Color(592, 181, 89), textColor = Color.Red) {

        }
        Spacer(modifier = Modifier.height(16.dp))
        SignInButton(buttonTitle = "Sign In", textColor = Color(592, 181, 89)) {

        }
    }
}

@Composable
fun SignInWithServiceButton(title: String, icon: Int, fontColor: Color, OnClick: () -> Unit) {
    Button(
        onClick = OnClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 32.dp, end = 32.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(235, 235, 235))
    ) {
        Row {
            Icon(
                Icons.Sharp.AccountBox,
                contentDescription = title,
                modifier = Modifier
                    .weight(0.5f)
                    .padding(start = 4.dp)
                    .wrapContentWidth(
                        Alignment.Start
                    )
            )
            Text(
                text = title,
                fontSize = 16.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Normal,
                color = fontColor,
                modifier = Modifier
                    .weight(3f)
                    .padding(end = 4.dp)
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )
        }
    }
}

@Composable
fun InputTextField(
    value: String,
    label: String,
    isPasswordField: Boolean = false,
    OnValueChange: (String) -> Unit
) {
    var passwordVisibility by remember {
        mutableStateOf(isPasswordField)
    }
    TextField(
        label = { Text(text = label) },
        value = value,
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
        visualTransformation = if (passwordVisibility) PasswordVisualTransformation() else VisualTransformation.None,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = normalSpacing, end = normalSpacing),
        leadingIcon = {},
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                val visibilityIcon =
                    if (passwordVisibility) Icons.Filled.Done else Icons.Filled.Clear
                // Please provide localized description for accessibility services
                val description = if (passwordVisibility) "Show password" else "Hide password"
                Icon(imageVector = visibilityIcon, contentDescription = description)
            }
        },
        onValueChange = { OnValueChange(it) },
    )
}

@Composable
fun SignUpButton(
    buttonTitle: String,
    backgroundColor: Color,
    textColor: Color,
    OnClick: () -> Unit,
) {
    Button(
        onClick = OnClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = normalSpacing, end = normalSpacing)
    ) {
        Text(text = buttonTitle, color = textColor, fontSize = 16.sp)
    }
}

@Composable
fun SignInButton(
    buttonTitle: String,
    textColor: Color,
    OnClick: () -> Unit,
) {
    TextButton(
        onClick = OnClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = normalSpacing, end = normalSpacing)
    ) {
        Text(text = buttonTitle, color = textColor, fontSize = 16.sp)
    }
}

@Preview(showSystemUi = true, showBackground = true, device = Devices.PIXEL_3_XL)
@Composable
fun PreviewMainActivity(viewModel: MainActivityViewModel = MainActivityViewModel()) {
    HelloComposeTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            MainActivityScreen(viewModel = viewModel)
        }
    }
}