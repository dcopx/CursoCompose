package com.pe.cajacusco.movil.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun CustomDivider(texto: String){
    Box(modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp),
        contentAlignment = Alignment.Center) {
        Divider(Modifier.padding(vertical = 5.dp), AppColors().primaryColor)
        Text(texto,
            modifier = Modifier
                .background(AppColors().white)
                .padding(horizontal = 5.dp),
            overflow = TextOverflow.Ellipsis)

    }
}