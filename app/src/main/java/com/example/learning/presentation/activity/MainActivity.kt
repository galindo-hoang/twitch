package com.example.learning.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learning.R
import com.example.learning.presentation.ui.theme.LearningTheme
import com.example.learning.presentation.ui.theme.SpaceMedium
import io.github.aakira.napier.Napier

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val textInput = remember { mutableStateOf("") }
            LearningTheme {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(100.dp)
                ) {

                }
                PlaceHolderText("Username/E-Mail", textInput)
            }
        }
    }

    companion object {
        val horizontal = 20.dp
        val vertical = 30.dp
    }
}


@Preview
@Composable
fun Login() {
    LearningTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = MainActivity.horizontal,
                    vertical = MainActivity.vertical
                )
        ) {
            LoginHeader(Modifier.fillMaxHeight(1 / 5f))
            LoginBody(Modifier.fillMaxHeight(3 / 5f))
            LoginFooter(Modifier.fillMaxHeight(1 / 5f))
        }
    }
}

@Composable
fun LoginBody(
    modifierBox: Modifier = Modifier
) {
    val username = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    Box(
        modifier = modifierBox
            .fillMaxWidth()
            .padding(horizontal = 5.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Login",
                    color = Color.White,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterStart)
                )
            }
            Spacer(modifier = Modifier.height(SpaceMedium))
            PlaceHolderText(
                textHint = stringResource(id = R.string.login_hint),
                textInput = username
            )
            Spacer(modifier = Modifier.height(SpaceMedium))
            PlaceholderPassword(
                textHint = stringResource(id = R.string.password_hint),
                textInput = password
            )
            Spacer(modifier = Modifier.height(SpaceMedium))
            Button(
                onClick = {
                    Napier.e(
                        tag = "huy.hoang1",
                        message = "username: ${username.value} - password: ${password.value}"
                    )
                },
                colors =
                ButtonDefaults.buttonColors(
                    backgroundColor = Color.Green,
                    contentColor = Color.Black
                ),
                modifier = Modifier.align(Alignment.End),
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 6.dp),
                    text = stringResource(id = R.string.login),
                    color = MaterialTheme.colors.onPrimary,
                    fontSize = 18.sp,
                )
            }
        }
    }
}

@Composable
fun LoginFooter(
    modifier: Modifier
) {
    Box(modifier = modifier) {

    }
}

@Composable
fun LoginHeader(
    modifier: Modifier
) {
    Box(
        modifier = modifier
    ) {

    }
}

@Composable
fun PlaceHolderText(
    textHint: String = "",
    textInput: MutableState<String>
) {
    OutlinedTextField(
        value = textInput.value,
        modifier = Modifier.fillMaxWidth(),
        onValueChange = { textInput.value = it },
        placeholder = { Text(text = textHint, color = Color.LightGray) },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            cursorColor = Color.Green
        )
    )
}

@Composable
fun PlaceholderPassword(
    textHint: String = "",
    textInput: MutableState<String>
) {
    val passwordVisible = rememberSaveable { mutableStateOf(true) }
    OutlinedTextField(
        value = textInput.value,
        modifier = Modifier.fillMaxWidth(),
        onValueChange = { textInput.value = it },
        placeholder = { Text(text = textHint, color = Color.LightGray) },
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            cursorColor = Color.Green,

            ),
        trailingIcon = {
            val image =
                if (passwordVisible.value) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
            val description = if (passwordVisible.value) "Hide password" else "Show password"
            IconButton(
                onClick = { passwordVisible.value = !passwordVisible.value }
            ) {
                Icon(imageVector = image, description)
            }
        }
    )
}
