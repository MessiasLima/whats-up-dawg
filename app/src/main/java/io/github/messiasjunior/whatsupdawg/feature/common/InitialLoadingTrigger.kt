package io.github.messiasjunior.whatsupdawg.feature.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import java.util.concurrent.atomic.AtomicBoolean

@Composable
fun InitialLoading(action: () -> Unit) {
    val shouldFetchData = rememberSaveable { AtomicBoolean(true) }
    if (shouldFetchData.getAndSet(false)) {
        action()
    }
}
