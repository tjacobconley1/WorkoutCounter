package com.test.workoutcounter.Navigating

sealed class Screen(val route: String){
    object StartScreen : Screen("start_screen")
    object NewWorkout : Screen("new_workout")

    fun withArgs(vararg args: String): String{
        return buildString {
            append(route)
            args.forEach {arg ->
                append("/$arg")
            }
        }
    }
}
