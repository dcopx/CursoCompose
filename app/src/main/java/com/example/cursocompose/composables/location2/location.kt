package com.example.cursocompose.composables.location2

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.style.TextAlign
import com.google.android.gms.location.FusedLocationProviderClient

@Composable
fun location(){

//    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
//
//    var locationText by remember { mutableStateOf("No location obtained :(") }
//    var showPermissionResultText by remember { mutableStateOf(false) }
//    var permissionResultText by remember { mutableStateOf("Permission Granted...") }
//    RequestLocationPermission(
//        onPermissionGranted = {
//            // Callback when permission is granted
//            showPermissionResultText = true
//            // Attempt to get the last known user location
//            getLastUserLocation(
//                onGetLastLocationSuccess = {
//                    locationText =
//                        "Location using LAST-LOCATION: LATITUDE: ${it.first}, LONGITUDE: ${it.second}"
//                },
//                onGetLastLocationFailed = { exception ->
//                    showPermissionResultText = true
//                    locationText =
//                        exception.localizedMessage ?: "Error Getting Last Location"
//                },
//                onGetLastLocationIsNull = {
//                    // Attempt to get the current user location
//                    getCurrentLocation(
//                        onGetCurrentLocationSuccess = {
//                            locationText =
//                                "Location using CURRENT-LOCATION: LATITUDE: ${it.first}, LONGITUDE: ${it.second}"
//                        },
//                        onGetCurrentLocationFailed = {
//                            showPermissionResultText = true
//                            locationText = it.localizedMessage ?: "Error Getting Current Location"
//                        }
//                    )
//                }
//            )
//        },
//        onPermissionDenied = {
//            // Callback when permission is denied
//            showPermissionResultText = true
//            permissionResultText = "Permission Denied :("
//        },
//        onPermissionsRevoked = {
//            // Callback when permission is revoked
//            showPermissionResultText = true
//            permissionResultText = "Permission Revoked :("
//        }
//    )
//
//    Text(
//        text = "Requesting location permission...",
//        textAlign = TextAlign.Center
//    )
//
//    // Display permission result and location information if available
//    if (showPermissionResultText) {
//        Text(text = permissionResultText, textAlign = TextAlign.Center)
//        Text(text = locationText, textAlign = TextAlign.Center)
//    }
}