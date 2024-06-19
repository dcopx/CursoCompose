package com.example.cursocompose.drawPolygon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomBotomDrawer(onDismiss: () -> Unit) {
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    ModalBottomSheet(
        onDismissRequest = {
            showBottomSheet = false
            onDismiss()
        },
        sheetState = sheetState,
        containerColor = Color.Gray
    ) {
        Contenido()
    }
}

@Preview
@Composable
fun Contenido(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(horizontal = 10.dp)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier
                    .size(40.dp)
                    .background(Color.Red)
                    .clip(RoundedCornerShape(5.dp))
                )
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text("sdfg g sdfg sdf", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    Text("23423423", color = Color.White)
                }
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 5.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier
                    .size(25.dp)
                    .background(Color.Red))
                Spacer(modifier = Modifier.padding(horizontal = 10.dp))
                Text("Iniciar Ruta", color = Color.White)

            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 5.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier
                    .size(25.dp)
                    .background(Color.Red))
                Spacer(modifier = Modifier.padding(horizontal = 10.dp))
                Text("Ver posición cliente", color = Color.White)
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 5.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier
                    .size(25.dp)
                    .background(Color.Red))
                Spacer(modifier = Modifier.padding(horizontal = 10.dp))
                Text("Gestionar??", color = Color.White)
            }
        }
    }
}
