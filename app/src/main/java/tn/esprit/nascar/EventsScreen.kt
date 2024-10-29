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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
fun EventsScreen(modifier: Modifier = Modifier) {
    val eventsList = listOf(
        Event(title = stringResource(id = R.string.event1), date = stringResource(id = R.string.eventDate1), image = R.drawable.ic_event1),
        Event(title = stringResource(id = R.string.event2), date = stringResource(id = R.string.eventDate2), image = R.drawable.ic_event2),
        Event(title = stringResource(id = R.string.event3), date = stringResource(id = R.string.eventDate3), image = R.drawable.ic_event3),
        Event(title = stringResource(id = R.string.event4), date = stringResource(id = R.string.eventDate4), image = R.drawable.ic_event4),
        Event(title = stringResource(id = R.string.event1), date = stringResource(id = R.string.eventDate1), image = R.drawable.ic_event1),
        Event(title = stringResource(id = R.string.event2), date = stringResource(id = R.string.eventDate2), image = R.drawable.ic_event2),
        Event(title = stringResource(id = R.string.event3), date = stringResource(id = R.string.eventDate3), image = R.drawable.ic_event3),
        Event(title = stringResource(id = R.string.event4), date = stringResource(id = R.string.eventDate4), image = R.drawable.ic_event4),

        )
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(eventsList) { event ->
            EventItem(event)
        }
    }
}

@Composable
fun EventItem(event: Event) {
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
                onClick = { addToFavorites(event, context) }, modifier = Modifier
                    .weight(0.5f)
                    .fillMaxHeight()
            ) {
                Icon(painter = painterResource(id = R.drawable.ic_favorite), contentDescription = "", tint = Color.Red)
            }
        }
    }
}

fun addToFavorites(event: Event, context: Context) {
    MyDatabase.getDatabase(context).eventsDao().insert(event)
    Toast.makeText(context, "Added to favorites", Toast.LENGTH_SHORT).show()
}


@Preview
@Composable
fun EventsScreenPreview() {
    NascarTheme {
        EventsScreen()
    }
}