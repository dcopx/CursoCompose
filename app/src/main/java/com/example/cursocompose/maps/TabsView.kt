package com.example.cursocompose.maps

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun TabsView(onTabSelected: (tab: Int) -> Unit) {
    val tabs = listOf(
        TabData("Ubicacion", Icons.Filled.LocationOn),
        TabData("Poligono", Icons.Filled.Create),
        TabData("Detalle", Icons.Filled.Info)
    )
    val selectedTab = remember { mutableStateOf(0) }

    Column {
        TabRow(selectedTabIndex = selectedTab.value) {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    selected = selectedTab.value == index,
                    onClick = { selectedTab.value = index },
                    text = { Text(text = tab.title) },
                    icon = { Icon(
                        imageVector = tab.icon,
                        contentDescription = null // Provide a content description if needed
                    )}
                )
            }
        }
        onTabSelected(selectedTab.value)
        // Contenido para cada tab
//        when (selectedTab.value) {
//            0 -> HomeScreen()
//            1 -> ProfileScreen()
//            2 -> SettingScreen()
//        }
    }
}

//Creamos una data class para el texto y el titulo del Tab
data class TabData(val title: String, val icon: ImageVector)

@Composable
fun HomeScreen() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Red))
}

@Composable
fun ProfileScreen() {
    // Contenido pantalla Profile
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Blue))
}

@Composable
fun SettingScreen() {
    // Contenido pantalla Setting
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Gray))
}