package com.example.cursocompose.composables.location
//
//import android.Manifest
//import android.app.Activity
//import android.content.Context
//import android.location.Location
//import android.util.Log
//import android.widget.Toast
//import androidx.activity.compose.ManagedActivityResultLauncher
//import androidx.activity.compose.rememberLauncherForActivityResult
//import androidx.activity.result.ActivityResult
//import androidx.activity.result.IntentSenderRequest
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.DisposableEffect
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.window.Dialog
//import androidx.compose.ui.window.DialogProperties
//import com.example.cursocompose.location.LocationUtils.createLocationRequest
//import com.example.cursocompose.location.LocationUtils.fetchLastLocation
//import com.google.accompanist.permissions.ExperimentalPermissionsApi
//import com.google.accompanist.permissions.MultiplePermissionsState
//import com.google.accompanist.permissions.rememberMultiplePermissionsState
//import com.google.android.gms.location.FusedLocationProviderClient
//import com.google.android.gms.location.LocationCallback
//import com.google.android.gms.location.LocationResult
//import com.google.android.gms.location.LocationServices
//
//@OptIn(ExperimentalPermissionsApi::class)
//@Composable
//fun getLocation(location: Location?, get: (Location?) -> Unit){
//    val context = LocalContext.current
//
////    var location: Location? by remember { mutableStateOf(null) }
//    var textDialog: String by remember { mutableStateOf("") }
//    var permissionStatus by remember { mutableStateOf(0) }
//
//    val fusedLocationProvider = remember { LocationServices.getFusedLocationProviderClient(context) }
//
//    val locationPermissionsState = rememberMultiplePermissionsState(
//        listOf(
//            Manifest.permission.ACCESS_COARSE_LOCATION,
//            Manifest.permission.ACCESS_FINE_LOCATION,
//        )
//    )
//
//    val locationCallback = remember {
//        object : LocationCallback() {
//            override fun onLocationResult(locationResult: LocationResult) {
//                Log.d(
//                    "onLocationResult",
//                    "locationResult.latitude: ${locationResult.lastLocation?.latitude}"
//                )
////              location = locationResult.lastLocation
//                get(locationResult.lastLocation)
//            }
//        }
//    }
//
//    val settingsLauncher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.StartIntentSenderForResult(),
//        onResult = {
//            when (it.resultCode) {
//                Activity.RESULT_OK -> {
//                    context.fetchLastLocation(
//                        fusedLocationClient = fusedLocationProvider,
//                        settingsLauncher = null,
//                        location = {
//                            Log.d("settingsLauncher", "location: ${it.latitude}")
//                            if (location == null || location.latitude != it.latitude) {
////                                location = it
//                                get(it)
//                            }
//                        },
//                        locationCallback = locationCallback
//                    )
//                }
//                Activity.RESULT_CANCELED -> {
//                    Toast.makeText(context, "Activity.RESULT_CANCELED", Toast.LENGTH_LONG).show()
//                }
//            }
//        }
//    )
//
//    LaunchedEffect(
//        key1 = locationPermissionsState.revokedPermissions.size,
//        key2 = locationPermissionsState.shouldShowRationale,
//        block = {
//            requestLocation(
//                locationPermissionsState,
//                context,
//                settingsLauncher,
//                fusedLocationProvider,
//                locationCallback,
//                locationRequestResult = { code, text ->
//                    permissionStatus = code
//                    textDialog = text
//                })
//        })
//
//    DisposableEffect(
//        key1 = true
//    ) {
//        onDispose {
//            fusedLocationProvider.removeLocationUpdates(locationCallback)
//        }
//    }
//
//    if (textDialog.isNotEmpty()) {
//        Box(
//            modifier = Modifier.fillMaxWidth(),
//            contentAlignment = Alignment.Center
//        ) {
//            Dialog(
//                onDismissRequest = { textDialog = "" },
//                properties = DialogProperties(
//                    dismissOnBackPress = false,
//                    dismissOnClickOutside = false
//                )
//            ) {
//                RequestPermissionDialog(
//                    permissionMessage = textDialog,
//                    permissionStatus = -1,
//                    context = context,
//                    requestPermission = {
//                        locationPermissionsState.launchMultiplePermissionRequest()
//                        textDialog = ""
//                    }
//                )
//            }
//        }
//    }
//}
//
//
//
//@OptIn(ExperimentalPermissionsApi::class)
// fun requestLocation(
//    locationPermissionsState: MultiplePermissionsState,
//    context: Context,
//    settingsLauncher: ManagedActivityResultLauncher<IntentSenderRequest, ActivityResult>,
//    fusedLocationProviderClient: FusedLocationProviderClient,
//    locationCallback: LocationCallback,
////    openDialog: (String) -> Unit,
//    locationRequestResult: (Int, String) -> Unit
//) {
//    when {
//        locationPermissionsState.revokedPermissions.size <= 1 -> {
//            // Has permission at least one permission [coarse or fine]
//            context.createLocationRequest(
//                settingsLauncher = settingsLauncher,
//                fusedLocationClient = fusedLocationProviderClient,
//                locationCallback = locationCallback
//            )
//            Log.d("LaunchedEffect", "revokedPermissions.size <= 1")
//            locationRequestResult(0, "OK")
//        }
//        locationPermissionsState.shouldShowRationale -> {
////            openDialog("Should show rationale")
//            locationRequestResult(2, "Go to settings for permission")
//            Log.d("LaunchedEffect", "shouldShowRationale")
//        }
//        locationPermissionsState.revokedPermissions.size == 2 -> {
//            locationPermissionsState.launchMultiplePermissionRequest()
//            locationRequestResult(1, "Requesting permission")
//            Log.d("LaunchedEffect", "revokedPermissions.size == 2")
//        }
//        else -> {
////            openDialog("This app requires location permission")
//            locationRequestResult(2, "This app requires location permission")
//            Log.d("LaunchedEffect", "else")
//        }
//    }
//}