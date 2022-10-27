package io.github.messiasjunior.whatsupdawg

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import io.github.messiasjunior.whatsupdawg.core.ui.theme.WhatsUpDawgTheme
import io.github.messiasjunior.whatsupdawg.feature.main.MainView

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WhatsUpDawgTheme {
                MainView()
            }
        }
    }
}
