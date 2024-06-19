package com.pe.cajacusco.movil.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun CustomOutlinedTextField(
    label: String,
    contenido: String,
    placeholder: String? = "",
    longCaracteres: Int? = 5000,
    habilitado: Boolean,
    tipoTeclado: Int,
    bold: Boolean,
    with: Double,
    isValid: Boolean? = false,
    onValueChange: (String) -> Unit
) {
    var text by remember { mutableStateOf(contenido) }

    val teclado = mapOf(
        0 to KeyboardType.Text,
        1 to KeyboardType.Number,
        2 to KeyboardType.Decimal,
        3 to KeyboardType.Email,
        4 to KeyboardType.Password,
        5 to KeyboardType.Phone
    )

    val regex = mapOf(
        0 to Regex("[^+<>?={}|!\$%^´*°\"]"),
        1 to Regex("[0-9]"),
        2 to Regex("[0-9.]*"),
        3 to Regex("[^+<>?={}|!\$%^´*°\"]"),
        4 to Regex("[*]"),
        5 to Regex("[0-9]")
    )


    Box(modifier = Modifier.width(with.dp)) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().padding(vertical = 3.dp),
            singleLine = true,
            maxLines = 1,
            keyboardOptions = KeyboardOptions(keyboardType = teclado[tipoTeclado] ?: KeyboardType.Text),
            enabled = habilitado,
            label = {Text(label, style = TextStyle(fontWeight = FontWeight.Bold))},
            value = text,
            placeholder = { Text(placeholder!!, overflow = TextOverflow.Ellipsis)},
            textStyle = TextStyle(fontWeight = if (bold) FontWeight.Bold else FontWeight.Light),
            isError = isValid!!,
            onValueChange = { newValue ->
                if (newValue.length <= (longCaracteres ?: 5000)){
                    val texto = newValue.filter { regex[tipoTeclado]!!.matches(it.toString()) }
                    text = if (texto != newValue) texto else newValue
                }
            }
        )
    }

    onValueChange(text)
}
