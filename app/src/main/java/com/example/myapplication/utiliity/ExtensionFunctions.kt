package com.example.myapplication.utiliity

import android.app.Activity
import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.NetworkCapabilities.*
import android.net.Uri
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.Q
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.myapplication.BuildConfig
import com.google.android.material.textfield.TextInputLayout
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.util.*

fun Context.toast(msg: String) {
    val toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT)
    toast.show()
}

fun Context.toast(msg: String, length: Int) {
    Toast.makeText(this, msg, length).show()
}

fun View.visible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}


fun Any.logE(tag: String, message: String?) {
    if (BuildConfig.DEBUG) {
        Log.e(tag, if (message.isNullOrEmpty()) "NULL" else message)
    }
}

fun Any.logD(tag: String, message: String) {
    if (BuildConfig.DEBUG) {
        Log.e(tag, message)
    }
}

fun Any.logLarge(tag: String?, str: String) {
    if (BuildConfig.DEBUG) {
        if (str.length > 3000) {
            Log.e(tag, str.substring(0, 3000))
            logLarge(tag, str.substring(3000))
        } else {
            Log.e(tag, str)
        }
    }
}

fun Context.isNetworkConnected(): Boolean {
    val manager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
    return if (SDK_INT >= Q)
        manager.getNetworkCapabilities(manager.activeNetwork)?.let {
            it.hasTransport(TRANSPORT_WIFI) || it.hasTransport(TRANSPORT_CELLULAR) ||
                    it.hasTransport(TRANSPORT_BLUETOOTH) ||
                    it.hasTransport(TRANSPORT_ETHERNET) ||
                    it.hasTransport(TRANSPORT_VPN)
        } ?: false
    else
        @Suppress("DEPRECATION")
        manager.activeNetworkInfo?.isConnected == true
}

fun Fragment.hideKeyboard() {
//    view?.let { activity?.hideKeyboard(it) }
}

fun Fragment.createPartFromString(string: String): RequestBody {
    return string.toRequestBody(MultipartBody.FORM)
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Fragment.prepareImageFilePart(partName: String, file: File): MultipartBody.Part {
    val requestBody: RequestBody = file.asRequestBody("image/*".toMediaTypeOrNull())
    return MultipartBody.Part.createFormData(partName, file.name, requestBody)
}

fun Fragment.prepareVideoFilePart(partName: String, file: File): MultipartBody.Part {
    val requestBody: RequestBody = file.asRequestBody("video/*".toMediaTypeOrNull())
    return MultipartBody.Part.createFormData(partName, file.name, requestBody)
}

fun Context.prepareFilePart(partName: String, file: File): MultipartBody.Part {
    val requestBody: RequestBody =
        file.asRequestBody(contentResolver.getType(Uri.fromFile(file))?.toMediaTypeOrNull())
    return MultipartBody.Part.createFormData(partName, file.name, requestBody)
}

fun Fragment.loadImageOrVideoPreview(uri: Uri, iv: ImageView) {
    iv.visibility = View.VISIBLE
    Glide.with(this).load(uri).into(iv)
}

fun Any.logLargeString(tag: String, str: String) {
    if (str.length > 3000) {
        Log.e(tag, str.substring(0, 3000))
        logLargeString(tag, str.substring(3000))
    } else {
        Log.e(tag, str)
    }
}

fun String.capitalizeWords(): String =
    split(" ").joinToString(" ") { str ->
        str.lowercase(Locale.getDefault())
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
    }

fun TextInputLayout.clearError() {
    error = null
    isErrorEnabled = false
}