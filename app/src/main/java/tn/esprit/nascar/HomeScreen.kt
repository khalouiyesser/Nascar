package tn.esprit.nascar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import tn.esprit.nascar.ui.theme.NascarTheme


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        val pages = listOf("News", "Events", "Profile")
        val pagerState = rememberPagerState(pageCount = { 3 })
        val coroutineScope = rememberCoroutineScope()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color = if (pagerState.currentPage == iteration) colorResource(id = R.color.purple_500) else Color.LightGray
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .clickable { coroutineScope.launch { pagerState.animateScrollToPage(iteration) } })
                {
                    Text(
                        text = pages[iteration],
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth(),
                        color = color
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(2.dp)
                            .background(color)
                            .size(2.dp)
                    )
                }
            }
        }
        HorizontalPager(state = pagerState) { page ->
            when (page) {
                0 -> NewsScreen()
                1 -> EventsScreen()
                2 -> ProfileScreen()
            }
        }
    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    NascarTheme {
        HomeScreen()
    }
}