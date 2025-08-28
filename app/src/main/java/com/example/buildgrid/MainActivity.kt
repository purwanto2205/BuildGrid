package com.example.buildgrid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.buildgrid.data.DataSource
import com.example.buildgrid.model.Topic
import com.example.buildgrid.ui.theme.BuildGridTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BuildGridTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TopicGrid(modifier = Modifier
                        .padding(
                            start = dimensionResource(R.dimen.padding_small),
                            top = dimensionResource(R.dimen.padding_small),
                            end = dimensionResource(R.dimen.padding_small)
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun TopicGrid(modifier: Modifier = Modifier) {
    LazyVerticalGrid (
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
        modifier = modifier
    ) {
        items(DataSource.topics) { topic ->
            TopicCard(topic = topic)
        }
    }
}

@Composable
fun TopicCard(topic: Topic, modifier: Modifier = Modifier) {
    Card {
        Row {
            Box {
                Image(
                    painter = painterResource(topic.imageResourceId),
                    contentDescription = stringResource(topic.stringResourceId),
                    modifier = modifier
                        .size(68.dp, height = 68.dp)
                        .aspectRatio(1f),
                    contentScale = ContentScale.Crop
                )
            }
            Column {
                Text(
                    stringResource(topic.stringResourceId),
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.padding_medium),
                        top = dimensionResource(R.dimen.padding_medium),
                        end = dimensionResource(R.dimen.padding_medium),
                        bottom = dimensionResource(R.dimen.padding_small)
                    )
                )
                Spacer(Modifier.width(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(R.drawable.ic_grain),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = dimensionResource(R.dimen.padding_medium))
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        topic.count.toString(),
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.padding(
                            start = dimensionResource(R.dimen.padding_medium)
                        )
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppPreview() {
    BuildGridTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding(),
            color = MaterialTheme.colorScheme.background
        ) {
            TopicGrid(modifier = Modifier
                .padding(
                    start = dimensionResource(R.dimen.padding_small),
                    top = dimensionResource(R.dimen.padding_small),
                    end = dimensionResource(R.dimen.padding_small)
                )
            )
        }
    }
}
