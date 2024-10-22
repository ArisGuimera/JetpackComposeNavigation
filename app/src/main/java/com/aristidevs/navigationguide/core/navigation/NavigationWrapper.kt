package com.aristidevs.navigationguide.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import androidx.navigation.toRoute
import com.aristidevs.navigationguide.DetailScreen
import com.aristidevs.navigationguide.HomeScreen
import com.aristidevs.navigationguide.LoginScreen
import com.aristidevs.navigationguide.SettingsScreen
import com.aristidevs.navigationguide.core.navigation.type.createNavType
import com.aristidevs.navigationguide.core.navigation.type.settingsInfoType
import kotlin.reflect.typeOf

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Login) {
        composable<Login> {
            LoginScreen { navController.navigate(Home) }
        }

        composable<Home>(deepLinks = listOf(navDeepLink {
            uriPattern = "https://aristi.dev/home"
        })) {
            HomeScreen { name -> navController.navigate(Detail(name = name)) }
        }

        composable<Detail>(deepLinks = listOf(navDeepLink { uriPattern = "https://aristi.dev/detail/{name}" })) { backStackEntry ->
//            val detail: Detail = backStackEntry.toRoute()
            val name = backStackEntry.arguments?.getString("name").orEmpty()
            DetailScreen(name = name,
                navigateBack = { navController.navigateUp() },
                navigateToSettings = { navController.navigate(Settings(it)) })
        }

        composable<Settings>(typeMap = mapOf(typeOf<SettingsInfo>() to createNavType<SettingsInfo>())) { backStackEntry ->
            val settings: Settings = backStackEntry.toRoute()
            SettingsScreen(settings.info)
        }
    }
}