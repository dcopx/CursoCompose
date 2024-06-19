//package com.example.cursocompose.drawPolygon
//
//import androidx.compose.runtime.Composable
//import com.google.maps.android.compose.Marker
//import com.google.maps.android.compose.MarkerState
//
//class MarkerObj(
//    var state: MarkerState,
//    var title: String
//)
//
//@Composable
//fun CustomMarker(marker: MarkerObj, onClick: () -> Unit){
//    Marker(
//        state = marker.state,
//        title = marker.title,
//        onClick = {
//            onClick()
//            true
//        }
//    )
//}