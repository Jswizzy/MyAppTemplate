package com.wintec.blelibrary.extensions

import android.content.Context
import android.widget.Toast

fun Context.toast(msg: String, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText( applicationContext, msg, duration).show()