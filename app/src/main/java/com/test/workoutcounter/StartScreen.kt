package com.test.workoutcounter

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.test.workoutcounter.Navigating.Screen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartScreen(
    navController: NavController
) {

    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var workoutName by remember { mutableStateOf("Standard") }
        var numReps by remember { mutableStateOf("10") }

    // Title
        Text(text = "Workout Tracking Application")
        Spacer(modifier = Modifier.height(80.dp))

    // Workout name text box
        OutlinedTextField(
            value = workoutName,
            onValueChange = { workoutName = it },
            textStyle = TextStyle(
                textAlign = TextAlign.Left
            ),
            label = {
                Text(text = "Enter Workout Name")
            },
            placeholder = {
                Text(text = "Name")
            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.List, contentDescription = "WNIcon" )
            },
            isError = false,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )

        Spacer(modifier = Modifier.height(32.dp))


    // Number of reps text box
        OutlinedTextField(
            value = numReps,
            onValueChange = { numReps = it },
            textStyle = TextStyle(
                textAlign = TextAlign.Left
            ),
            label = {
                Text(text = "Number Of Reps Per Set")
            },
            placeholder = {
                Text(text = "Reps Per Set")
            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Star, contentDescription = "NRIcon" )
            },
            isError = false,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {

                }
            )
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                if(workoutName == "" || numReps == ""){
                    Toast.makeText(context, "Please Enter Values", Toast.LENGTH_LONG).show()
                }else {
                    navController.navigate(Screen.NewWorkout.withArgs(workoutName, numReps))
                }
            }
        ) {
            Text(text = "Start Workout")
        }
    }
}


