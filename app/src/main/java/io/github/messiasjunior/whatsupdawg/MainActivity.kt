package io.github.messiasjunior.whatsupdawg

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.ExperimentalMaterial3Api
import dagger.hilt.android.AndroidEntryPoint
import io.github.messiasjunior.whatsupdawg.core.ui.theme.WhatsUpDawgTheme
import io.github.messiasjunior.whatsupdawg.feature.main.MainView
import kotlinx.coroutines.FlowPreview

@ExperimentalMaterial3Api
@FlowPreview
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
