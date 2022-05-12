package com.example.notejetpack

import android.os.Build
import androidx.lifecycle.viewmodel.compose.viewModel

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notejetpack.model.Note
import com.example.notejetpack.ui.theme.NoteJetPackTheme
import com.example.notejetpack.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@AndroidEntryPoint

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                val noteViewModel = viewModel<NoteViewModel>()
                val textTitle = remember {
                    mutableStateOf("")
                }
                val textDescription = remember {
                    mutableStateOf("")
                }
                val listNote = remember {
                    mutableStateListOf<Note>()
                }

                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                    ) {

                        Column(
                            modifier = Modifier.padding(top = 10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            TextField(
                                value = textTitle.value,
                                onValueChange = {
                                    textTitle.value = it
                                },
                                colors = TextFieldDefaults.textFieldColors(
                                    backgroundColor = MaterialTheme.colors.surface
                                ),
                                label = { Text("Title") },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(60.dp)
                                    .padding(start = 30.dp, end = 30.dp, bottom = 5.dp),
                                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                            )
                            TextField(
                                value = textDescription.value,
                                onValueChange = {
                                    textDescription.value = it
                                },
                                colors = TextFieldDefaults.textFieldColors(
                                    backgroundColor = MaterialTheme.colors.surface
                                ),
                                label = { Text("Add a note") },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(60.dp)
                                    .padding(start = 30.dp, end = 30.dp, bottom = 10.dp),
                                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                            )
                            Button(
                                onClick = {
                                    if (textTitle.value != "" && textDescription.value != "")
                                    {
                                        val current = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                            LocalDateTime.now()
                                        } else {
                                            TODO("VERSION.SDK_INT < O")
                                        }
                                        val formatter = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
                                        } else {
                                            TODO("VERSION.SDK_INT < O")
                                        }

                                        val note = Note(title = textTitle.value,
                                            description = textDescription.value,
                                            entryDate = current.format(formatter)
                                        )
                                        textTitle.value = ""
                                        textDescription.value = ""
                                        noteViewModel.addNote(note)
                                    }
                                },
                                modifier = Modifier
                                    .clip(CircleShape)
                            ) {
                                Text(
                                    text = "Save",
                                    fontSize = 16.sp,
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                                )
                            }
                        }

                        LazyColumn(
                            modifier = Modifier.padding(top = 10.dp)
                        ) {
                            listNote.convertList(noteViewModel.noteList.value.reversed())

                            items(listNote) {
                                    item ->
                                Card(
                                    modifier = Modifier
                                        .padding(horizontal = 20.dp, vertical = 5.dp)
                                        .fillMaxWidth()
                                        .height(80.dp)
                                        .clip(RoundedCornerShape(0.dp, 35.dp, 0.dp, 35.dp)),
                                    backgroundColor = Color(0xFF38D3EE),
                                    elevation = 0.dp,
                                ) {
                                    Row(
                                        modifier = Modifier.padding(5.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        Column(
                                            modifier = Modifier
                                                .padding(5.dp)
                                                .width(270.dp)
                                        ) {
                                            Text(
                                                text = item.title,
                                                fontWeight = FontWeight.Bold
                                            )
                                            Text(
                                                text = item.description,
                                                fontSize = 18.sp
                                            )
                                            Text(
                                                text = item.entryDate,
                                                fontSize = 13.sp
                                            )
                                        }

                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

}






fun <T> SnapshotStateList<T>.convertList(newList: List<T>){
    clear()
    addAll(newList)
}

