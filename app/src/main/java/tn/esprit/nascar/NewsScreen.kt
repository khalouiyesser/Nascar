package tn.esprit.nascar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tn.esprit.nascar.models.News
import tn.esprit.nascar.ui.theme.NascarTheme


@Composable
fun NewsScreen(modifier: Modifier = Modifier) {
    val newsList = listOf(
        News(title = stringResource(id = R.string.news1), description = stringResource(id = R.string.newsDesc1), image = R.drawable.ic_news1),
        News(title = stringResource(id = R.string.news2), description = stringResource(id = R.string.newsDesc2), image = R.drawable.ic_news2),
        News(title = stringResource(id = R.string.news3), description = stringResource(id = R.string.newsDesc3), image = R.drawable.ic_news3),
        News(title = stringResource(id = R.string.news1), description = stringResource(id = R.string.newsDesc1), image = R.drawable.ic_news1),
        News(title = stringResource(id = R.string.news2), description = stringResource(id = R.string.newsDesc2), image = R.drawable.ic_news2),
        News(title = stringResource(id = R.string.news3), description = stringResource(id = R.string.newsDesc3), image = R.drawable.ic_news3),
        News(title = stringResource(id = R.string.news1), description = stringResource(id = R.string.newsDesc1), image = R.drawable.ic_news1),
        News(title = stringResource(id = R.string.news2), description = stringResource(id = R.string.newsDesc2), image = R.drawable.ic_news2),
        News(title = stringResource(id = R.string.news3), description = stringResource(id = R.string.newsDesc3), image = R.drawable.ic_news3)
    )

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        items(newsList) { news ->
            NewsItem(news)
        }
    }
}

@Composable
fun NewsItem(news: News) {
    Card(
        onClick = {},
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(350.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 10.dp)
    ) {

        Image(
            painter = painterResource(id = news.image), contentDescription = "",
            modifier = Modifier
                .fillMaxWidth(1f)
                .height(150.dp),
            contentScale = ContentScale.FillWidth
        )
        Text(
            text = news.title, modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
        )
        Text(
            text = news.description,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            overflow = TextOverflow.Ellipsis
        )
    }
}


@Preview
@Composable
fun NewsScreenPreview() {
    NascarTheme {
        NewsScreen()
    }
}