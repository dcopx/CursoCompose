package com.example.cursocompose.composables.location

import android.Manifest
import android.app.Activity
import android.content.Context
import android.location.Location
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.example.cursocompose.composables.location.LocationUtils.createLocationRequest
import com.example.cursocompose.composables.location.LocationUtils.fetchLastLocation
import com.example.cursocompose.drawPolygon.MarkerObj2
import com.example.cursocompose.maps.loadMarkers
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.MarkerState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun locationRequest(context: Context, onLocationChange: (newLocation: Location?, messageDialog: String, permissionStatus: Int) -> Unit){

    var location: Location? by remember { mutableStateOf(null) }
    var textDialog: String by remember { mutableStateOf("") }
    var permissionStatus by remember { mutableStateOf(0) }

    val locationPermissionsState = rememberMultiplePermissionsState(
        listOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
        )
    )

    val fusedLocationProvider = remember { LocationServices.getFusedLocationProviderClient(context) }

    val locationListenerCallback = remember {
        object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                Log.d("onLocationResult", "locationResult.latitude: ${locationResult.lastLocation?.latitude}" )
                location = locationResult.lastLocation
                onLocationChange(location, textDialog, permissionStatus)
            }
        }
    }

    val settingsLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = {
            when (it.resultCode) {
                Activity.RESULT_OK -> {
                    context.fetchLastLocation(
                        fusedLocationClient = fusedLocationProvider,
                        settingsLauncher = null,
                        location = {
                            Log.d("settingsLauncher", "location: ${it.latitude}")
                            if (location == null && location != it) {
                                location = it
                                onLocationChange(location, textDialog, permissionStatus)
                            }
                        },
                        locationCallback = locationListenerCallback
                    )
                }
                Activity.RESULT_CANCELED -> {
                    Toast.makeText(context, "Activity.RESULT_CANCELED", Toast.LENGTH_LONG).show()
                }
            }
        }
    )

    LaunchedEffect(
        key1 = locationPermissionsState.revokedPermissions.size,
        key2 = locationPermissionsState.shouldShowRationale,
        block = {
            permissionRequest(
                locationPermissionsState,
                context,
                settingsLauncher,
                fusedLocationProvider,
                locationListenerCallback,
                permissionResult = { code, text ->
                    permissionStatus = code
                    textDialog = text
                    onLocationChange(location, textDialog, permissionStatus)
                },
            )
        }
    )

    DisposableEffect(
    key1 = true
    ) {
        onDispose {
            fusedLocationProvider.removeLocationUpdates(locationListenerCallback)
        }
    }


}



@OptIn(ExperimentalPermissionsApi::class)
fun permissionRequest(
    locationPermissionsState: MultiplePermissionsState,
    context: Context,
    settingsLauncher: ManagedActivityResultLauncher<IntentSenderRequest, ActivityResult>,
    fusedLocationProviderClient: FusedLocationProviderClient,
    locationCallback: LocationCallback,
    permissionResult: (Int, String) -> Unit
) {
    when {
        locationPermissionsState.revokedPermissions.size <= 1 -> {
            // Has permission at least one permission [coarse or fine]
            context.createLocationRequest(
                settingsLauncher = settingsLauncher,
                fusedLocationClient = fusedLocationProviderClient,
                locationCallback = locationCallback
            )
            Log.d("LaunchedEffect", "revokedPermissions.size <= 1")
            permissionResult(0, "OK")
        }
        locationPermissionsState.shouldShowRationale -> {
            permissionResult(2, "Go to settings for permission")
            Log.d("LaunchedEffect", "shouldShowRationale")
        }
        locationPermissionsState.revokedPermissions.size == 2 -> {
            locationPermissionsState.launchMultiplePermissionRequest()
            permissionResult(1, "Requesting permission")
            Log.d("LaunchedEffect", "revokedPermissions.size == 2")
        }
        else -> {
            permissionResult(2, "This app requires location permission")
            Log.d("LaunchedEffect", "else")
        }
    }
}

