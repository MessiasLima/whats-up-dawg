package io.github.messiasjunior.whatsupdawg.feature.breeds.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import io.github.messiasjunior.whatsupdawg.R
import io.github.messiasjunior.whatsupdawg.core.ui.theme.Spacing
import io.github.messiasjunior.whatsupdawg.core.ui.theme.WhatsUpDawgTheme

@Composable
fun BreedsLoadingView() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
        Spacer(modifier = Modifier.height(Spacing.Small))
        Text(text = stringResource(id = R.string.loading))
    }
}

@Composable
@Preview(showBackground = true)
private fun BreedsLoadingViewPreview() {
    WhatsUpDawgTheme {
        BreedsLoadingView()
    }
}
