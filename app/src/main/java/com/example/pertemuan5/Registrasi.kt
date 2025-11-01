package com.example.pertemuan5

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pertemuan5.ui.theme.Pertemuan5Theme
import java.text.SimpleDateFormat
import java.util.*

private fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationForm(modifier: Modifier = Modifier) {
    // --- State ---
    var showDialog by remember { mutableStateOf(false) }
    var namaLengkap by remember { mutableStateOf("") }
    var kotaAsal by remember { mutableStateOf("") }
    var tanggalLahir by remember { mutableStateOf("") }
    var rt by remember { mutableStateOf("") }
    var rw by remember { mutableStateOf("") }
    var umur by remember { mutableStateOf("") }
    val radioOptions = listOf("Laki-laki", "Perempuan")
    var selectedJenisKelamin by remember { mutableStateOf(radioOptions[0]) }
    var setuju by remember { mutableStateOf(false) }

    val datePickerState = rememberDatePickerState()
    var showDatePicker by remember { mutableStateOf(false) }

    val customTextFieldColors = TextFieldDefaults.colors(
        focusedContainerColor = Color.White,
        unfocusedContainerColor = Color.White,
        disabledContainerColor = Color.White,
        focusedIndicatorColor = Color(0xFF8BC34A),
        unfocusedIndicatorColor = Color(0xFFD3D3D3),
        cursorColor = Color(0xFF8BC34A)
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFFFF9C4))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .background(Color.White, shape = RoundedCornerShape(16.dp))
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text("Form Registrasi", fontSize = 24.sp, modifier = Modifier.padding(bottom = 16.dp))
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = namaLengkap,
                onValueChange = { namaLengkap = it },
                label = { Text("Nama Lengkap") },
                modifier = Modifier.fillMaxWidth(),
                colors = customTextFieldColors,
                shape = RoundedCornerShape(8.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = kotaAsal,
                onValueChange = { kotaAsal = it },
                label = { Text("Kota Asal") },
                modifier = Modifier.fillMaxWidth(),
                colors = customTextFieldColors,
                shape = RoundedCornerShape(8.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier.weight(0.5f)) {
                    OutlinedTextField(
                        value = tanggalLahir,
                        onValueChange = {},
                        label = { Text("Tanggal Lahir") },
                        modifier = Modifier.fillMaxWidth(),
                        readOnly = true,
                        colors = customTextFieldColors,
                        shape = RoundedCornerShape(8.dp)
                    )
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .clickable { showDatePicker = true }
                    )
                }

                OutlinedTextField(
                    value = rt,
                    onValueChange = { rt = it },
                    label = { Text("RT") },
                    modifier = Modifier.weight(0.25f),
                    colors = customTextFieldColors,
                    shape = RoundedCornerShape(8.dp)
                )

                OutlinedTextField(
                    value = rw,
                    onValueChange = { rw = it },
                    label = { Text("RW") },
                    modifier = Modifier.weight(0.25f),
                    colors = customTextFieldColors,
                    shape = RoundedCornerShape(8.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = umur,
                onValueChange = { umur = it },
                label = { Text("Umur") },
                modifier = Modifier.fillMaxWidth(),
                colors = customTextFieldColors,
                shape = RoundedCornerShape(8.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            // --- Jenis Kelamin ---
            Text("Jenis Kelamin:", modifier = Modifier.fillMaxWidth())
            Row(modifier = Modifier.fillMaxWidth()) {
                radioOptions.forEach { text ->
                    Row(
                        Modifier
                            .selectable(
                                selected = (text == selectedJenisKelamin),
                                onClick = { selectedJenisKelamin = text }
                            )
                            .padding(end = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (text == selectedJenisKelamin),
                            onClick = null
                        )
                        Text(text = text)
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = setuju,
                    onCheckedChange = { setuju = it }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Saya setuju dengan syarat dan ketentuan")
            }
            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { showDialog = true },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8BC34A)),
                shape = RoundedCornerShape(24.dp)
            ) {
                Text("Submit", color = Color.White)
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = "Registrasi Berhasil") },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text("Nama: $namaLengkap")
                    Text("Kota Asal: $kotaAsal")
                    Text("Tanggal Lahir: $tanggalLahir")
                    Text("Alamat: RT $rt / RW $rw")
                    Text("Umur: $umur")
                    Text("Jenis Kelamin: $selectedJenisKelamin")
                    Text("Setuju Syarat: ${if (setuju) "Ya" else "Tidak"}")
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    showDialog = false
                    // Reset form
                    namaLengkap = ""
                    kotaAsal = ""
                    tanggalLahir = ""
                    rt = ""
                    rw = ""
                    umur = ""
                    selectedJenisKelamin = radioOptions[0]
                    setuju = false
                }) {
                    Text("OK")
                }
            }
        )
    }

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    showDatePicker = false
                    datePickerState.selectedDateMillis?.let { millis ->
                        tanggalLahir = convertMillisToDate(millis)
                    }
                }) { Text("OK") }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegistrationFormPreview() {
    Pertemuan5Theme {
        RegistrationForm()
    }
}
