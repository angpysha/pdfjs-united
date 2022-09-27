package io.github.angpysha.pdfjsview

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.RelativeLayout
import androidx.annotation.RequiresApi
import androidx.documentfile.provider.DocumentFile
import androidx.webkit.WebViewAssetLoader
import androidx.webkit.WebViewClientCompat
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream


class PDFJSView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : RelativeLayout(context, attrs, defStyle) {

    val domain = "pdfjsview.dev"
    private lateinit var pdfView: WebView

    var source: Uri = Uri.EMPTY
        set(value) {
            field = value
            didSourceSet(value)
        }

    private fun didSourceSet(value: Uri) {
        pdfView?.let {
            val filesDir = context.filesDir

            val contentResolver = context.contentResolver ?: return

            val fileName = value.lastPathSegment

            val file = File(filesDir, fileName)
            try {
                file.parentFile.mkdirs()
                file.createNewFile()
                val inputStream = contentResolver.openInputStream(value) ?: return //crashing here
                val outputStream: OutputStream = FileOutputStream(file)
                val buf = ByteArray(1024)
                var len: Int
                while (inputStream!!.read(buf).also { len = it } > 0) outputStream.write(buf, 0, len)
                outputStream.close()
                inputStream.close()
            } catch (ignore: IOException) {
                return
            }
            val script = "pdfjsLib.getDocument('https://${domain}/internal/${fileName}').promise.then(doc => { PDFViewerApplication.load(doc); });"
            Log.d("Open script", script)
            it.evaluateJavascript(script, {st ->

            })
        }
    }

    init {
        View.inflate(context, R.layout.pdfview, this)

        pdfView = findViewById<WebView>(R.id.pdfjswebview)
        pdfView.settings.allowFileAccess = true
        pdfView.settings.javaScriptEnabled = true

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        val assetLoader = WebViewAssetLoader.Builder()
            .setDomain(domain)
            .addPathHandler("/assets/", WebViewAssetLoader.AssetsPathHandler(context))
            .addPathHandler("/internal/", WebViewAssetLoader.InternalStoragePathHandler(context, context.filesDir))
            .build()

        pdfView.setWebViewClient(object : WebViewClientCompat() {
            @RequiresApi(21)
            override fun shouldInterceptRequest(
                view: WebView,
                request: WebResourceRequest
            ): WebResourceResponse? {
                return assetLoader.shouldInterceptRequest(request.url)
            }

            // for API < 21
            override fun shouldInterceptRequest(
                view: WebView,
                url: String
            ): WebResourceResponse? {
                return assetLoader.shouldInterceptRequest(Uri.parse(url))
            }
        })

        val webViewSettings: WebSettings = pdfView.settings
        // Setting this off for security. Off by default for SDK versions >= 16.
        // Setting this off for security. Off by default for SDK versions >= 16.
        webViewSettings.allowFileAccessFromFileURLs = false
        // Off by default, deprecated for SDK versions >= 30.
        // Off by default, deprecated for SDK versions >= 30.
        webViewSettings.allowUniversalAccessFromFileURLs = false
        // Keeping these off is less critical but still a good idea, especially if your app is not
        // using file:// or content:// URLs.
        // Keeping these off is less critical but still a good idea, especially if your app is not
        // using file:// or content:// URLs.
        pdfView.settings.allowFileAccess = false
        pdfView.settings.allowContentAccess = false

        // Assets are hosted under http(s)://appassets.androidplatform.net/assets/... .
        // If the application's assets are in the "main/assets" folder this will read the file
        // from "main/assets/www/index.html" and load it as if it were hosted on:
        // https://appassets.androidplatform.net/assets/www/index.html

        // Assets are hosted under http(s)://appassets.androidplatform.net/assets/... .
        // If the application's assets are in the "main/assets" folder this will read the file
        // from "main/assets/www/index.html" and load it as if it were hosted on:
        // https://appassets.androidplatform.net/assets/www/index.html
        pdfView.loadUrl("https://${domain}/assets/pdfjs/web/viewer.html")
    }
}