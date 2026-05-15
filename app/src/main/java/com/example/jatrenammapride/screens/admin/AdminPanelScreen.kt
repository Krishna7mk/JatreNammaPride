package com.example.jatrenammapride.screens.admin

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jatrenammapride.firebase.FirebaseModule
import com.example.jatrenammapride.model.Event
import com.example.jatrenammapride.navigation.Screen
import com.example.jatrenammapride.ui.theme.FestivalOrange
import com.example.jatrenammapride.ui.theme.LightCream
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminPanelScreen(
    navController: NavController
) {

    val context = LocalContext.current

    var title by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }

    val eventList = remember {
        mutableStateListOf<Event>()
    }

    DisposableEffect(Unit) {

        val listener = FirebaseModule.firestore
            .collection("schedule")
            .addSnapshotListener { value, _ ->

                eventList.clear()

                value?.documents?.forEach {

                    val event =
                        it.toObject(Event::class.java)

                    if (event != null) {
                        eventList.add(event)
                    }
                }
            }

        onDispose { listener.remove() }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightCream)
            .padding(16.dp),

        verticalArrangement = Arrangement.Top
    ) {

        CenterAlignedTopAppBar(

            title = {
                Text("Admin Panel")
            },

            navigationIcon = {

                IconButton(
                    onClick = {
                        navController.popBackStack()
                    }
                ) {

                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = title,
            onValueChange = {
                title = it
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("Event Title")
            }
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = time,
            onValueChange = {
                time = it
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("Event Time")
            }
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = location,
            onValueChange = {
                location = it
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("Event Location")
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(

            onClick = {

                if (
                    title.isEmpty() ||
                    time.isEmpty() ||
                    location.isEmpty()
                ) {

                    Toast.makeText(
                        context,
                        "Fill all fields",
                        Toast.LENGTH_SHORT
                    ).show()

                    return@Button
                }

                val event = Event(
                    id = UUID.randomUUID().toString(),
                    title = title,
                    time = time,
                    location = location,
                    description = "",
                    isLive = false
                )

                FirebaseModule.firestore
                    .collection("schedule")
                    .document(event.id)
                    .set(event)

                Toast.makeText(
                    context,
                    "Event Added",
                    Toast.LENGTH_SHORT
                ).show()

                title = ""
                time = ""
                location = ""
            },

            modifier = Modifier.fillMaxWidth(),

            colors = ButtonDefaults.buttonColors(
                containerColor = FestivalOrange
            )
        ) {

            Text("Add Event")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(

            onClick = {
                navController.navigate(Screen.AdminLostFound.route)
            },

            modifier = Modifier.fillMaxWidth(),

            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF6A1B9A)
            )
        ) {

            Text("Manage Lost & Found")
        }

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn(

            modifier = Modifier.weight(1f)

        ) {

            items(eventList) { event ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),

                    shape = RoundedCornerShape(16.dp),

                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(14.dp)
                    ) {

                        Text(
                            text = event.title,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )

                        Text("Time: ${event.time}")

                        Text("Location: ${event.location}")

                        Spacer(modifier = Modifier.height(10.dp))

                        Button(

                            onClick = {

                                FirebaseModule.firestore
                                    .collection("schedule")
                                    .document(event.id)
                                    .delete()
                            },

                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Red
                            )
                        ) {

                            Text("Delete")
                        }
                    }
                }
            }
        }
    }
}