package com.stonetree.fallingwords.core.extensions

import android.content.Context

fun String.read(context: Context): String{
    return context.assets.open(this).use {  input ->
        return@use input.reader().readText()
    }
}