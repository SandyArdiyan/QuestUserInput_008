package com.example.pertemuan5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.pertemuan5.ui.theme.Pertemuan5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Pertemuan5Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    // [INI BAGIAN UTAMA]
                    // Memanggil RegistrationForm saat aplikasi dibuka
                    // dan memberinya padding dari Scaffold
                    RegistrationForm(modifier = Modifier.padding(innerPadding))

                }
            }
        }
    }
}