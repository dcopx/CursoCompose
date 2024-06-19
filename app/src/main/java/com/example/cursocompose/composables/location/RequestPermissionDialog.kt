package com.example.cursocompose.composables.location

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RequestPermissionDialog(
    permissionMessage: String,
    permissionStatus: Int,
    context: Context
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Card(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .padding(20.dp)
                .align(Alignment.Center),
        ) {
            Column(
                Modifier.padding(16.dp)
            ) {
                Text(text = permissionMessage)
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier.align(Alignment.End),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Button(onClick = {
                        try {
                            context.startActivity(
                                Intent(
                                    Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                    Uri.parse("package:" + "com.example.cursocompose")
                                )
                            )
                        } catch (e: Exception) {
                            Log.e("NoPermissionDialog", "e:: $e")
                        }
                    }) {
                        Text(text = if (permissionStatus == 2) "Go to settings" else "Allow")
                    }

                }
            }
        }
    }
}
