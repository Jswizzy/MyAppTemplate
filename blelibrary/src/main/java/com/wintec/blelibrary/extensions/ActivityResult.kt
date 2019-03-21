package com.wintec.blelibrary.extensions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity


fun AppCompatActivity.startResult(intent: Intent, requestCode: Int): OnResult {
    startActivityForResult(intent, requestCode)
    return OnResult(requestCode)
}

class OnResult(private val requestCode: Int) {
    operator fun invoke(callback: (Int) -> Unit ) {
            callback(requestCode)
    }
}

// Constants
internal const val REQUEST_ENABLE_BLUETOOTH = 1
