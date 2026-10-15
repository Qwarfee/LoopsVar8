package com.example.loopsvar8

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loopsvar8.ui.theme.LoopsVar8Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoopsVar8Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SumScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun SumScreen(modifier: Modifier = Modifier) {
    var input by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "a =", fontSize = 20.sp)
            Spacer(Modifier.width(12.dp))
            OutlinedTextField(
                value = input,
                onValueChange = { input = it },
                label = { Text("Введите число a") },
                singleLine = true,
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(Modifier.height(24.dp))
        Button(onClick = { result = input }) {
            Text("Вычислить")
        }
        Spacer(Modifier.height(24.dp))
        Text(text = result, fontSize = 20.sp, textAlign = TextAlign.Center)
    }
}

@Preview(showBackground = true)
@Composable
fun SumScreenPreview() {
    SumScreen()
}


const val MAX_A = 7.0

// Ищет первое из чисел 1 + 1/2, 1 + 1/2 + 1/3, ..., которое больше a.
// Возвращает пару: (номер последнего слагаемого n, сумма)
fun findFirstGreater(a: Double): Pair<Int, Double> {
    var sum = 1.0
    var n = 1
    while (true) {
        n++
        sum += 1.0 / n
        if (sum > a) break   // нашли — выходим из цикла
    }
    return Pair(n, sum)
}