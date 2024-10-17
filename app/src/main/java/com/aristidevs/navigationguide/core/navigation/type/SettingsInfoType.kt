package com.aristidevs.navigationguide.core.navigation.type

import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.navigation.NavType
import com.aristidevs.navigationguide.core.navigation.SettingsInfo
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

val settingsInfoType = object :NavType<SettingsInfo>(isNullableAllowed = true){

    override fun get(bundle: Bundle, key: String): SettingsInfo? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(key, SettingsInfo::class.java)
        } else {
            bundle.getParcelable(key)
        }
    }

    override fun parseValue(value: String): SettingsInfo {
        return Json.decodeFromString<SettingsInfo>(value)
    }

    override fun serializeAsValue(value: SettingsInfo): String {
        return Uri.encode(Json.encodeToString(value))
    }

    override fun put(bundle: Bundle, key: String, value: SettingsInfo) {
        bundle.putParcelable(key, value)
    }

}