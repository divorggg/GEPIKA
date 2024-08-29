package com.example.projectmagangapp

import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan

fun SpannableStringBuilder.bold(builderAction: SpannableStringBuilder.() -> Unit): SpannableStringBuilder {
    val start = length
    builderAction()
    setSpan(StyleSpan(Typeface.BOLD), start, length, 0)
    return this
}
