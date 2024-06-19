package com.pe.cajacusco.movil.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize

@Composable
fun CustomOutlinedDropdown(
    label: String,
    lista: Map<String, String>,
    contenido: String,
    onItemSelected: (index: String, item: String) -> Unit,
    habilitado: Boolean,
    width: Double
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("") }
    var dropDownSize by remember { mutableStateOf(Size.Zero) }
    val icon = if (expanded) Icons.Filled.ArrowDropUp else Icons.Filled.ArrowDropDown
    val configuracion = ConfiguracionLocal()
    val focusManager = configuracion.focus
    val screenHeight50 by remember {
        val screenHeight = configuracion.height
        mutableStateOf(screenHeight / 2)
    }
    val sizeOfOneItem by remember { mutableStateOf(50.dp) }
    val height by remember(lista.size) {
        val itemsSize = sizeOfOneItem * lista.size
        mutableStateOf(minOf(itemsSize, screenHeight50))
    }

    Column(modifier = Modifier.width(width.dp).padding(vertical = 3.dp)) {
        Box(modifier = Modifier.clickable(enabled = habilitado) {}) {
            OutlinedTextField(
                value = contenido,
                label = { Text(label, style = TextStyle(fontWeight = FontWeight.Bold)) },
                readOnly = true,
                singleLine = true,
                maxLines = 1,
                onValueChange = {},
                trailingIcon = { Icon(icon, null) },
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged { expanded = it.isFocused }
                    .onGloballyPositioned { coordinates ->
                        dropDownSize = coordinates.size.toSize()
                    }
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
                focusManager.clearFocus()
            }
        ) {
            LazyColumn(modifier = Modifier.width(width.dp).height(height)) {
                items(count = lista.size) { index ->
                    val key = lista.keys.toList()[index]
                    val value = lista.get(key)!!
                    DropdownMenuItem(onClick = {
                        selectedText = value
                        onItemSelected(key, value)
                        expanded = false
                        focusManager.clearFocus()
                    }) {
                        Text(text = value)
                    }
                    Divider(modifier = Modifier.padding(horizontal = 5.dp))
                }
            }
        }
    }
}
