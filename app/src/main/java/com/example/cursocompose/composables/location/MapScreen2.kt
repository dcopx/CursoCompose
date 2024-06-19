package com.example.cursocompose.composables.location

import android.location.Location
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
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
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapScreen2() {
    val context = LocalContext.current
    var location: Location? by remember { mutableStateOf(null) }
    var textDialog: String by remember { mutableStateOf("") }
    var permissionStatus by remember { mutableStateOf(0) }

    locationRequest(context){ newLocation, messageDialog, permission ->
        location = newLocation
        textDialog = messageDialog
        permissionStatus = permission
    }

    if (location != null) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(LatLng(location?.latitude ?: 0.0, location?.longitude ?: 0.0), 19f)
            },
            properties = MapProperties(isMyLocationEnabled = true),
        )
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
