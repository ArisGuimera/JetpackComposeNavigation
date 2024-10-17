package com.aristidevs.navigationguide.core.navigation

import android.os.Bundle
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
object Login

@Serializable
object Home

@Serializable
data class Detail(val name: String)

@Serializable
data class Settings(val info: SettingsInfo)

@Serializable
@Parcelize
data class SettingsInfo(
    val name: String,
    val id: Int,
    val darkMode: Boolean,
    val suscribete: Boolean
) : Parcelable

