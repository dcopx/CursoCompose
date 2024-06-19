package com.example.cursocompose.maps

import android.location.Location
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.cursocompose.composables.location.RequestPermissionDialog
import com.example.cursocompose.composables.location.locationRequest
import com.example.cursocompose.drawPolygon.CustomBotomDrawer
import com.example.cursocompose.drawPolygon.CustomMarker2
import com.example.cursocompose.drawPolygon.MarkerObj2
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polygon

@Composable
fun MapScreen() {
    val context = LocalContext.current
    var tabSelected: Int by remember { mutableStateOf(0) }

    var location: Location? by remember { mutableStateOf(null) }
    var textDialog: String by remember { mutableStateOf("") }
    var permissionStatus by remember { mutableStateOf(0) }

    var markers by remember { mutableStateOf(listOf<MarkerObj2>()) }
    var showDrawer by remember { mutableStateOf(false) }


    locationRequest(context) { newLocation, messageDialog, permission ->
        if (location == null){
            location = newLocation
            textDialog = messageDialog
            permissionStatus = permission
        }
        if (location != null && newLocation != null ) {
            if (isNewLocation(location!!, newLocation)){
                location = newLocation
                textDialog = messageDialog
                permissionStatus = permission
            }
        }
    }

    if (location != null) {
        LaunchedEffect(key1 = tabSelected) {
            markers = loadMarkers(tabSelected, location!!)
        }

        Column(modifier = Modifier.fillMaxSize()) {
            TabsView(){
                if (it != tabSelected){
                    tabSelected = it
                }
            }
            MapView(
                location = location!!,
                onMapClick = { position ->
                    if (tabSelected == 1) {
                        markers = markers.toMutableList().apply {
                            add(MarkerObj2(MarkerState(position), ""))
                        }
                    }
                },
                onMarkerClick = {
                    if (tabSelected != 0){
                        markers.forEach { marker ->
                            CustomMarker2(
                                marker = marker,
                                onClick = {
                                    if (tabSelected == 1)
                                        markers = markers.toMutableList().apply { remove(marker) }
                                    if (tabSelected == 2)
                                        showDrawer = true
                                }
                            )
                        }
                        if (tabSelected == 1 && markers.size > 2){
                            val points = mutableListOf<LatLng>()
                            markers.forEach {markerObj ->
                                points.add(markerObj.state.position)
                            }
                            Polygon(
                                points = points,
                                fillColor = Color.Cyan,
                                strokeColor = Color.Cyan)
                        }
                    }
                }
            )
        }
        if (showDrawer){
            CustomBotomDrawer { showDrawer = false }
        }
    } else {
        if (permissionStatus == 2){
            RequestPermissionDialog(
                permissionMessage = textDialog,
                permissionStatus = permissionStatus,
                context = context)
        } else {
            Dialog(
                onDismissRequest = { },
                DialogProperties(
                    dismissOnBackPress = false,
                    dismissOnClickOutside = false
                )
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(100.dp)
                        .background(Color.White, shape = RoundedCornerShape(8.dp))
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

private fun loadMarkers(tabSelected: Int, location: Location): List<MarkerObj2> {
    val markers = mutableListOf<MarkerObj2>()

    if (tabSelected == 1) {
        markers.add(
            MarkerObj2(
                state = MarkerState(
                    LatLng(
                        location.latitude + 0.0003,
                        location.longitude + 0.0003
                    )
                ),
                title = "Aea1"
            )
        )
    }
    if (tabSelected == 2) {
        markers.add(
            MarkerObj2(
                state = MarkerState(
                    LatLng(
                        location.latitude + 0.0004,
                        location.longitude + 0.0002
                    )
                ),
                title = "Aea2"
            )
        )
        markers.add(
            MarkerObj2(
                state = MarkerState(
                    LatLng(
                        location.latitude + 0.0002,
                        location.longitude + 0.0004
                    )
                ),
                title = "Aea3"
            )
        )
        markers.add(
            MarkerObj2(
                state = MarkerState(
                    LatLng(
                        location.latitude + 0.0001,
                        location.longitude + 0.0001
                    )
                ),
                title = "Aea4"
            )
        )
    }
    return markers
}

private fun isNewLocation(oldLocation: Location, newLocation: Location): Boolean{
    return ((Math.round(oldLocation.longitude * 1e6)) != (Math.round(newLocation.longitude * 1e6))) &&
            ((Math.round(oldLocation.longitude * 1e6) + 0.000001) != (Math.round(newLocation.longitude * 1e6) + 0.000000)) &&
            ((Math.round(oldLocation.longitude * 1e6) + 0.000001) != (Math.round(newLocation.longitude * 1e6) + 0.000001)) &&
            ((Math.round(oldLocation.longitude * 1e6) + 0.000001) != (Math.round(newLocation.longitude * 1e6) - 0.000001)) &&
            ((Math.round(oldLocation.longitude * 1e6) - 0.000001) != (Math.round(newLocation.longitude * 1e6) + 0.000000)) &&
            ((Math.round(oldLocation.longitude * 1e6) - 0.000001) != (Math.round(newLocation.longitude * 1e6) + 0.000001)) &&
            ((Math.round(oldLocation.longitude * 1e6) - 0.000001) != (Math.round(newLocation.longitude * 1e6) - 0.000001)) &&
            ((Math.round(oldLocation.latitude * 1e6)) != (Math.round(newLocation.latitude * 1e6))) &&
            ((Math.round(oldLocation.latitude * 1e6) + 0.000001) != (Math.round(newLocation.latitude * 1e6) + 0.000000)) &&
            ((Math.round(oldLocation.latitude * 1e6) + 0.000001) != (Math.round(newLocation.latitude * 1e6) + 0.000001)) &&
            ((Math.round(oldLocation.latitude * 1e6) + 0.000001) != (Math.round(newLocation.latitude * 1e6) - 0.000001)) &&
            ((Math.round(oldLocation.latitude * 1e6) - 0.000001) != (Math.round(newLocation.latitude * 1e6) + 0.000000)) &&
            ((Math.round(oldLocation.latitude * 1e6) - 0.000001) != (Math.round(newLocation.latitude * 1e6) + 0.000001)) &&
            ((Math.round(oldLocation.latitude * 1e6) - 0.000001) != (Math.round(newLocation.latitude * 1e6) - 0.000001))
}