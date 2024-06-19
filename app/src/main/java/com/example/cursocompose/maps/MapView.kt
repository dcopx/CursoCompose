package com.example.cursocompose.maps

import android.content.Context
import android.location.Location
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import com.example.cursocompose.drawPolygon.CustomBotomDrawer
import com.example.cursocompose.drawPolygon.CustomMarker2
import com.example.cursocompose.drawPolygon.MarkerObj2

import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polygon
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapView(
    location: Location,
    onMapClick: (position: LatLng) -> Unit,
    onMarkerClick: @Composable () -> Unit
){
    GoogleMap(
        cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(LatLng(location.latitude, location.longitude), 19f)
        },
        properties = MapProperties(isMyLocationEnabled = true),
        onMapClick = onMapClick
    ){ onMarkerClick() }
}
