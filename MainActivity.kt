package com.example.formprofilmahasiswa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.formprofilmahasiswa.ui.theme.FormProfilMahasiswaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FormProfilMahasiswaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FormProfil()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormProfil() {
    // 1. STATE MANAGEMENT
    var namaInput by remember { mutableStateOf("") }
    var nimInput by remember { mutableStateOf("") }
    var prodiInput by remember { mutableStateOf("") }

    // State yang disimpan setelah tombol diklik
    var namaSubmitted by remember { mutableStateOf("") }
    var nimSubmitted by remember { mutableStateOf("") }
    var prodiSubmitted by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(top = 32.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Form Profil Mahasiswa",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        // 2. INPUT FORM
        OutlinedTextField(
            value = namaInput,
            onValueChange = { namaInput = it },
            label = { Text("Nama Lengkap") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = nimInput,
            onValueChange = { nimInput = it },
            label = { Text("NIM") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = prodiInput,
            onValueChange = { prodiInput = it },
            label = { Text("Program Studi") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        // 3. EVENT (Tombol Simpan)
        Button(
            onClick = {
                // Mengubah State hasil simpan
                namaSubmitted = namaInput
                nimSubmitted = nimInput
                prodiSubmitted = prodiInput
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Simpan Profil")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 4. HASIL PROFIL (Recomposition otomatis berdasarkan State)
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Hasil Profil Mahasiswa",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                HorizontalDivider()
                Text(text = "Nama  : ${if (namaSubmitted.isEmpty()) "-" else namaSubmitted}")
                Text(text = "NIM   : ${if (nimSubmitted.isEmpty()) "-" else nimSubmitted}")
                Text(text = "Prodi : ${if (prodiSubmitted.isEmpty()) "-" else prodiSubmitted}")
            }
        }
    }
}