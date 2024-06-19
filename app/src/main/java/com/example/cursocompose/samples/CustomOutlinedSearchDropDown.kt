package com.pe.cajacusco.movil.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CustomOutlinedSearchDropdown(
    label: String,
    lista: Map<String, String>,
    contenido : String,
    onItemSelected: (key: String, value: String) -> Unit,
    habilitado: Boolean,
    width: Double
) {
    var selectedOptionText by rememberSaveable { mutableStateOf(contenido) }
    var searchedOption by rememberSaveable { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val icon = if (expanded) Icons.Filled.ArrowDropUp else Icons.Filled.ArrowDropDown
    var filteredItems = mutableListOf<String>()
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    val itemHeights = remember { mutableStateMapOf<Int, Int>() }
    val baseHeight = 530.dp
    val density = LocalDensity.current


    val maxHeight = remember(itemHeights.toMap()) {
        if (itemHeights.keys.toSet() != lista.keys.toSet()) {
            return@remember baseHeight
        }
        val baseHeightInt = with(density) { baseHeight.toPx().toInt() }

        var sum = with(density) { 8.dp.toPx().toInt() } * 2
        for ((_, itemSize) in itemHeights.toSortedMap()) {
            sum += itemSize
            if (sum >= baseHeightInt) {
                return@remember with(density) { (sum - itemSize / 2).toDp() }
            }
        }
        baseHeight
    }

    Column(
        modifier = Modifier.padding(3.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OutlinedTextField(
            modifier = Modifier.width(width.dp).clickable(enabled = habilitado) {},
            label = { Text(label, style = TextStyle(fontWeight = FontWeight.Bold)) },
            value = selectedOptionText,
            readOnly = true,
            onValueChange = { selectedOptionText = it },
            trailingIcon = { Icon(icon, null) },
            interactionSource = remember { MutableInteractionSource() }
                .also { interactionSource ->
                    LaunchedEffect(interactionSource) {
                        keyboardController?.show()
                        interactionSource.interactions.collect {
                            if (it is PressInteraction.Release) {
                                expanded = !expanded
                            }
                        }
                    }
                },
        )
        if (expanded) {
            DropdownMenu(
                modifier = Modifier
                    .fillMaxWidth(0.75f),
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .focusRequester(focusRequester),
                        singleLine = true,
                        maxLines = 1,
                        value = searchedOption,
                        onValueChange = { selectedSport ->
                            searchedOption = selectedSport
                            filteredItems = lista.values.filter {
                                it.contains(
                                    searchedOption,
                                    ignoreCase = true,
                                )
                            }.toMutableList()
                        },
                        leadingIcon = { Icon(imageVector = Icons.Outlined.Search, contentDescription = null) },
                        placeholder = { Text(text = "Buscar $label") },
                    )

                    val filtrados = if (filteredItems.isEmpty()) {
                        lista.values
                    } else {
                        filteredItems
                    }
                    LazyColumn(modifier = Modifier.width(width.dp).height(maxHeight)){
                        items(count = filtrados.size){ index ->
                            var clave = ""
                            var valor = ""
                            DropdownMenuItem(
                                onClick = {
                                    keyboardController?.hide()
                                    selectedOptionText = filtrados.toList()[index]
                                    searchedOption = ""
                                    expanded = false

                                    lista.forEach{ item ->
                                        if (item.value == selectedOptionText){
                                            clave = item.key
                                            valor = item.value
                                        }
                                    }
                                    onItemSelected(clave, valor)
                                },
                                content = {
                                    Text(filtrados.toList()[index])
                                },
                            )
                            Divider(modifier = Modifier.padding(horizontal = 5.dp))
                        }
                    }
                }
            }
        }
    }
}