package io.github.angpysha.pdfjsview

import android.content.Context
import android.net.Uri
import android.util.AttributeSet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.AbstractComposeView
import androidx.compose.ui.viewinterop.AndroidView
import java.lang.reflect.Modifier

@Composable
fun ComposePDFJSView(uri: Uri? = null) {
    //val uri = mutableStateOf<Uri>(Uri.EMPTY)

    AndroidView(
        //modifier = Modifier.fillMaxSize(),
        factory = {
            PDFJSView(it).apply {

            }
        },
        update = { view ->
            uri?.let {
                view.source = uri
            }
        }
    )
}