package com.example.thuchanh3_calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorApp() {

    var textSoA by remember { mutableStateOf("") }
    var textSoB by remember { mutableStateOf("") }
    var ketQua by remember { mutableStateOf("") }

    val context = LocalContext.current

    // Hàm xử lý tính toán logic
    fun tinhToan(phepTinh: String) {
        val soA = textSoA.toDoubleOrNull()
        val soB = textSoB.toDoubleOrNull()

        if (soA == null || soB == null) {
            Toast.makeText(context, "Vui lòng nhập đủ 2 số", Toast.LENGTH_SHORT).show()
            return
        }

        val kq = when (phepTinh) {
            "+" -> soA + soB
            "-" -> soA - soB
            "*" -> soA * soB
            "/" -> if (soB != 0.0) soA / soB else "Lỗi chia 0"
            else -> 0.0
        }


        ketQua = kq.toString()
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(40.dp))


        Text(
            text = "Thực hành 03",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(30.dp))


        OutlinedTextField(
            value = textSoA,
            onValueChange = { textSoA = it },
            label = { Text("Nhập số A") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            NutBam(text = "+", color = Color(0xFFF44336)) { tinhToan("+") } // Đỏ
            NutBam(text = "-", color = Color(0xFFFF9800)) { tinhToan("-") } // Cam
            NutBam(text = "*", color = Color(0xFF3F51B5)) { tinhToan("*") } // Xanh
            NutBam(text = "/", color = Color(0xFF000000)) { tinhToan("/") } // Đen
        }

        Spacer(modifier = Modifier.height(20.dp))


        OutlinedTextField(
            value = textSoB,
            onValueChange = { textSoB = it },
            label = { Text("Nhập số B") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(30.dp))


        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Kết quả: ",
                fontSize = 18.sp
            )
            Text(
                text = ketQua,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Composable
fun NutBam(text: String, color: Color, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = color),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.size(width = 70.dp, height = 50.dp),
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(text = text, fontSize = 24.sp, fontWeight = FontWeight.Bold)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewApp() {
    CalculatorApp()
}