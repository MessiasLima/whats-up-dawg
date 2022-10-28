package io.github.messiasjunior.whatsupdawg.feature.breeds.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.messiasjunior.whatsupdawg.R
import io.github.messiasjunior.whatsupdawg.core.ui.theme.Spacing
import io.github.messiasjunior.whatsupdawg.core.ui.theme.WhatsUpDawgTheme

@Composable
fun BreedsErrorView(onTryAgain: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(92.dp),
            painter = painterResource(id = R.drawable.ic_shiba_cry),
            contentDescription = null
        )

        Spacer(modifier = Modifier.height(Spacing.Small))

        Text(
            text = stringResource(id = R.string.error_title),
            style = MaterialTheme.typography.titleLarge
        )

        Text(
            text = stringResource(id = R.string.error_message),
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(Spacing.Small))

        Button(onClick = onTryAgain) {
            Text(text = stringResource(id = R.string.try_again))
        }
    }
}

@Composable
@Preview
private fun BreedsErrorViewPreview() {
    WhatsUpDawgTheme {
        BreedsErrorView {

        }
    }
}
