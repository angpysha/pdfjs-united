package io.github.angpysha.pdfjsviewdemo

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import com.google.android.material.composethemeadapter.MdcTheme
import io.github.angpysha.pdfjsview.ComposePDFJSView

class ComposeHostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compose_host)

        val cv = findViewById<ComposeView>(R.id.compose_view)

        cv.setContent {
            MdcTheme {
                SetContent()
            }
        }
    }
    val fileUrl = mutableStateOf<String>("")
    @Composable
    @Preview
    private fun SetContent() {

        val result = remember { mutableStateOf<Uri?>(null) }
        val launcher = rememberLauncherForActivityResult(ActivityResultContracts.OpenDocument()) {
            result.value = it
        }

        Column() {
            Text(text = "Test compose activity")
            Button(onClick = {
                launcher.launch(arrayOf("*/*"))
            }) {
                Text(text = "open file picker")
            }
            ComposePDFJSView(uri = result.value)
        }
    }
}