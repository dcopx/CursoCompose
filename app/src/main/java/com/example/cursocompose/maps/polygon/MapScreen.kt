package com.example.cursocompose.drawPolygon

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polygon
import com.google.maps.android.compose.rememberCameraPositionState

//
//@Composable
//fun MapScreen(opcion: Int) {
//
//    var markers by remember { mutableStateOf(listOf(
//        MarkerObj(
//            state = MarkerState(LatLng(40.9972, 29.1007)),
//            title = "Aea1"
//        ),
//        MarkerObj(
//            state = MarkerState(LatLng(40.9973, 29.1008)),
//            title = "Aea2"
//        ),
//        MarkerObj(
//            state = MarkerState(LatLng(40.9974, 29.1009)),
//            title = "Aea3"
//        ),
//        MarkerObj(
//            state = MarkerState(LatLng(40.9975, 29.1010)),
//            title = "Aea4"
//        )
//    )) }
//
//    val location = LatLng(40.9971, 29.1007)
//    val cameraPositionState = rememberCameraPositionState {
//        position = CameraPosition.fromLatLngZoom(location, 20f)
//    }
//
//    var showDrawer by remember { mutableStateOf(false) }
//
//    GoogleMap(
//        modifier = Modifier.fillMaxSize(),
//        cameraPositionState = cameraPositionState,
//        onMapClick = { position ->
//            markers = markers.toMutableList().apply {
//                add(MarkerObj(MarkerState(position), ""))
//            }
//        }
//    ){
//        markers.forEach { marker ->
//            CustomMarker(
//                marker = marker,
//                onClick = {
//                    if (opcion == 1)
//                        markers = markers.toMutableList().apply { remove(marker) }
//                    if (opcion == 2)
//                        showDrawer = true
//                }
//            )
//        }
//        if (markers.size > 2){
//            val points = mutableListOf<LatLng>()
//            markers.forEach {markerObj ->
//                points.add(markerObj.state.position)
//            }
//            Polygon(
//                points = points,
//                fillColor = Color.Cyan,
//                strokeColor = Color.Cyan)
//        }
//    }
//
//    if (showDrawer){
//        CustomBotomDrawer { showDrawer = false }
//    }
//}
