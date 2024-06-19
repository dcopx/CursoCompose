package com.example.cursocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.cursocompose.maps.MapScreen
//import com.example.cursocompose.maps.MapScreen
import com.example.cursocompose.ui.theme.CursoComposeTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi

/*
Estado permiso:
concedido = 0
concedido parcialmente = 1
totalmente denegado = 2
 */
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            CursoComposeTheme(darkTheme = true, dynamicColor = false) {

                MapScreen()

//
//                val locationPermissionsState = rememberMultiplePermissionsState(
//                    listOf(
//                        Manifest.permission.ACCESS_COARSE_LOCATION,
//                        Manifest.permission.ACCESS_FINE_LOCATION,
//                    )
//                )
//                val context = LocalContext.current
//                val fusedLocationProvider = remember { LocationServices.getFusedLocationProviderClient(context) }
//
//                val locationListenerCallback = remember {
//                    object : LocationCallback() {
//                        override fun onLocationResult(locationResult: LocationResult) {
//                            Log.d("onLocationResult", "locationResult.latitude: ${locationResult.lastLocation?.latitude}" )
//                            location = locationResult.lastLocation
////                        get(locationResult.lastLocation)
//                        }
//                    }
//                }
//
//                val settingsLauncher = rememberLauncherForActivityResult(
//                    contract = ActivityResultContracts.StartIntentSenderForResult(),
//                    onResult = {
//                        when (it.resultCode) {
//                            RESULT_OK -> {
//                                context.fetchLastLocation(
//                                    fusedLocationClient = fusedLocationProvider,
//                                    settingsLauncher = null,
//                                    location = {
//                                        Log.d("settingsLauncher", "location: ${it.latitude}")
//                                        if (location == null && location != it) {
//                                            location = it
//                                        }
//                                    },
//                                    locationCallback = locationListenerCallback
//                                )
//                            }
//                            RESULT_CANCELED -> {
//                                Toast.makeText(context, "Activity.RESULT_CANCELED", Toast.LENGTH_LONG).show()
//                            }
//                        }
//                    }
//                )
//
//                LaunchedEffect(
//                    key1 = locationPermissionsState.revokedPermissions.size,
//                    key2 = locationPermissionsState.shouldShowRationale,
//                    block = {
//                        requestLocation(
//                            locationPermissionsState,
//                            context,
//                            settingsLauncher,
//                            fusedLocationProvider,
//                            locationListenerCallback,
//                            locationRequestResult = { code, text ->
//                                permissionStatus = code
//                                textDialog = text
//                            },
//
//                        )
//                    }
//                )
//
//                DisposableEffect(
//                    key1 = true
//                ) {
//                    onDispose {
//                        fusedLocationProvider.removeLocationUpdates(locationListenerCallback)
//                    }
//                }




//                Box( modifier = Modifier
//                    .fillMaxSize()
//                    .background(Color.White) ) {
////                    MapScreen(1)
////                    MapScreen(2)
////                    MapScreen2()
////                    fetchLocation(
////                        locationPermissionsState = locationPermissionsState,
////                        context = context,
////                        settingsLauncher = settingsLauncher,
////                        fusedLocationProviderClient = fusedLocationProvider,
////                        locationCallback = locationCallback,
////                        openDialog = {textDialog = it}
////                    )
//
//
//                    if (
//                        locationPermissionsState.revokedPermissions.size == 2
//                        && !locationPermissionsState.shouldShowRationale
//                    ) {
//                        textDialog = "Permiso de ubicación denegado. Habilite en configuración."
//                        permissionStatus = -1
//                        Log.d("LaunchedEffect", "revokedPermissions.size == 2 && shouldShowRationale")
//                    } else {
//                        fetchLocation(
//                            locationPermissionsState,
//                            context,
//                            settingsLauncher,
//                            fusedLocationProvider,
//                            locationListenerCallback,
//                            openDialog = {
//                                textDialog = it
//                            }
//                        )
//                    }
//
//                    if (permissionStatus != 0) {
//                        Dialog(
//                            onDismissRequest = {
//                                textDialog = ""
//                                permissionStatus = 0
//                            },
//                            properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
//                        ) {
//                            RequestPermissionDialog(
//                                permissionMessage = textDialog,
//                                permissionStatus = permissionStatus,
//                                context = context,
//                                requestPermission = {
//                                    locationPermissionsState.launchMultiplePermissionRequest()
//                                    textDialog = ""
//                                    permissionStatus = 0
//                                }
//                            )
//                        }
//                    }
//                }
            }
        }
    }
}
