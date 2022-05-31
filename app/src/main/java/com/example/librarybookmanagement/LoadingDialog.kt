package com.example.librarybookmanagement

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout

class LoadingDialog(var activity: Activity) {
    lateinit var dialog: AlertDialog

    fun startLoadingDialog() {
        var builder = AlertDialog.Builder(activity)
        builder.setView(LayoutInflater.from(activity)
            .inflate(R.layout.custom_loading_layout, null))
        builder.setCancelable(true)

        dialog = builder.create()
        dialog.show()
    }

    fun dismissDialog() {
        dialog.dismiss()
    }
}