import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

@Composable
@Preview
fun App() {
    // criando estado em textField, onde usamos o rememberSaveable
    // para salvar o estado do component. OBS: o rememberSaveable salva objetos minimos e pode levar
    // os dados para diferentes activitys
    val (email, setEmail) = rememberSaveable(
        stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("")) }
    val (pass, setPass) = rememberSaveable(
        stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("")) }
    val (text, setText) = remember { mutableStateOf("") }

    MaterialTheme {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            TextField(
                value = email,
                onValueChange = { setEmail(it)},
                label = {Text("Email")},
                placeholder = {Text("Enter your e-mail")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                leadingIcon = { Icon(imageVector = Icons.Default.Email,
                    contentDescription = "emailIcon") },
                maxLines = 1,
                modifier = Modifier.width(300.dp).padding(20.dp)
            )
            TextField(
                value = pass,
                onValueChange = { setPass(it)},
                label = {Text("Password")},
                placeholder = {Text("Enter your password")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                leadingIcon = { Icon(imageVector = Icons.Default.Close,
                    contentDescription = "password icon") },
                maxLines = 1,
                modifier = Modifier.width(300.dp).padding(20.dp)
            )

            Button(onClick = {
                if (email.text.length < 3 || pass.text.length < 3) {
                    setText("Erro ao logar")
                } else {
                    setText("Logado com sucesso")
                }
            },
                shape = RectangleShape,
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 15.dp
                ),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Magenta)
            ) {
                Text("Login".uppercase(),
                    textAlign = TextAlign.Center,
                )
            }

            Text(text.uppercase(), fontSize = 30.sp, modifier = Modifier.padding(20.dp))
        }
    }
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        state = rememberWindowState( width = 1440.dp, height = 900.dp)
    ) {
        App()
    }
}
