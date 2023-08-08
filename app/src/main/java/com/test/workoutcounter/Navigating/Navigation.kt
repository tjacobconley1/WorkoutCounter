package com.test.workoutcounter.Navigating

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.test.workoutcounter.NewWorkout
import com.test.workoutcounter.StartScreen
import com.test.workoutcounter.screen.NewWorkoutViewModel

@Composable
fun Navigation(){

    val navController = rememberNavController()
    NavHost(navController, startDestination = Screen.StartScreen.route){

        // Start Screen
        composable(route = Screen.StartScreen.route){
            StartScreen(navController)
        }

        // NewWorkout Screen
        composable(
            route = Screen.NewWorkout.route + "/{nameOfWorkout}/{numRepsPerSet}",
            arguments = listOf(
                navArgument("nameOfWorkout"){
                    type = NavType.StringType
                    defaultValue = "Standard"
                    nullable = false
                },
                navArgument("numRepsPerSet"){
                    type = NavType.IntType
                    defaultValue = 10
                    nullable = false
                }
            )
        ){entry ->
            NewWorkout(
                nameOfWorkout = entry.arguments?.getString("nameOfWorkout"),
                numRepsPerSet = entry.arguments?.getInt("numRepsPerSet"))
        }

    }
}