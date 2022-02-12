package com.example.custom_launcher.ui

import android.app.Activity
import android.app.Application
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import data.AppData
import android.content.pm.ResolveInfo

import android.content.Intent
import android.content.pm.PackageInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

import java.util.ArrayList







object DataHandler {

   suspend fun getInstalledApps(activity:Activity):ArrayList<AppData> {
       val res: ArrayList<AppData> = ArrayList<AppData>()
                val packs: List<PackageInfo> = activity.getPackageManager().getInstalledPackages(PackageManager.GET_META_DATA)
                for (i in packs.indices) {
                    val p = packs[i]
                    if (!false && p.versionName == null) {
                        continue
                    }
                    res.add(AppData(p.applicationInfo.loadLabel(activity.getPackageManager()).toString(),p.packageName,p.applicationInfo.loadIcon(activity.packageManager),p.applicationInfo.packageName,p.versionCode,p.versionName))
                }
                res.sortBy { it.app_name }
       return res;
       }


}