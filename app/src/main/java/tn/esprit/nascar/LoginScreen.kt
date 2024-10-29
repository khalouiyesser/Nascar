package tn.esprit.nascar

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tn.esprit.nascar.models.User
import tn.esprit.nascar.remote.MyRetrofit
import tn.esprit.nascar.remote.UserAPI
import tn.esprit.nascar.ui.theme.NascarTheme


@Composable
fun LoginScreen(navController: NavController, modifier: Modifier = Modifier) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val isChecked = remember { mutableStateOf(false) }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo), contentDescription = "", modifier = Modifier
                .aspectRatio(16f / 9f)
                .padding(top = 50.dp, start = 20.dp, end = 20.dp)
        )
        OutlinedTextField(
            value = email.value,
            label = { Text(text = "Email") },
            onValueChange = {email.value = it},
            leadingIcon = { Icon(imageVector = Icons.Outlined.Email, contentDescription = "Email") },
            placeholder = { Text(text = "Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        )
        OutlinedTextField(
            value = password.value,
            label = { Text(text = "Password") },
            onValueChange = { password.value = it },
            leadingIcon = { Icon(imageVector = Icons.Outlined.Lock, contentDescription = "Password") },
            placeholder = { Text(text = "Password") },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passwordVisible)
                    Icons.Outlined.Visibility
                else Icons.Filled.VisibilityOff

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, contentDescription = "")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        )
        Row(modifier = Modifier.padding(20.dp), verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = isChecked.value, onCheckedChange = { isChecked.value = it })
            Text(text = "Remember me")
        }
        Button(
            onClick = { handleNavigation(navController, email, password) }, modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
        ) {
            Text(text = "Login")
        }
    }
}

fun handleNavigation(navController: NavController, email: MutableState<String>, password: MutableState<String>) {

    MyRetrofit.getRetrofit().create(UserAPI::class.java).login(User(email = email.value, password = password.value)).enqueue(object : Callback<User> {
        override fun onResponse(call: Call<User>, response: Response<User>) {
            if (response.isSuccessful)
                navController.navigate("home")
            else
                Toast.makeText(navController.context, response.errorBody()!!.string(), Toast.LENGTH_SHORT).show()
        }

        override fun onFailure(call: Call<User>, t: Throwable) {
            Toast.makeText(navController.context, t.message, Toast.LENGTH_SHORT).show()

        }

    })
}


@Preview
@Composable
fun LoginScreenPreview() {
    NascarTheme {
        LoginScreen(NavController(LocalContext.current))
    }
}