package com.pe.cajacusco.movil.utils

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CustomFilledButton(
    texto: String,
    habilitado: Boolean,
    onClick: () -> Unit
){
    Button(
        modifier = Modifier.padding(vertical = 2.dp),
        enabled = habilitado,
        colors = ButtonDefaults.buttonColors(backgroundColor = AppColors().primaryColor),
        onClick = onClick)
    {
        Text(texto, color = AppColors().white)
    }
}

