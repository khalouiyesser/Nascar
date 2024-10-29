package tn.esprit.nascar

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tn.esprit.nascar.database.MyDatabase
import tn.esprit.nascar.models.Event
import tn.esprit.nascar.ui.theme.NascarTheme

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val favoritesEventsList = remember {
        mutableStateOf(
            MyDatabase.getDatabase(context).eventsDao().getAll()
        )
    }
    Column(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = R.drawable.ic_account_circle),
            contentDescription = "",
            colorFilter = ColorFilter.tint(Color.Black),
            modifier = Modifier
                .padding(top = 20.dp)
                .size(200.dp)
                .clip(CircleShape)
        )
        Text(
            text = "Name Here",
            textAlign = TextAlign.Center,
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp),
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = "Email Here",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            items(favoritesEventsList.value) { event ->
                val onItemRemoved = {
                   favoritesEventsList.value = favoritesEventsList.value.filter { it.id != event.id }
                }
                FavoriteEventItem(event, onItemRemoved)
            }
        }
    }
}

@Composable
fun FavoriteEventItem(event: Event, onItemRemoved: () -> Unit) {
    val context = LocalContext.current
    Card(
        onClick = {},
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(150.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 10.dp)
    ) {
        Row {
            Image(
                painter = painterResource(id = event.image), contentDescription = "",
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1.5f)
                    .padding(10.dp),
                contentScale = ContentScale.Fit
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(2f)
                    .padding(vertical = 30.dp), verticalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = event.title,
                    textAlign = TextAlign.Center,
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
                )
                Text(
                    text = event.date,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth(),
                    color = Color.Red,
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 14.sp)
                )
            }
            IconButton(
                onClick = {
                    removeFromFavorites(event, context)
                    onItemRemoved()
                }, modifier = Modifier
                    .weight(0.5f)
                    .fillMaxHeight()
            ) {
                Icon(painter = painterResource(id = R.drawable.ic_favorite_empty), contentDescription = "", tint = Color.Red)
            }
        }
    }
}

fun removeFromFavorites(event: Event, context: Context) {
    MyDatabase.getDatabase(context).eventsDao().delete(event)
    Toast.makeText(context, "Removed from favorites", Toast.LENGTH_SHORT).show()
}


@Preview
@Composable
fun ProfileScreenPreview() {
    NascarTheme {
        ProfileScreen()
    }
}