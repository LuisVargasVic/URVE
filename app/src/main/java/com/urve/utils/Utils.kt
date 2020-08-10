package com.urve.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.Window
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.urve.R


fun Activity.navigateToActivityTop(classActivity: AppCompatActivity) {
    val intent = Intent(this, classActivity::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
    this.startActivity(intent)
    this.finish()
}

fun Activity.navigateToActivityAffinity(classActivity: AppCompatActivity) {
    val intent = Intent(this, classActivity::class.java)
    this.startActivity(intent)
    this.finishAffinity()
}

fun Context.simpleAlertDialog(message: String?, function: (() -> Unit)?): AlertDialog? {
    val alertDialog =
        AlertDialog.Builder(this).create()
    alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    alertDialog.setMessage(message)
    alertDialog.setButton(
        AlertDialog.BUTTON_POSITIVE,
        this.getString(R.string.all_ok)
    ) { dialog, _ ->
        if (function != null) {
            function()
        }
        dialog.dismiss()
    }
    return alertDialog
}