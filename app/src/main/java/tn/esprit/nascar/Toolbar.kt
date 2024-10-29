package tn.esprit.nascar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ToolbarComponent(navController: NavController, modifier: Modifier = Modifier) {
    val openAlertDialog = remember { mutableStateOf(false) }

    if (openAlertDialog.value) BuildAlertDialog(
        onDismissRequest = { openAlertDialog.value = false },
        onConfirmRequest = { openAlertDialog.value = false })

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(Color.Black)
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(painter = painterResource(id = R.drawable.ic_burger), contentDescription = "")
        Spacer(modifier = Modifier.width(20.dp))
        Text(text = stringResource(id = R.string.app_name), color = Color.White, fontSize = 20.sp, modifier = Modifier.weight(2f))
        Icon(
            painter = painterResource(id = R.drawable.ic_info),
            contentDescription = "",
            tint = Color.White,
            modifier = Modifier.clickable { navController.navigate("about") }
        )
        Spacer(modifier = Modifier.width(20.dp))
        Icon(
            painter = painterResource(id = R.drawable.ic_logout),
            contentDescription = "",
            tint = Color.White,
            modifier = Modifier.clickable { openAlertDialog.value = true })
    }

}

@Composable
fun BuildAlertDialog(onDismissRequest: () -> Unit, onConfirmRequest: () -> Unit) {
    AlertDialog(onDismissRequest = onDismissRequest, confirmButton = {
        TextButton(onClick = onConfirmRequest) {
            Text(text = "Yes")
        }
    }, dismissButton = {
        TextButton(onClick = onDismissRequest) {
            Text(text = "No")
        }
    }, title = { Text(text = "Logout") }, text = { Text(text = "Are you sure you want to logout?") })
}

@Preview
@Composable
fun ToolbarComponentPreview() {
    ToolbarComponent(NavController(LocalContext.current))
}