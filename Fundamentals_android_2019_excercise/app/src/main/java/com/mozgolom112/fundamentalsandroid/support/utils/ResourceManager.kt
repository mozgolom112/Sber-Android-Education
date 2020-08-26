package com.mozgolom112.fundamentalsandroid.support.utils

import android.app.Application
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

class ResourceManager(private val app: Application) {

    val packageName: String = app.packageName

    fun getString(@StringRes resId: Int): String = app.getString(resId)

    fun getString(@StringRes resId: Int, vararg formatArgs: Any): String =
        app.getString(resId, *formatArgs)

    fun getColor(@ColorRes resId: Int): Int = ContextCompat.getColor(app, resId)

    fun getDimension(@DimenRes resId: Int): Float = getResources().getDimension(resId)

    fun getDimensionPixelSize(@DimenRes resId: Int): Int =
        getResources().getDimensionPixelSize(resId)

    fun getDrawable(@DrawableRes resId: Int): Drawable? = ContextCompat.getDrawable(app, resId)

    fun getResources(): Resources = app.resources

    fun getBitmap(@DrawableRes resId: Int) =
        BitmapFactory.decodeResource(getResources(), resId)
}