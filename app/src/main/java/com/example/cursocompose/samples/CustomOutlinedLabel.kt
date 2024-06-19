package com.pe.cajacusco.movil.utils

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun CustomOutlinedLabel(
    titulo: String,
    contenido: String,
    bold: Boolean,
    width: Double) {

    OutlinedTextField(
        modifier = Modifier
            .width(width.dp)
            .padding(vertical = 3.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(backgroundColor = AppColors().white),
        singleLine = true,
        enabled = false,
        value = contenido,
        textStyle = TextStyle(fontWeight = if (bold) FontWeight.Bold else FontWeight.Light),
        label = { Text(titulo, style = TextStyle(fontWeight = FontWeight.Bold)) },
        onValueChange = {}
    )
}