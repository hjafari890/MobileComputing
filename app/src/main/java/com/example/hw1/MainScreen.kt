package com.example.hw1

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen(onNavigateToHw1: () -> Unit) {
    androidx.compose.material3.Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
        ) {
            androidx.compose.material3.Text(
                text = "Mobile Computing HW2",
                style = MaterialTheme.typography.headlineMedium
            )

            androidx.compose.foundation.layout.Spacer(modifier = Modifier.height(24.dp))

            androidx.compose.material3.Button(onClick = onNavigateToHw1) {
                androidx.compose.material3.Text("Go to HW1 (List Screen)")
            }
        }
    }
}