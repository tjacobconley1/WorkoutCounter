package com.test.workoutcounter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.test.workoutcounter.Navigating.Navigation
import com.test.workoutcounter.ui.theme.WorkoutCounterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorkoutCounterTheme {

                Navigation()

            }
        }
    }
}

@Composable
fun NewWorkout(nameOfWorkout: String?, numRepsPerSet: Int?){

    var setsCompleted by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        var count by remember { mutableStateOf(0) }
        
        Text(text = nameOfWorkout ?: "Standard")
        AnimatedCounter(
            count = count,
            style = MaterialTheme.typography.headlineLarge)
        Button(onClick = { count++
            if(count == numRepsPerSet){
                setsCompleted +=1
                count = 0
            }

        }) {
            Text(text = "Increment Reps")
        }

        Text(text = "Sets Completed = ${setsCompleted}")

    }
}




