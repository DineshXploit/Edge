package com.righttofitness.ai.security

import android.os.Build
import java.io.File
import javax.inject.Inject

class DeviceIntegrityMonitor @Inject constructor() {
    fun isLikelyCompromised(): Boolean {
        val rootArtifacts = listOf("/system/app/Superuser.apk", "/system/xbin/su", "/system/bin/su", "/sbin/su")
        return rootArtifacts.any { File(it).exists() } || Build.TAGS?.contains("test-keys") == true
    }
}
