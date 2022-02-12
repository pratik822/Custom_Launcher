package data

import android.graphics.drawable.Drawable

data class AppData(
    val app_name:String,
    val package_Name:String,
    val icon: Drawable,
    val class_name:String,
    val version: Int,
    val version_name: String
);
