package com.test.workoutcounter.data

import com.test.workoutcounter.model.WorkoutInfo
import kotlinx.coroutines.flow.Flow
import org.mongodb.kbson.ObjectId

interface MongoRepository {
    fun getWorkoutData(): Flow<List<WorkoutInfo>>
    fun filerData(workoutName: String): Flow<List<WorkoutInfo>>
    suspend fun insertWorkout(workoutInfo: WorkoutInfo)
    suspend fun updateWorkout(workoutInfo: WorkoutInfo)
    suspend fun deleteWorkout(id: ObjectId)
}