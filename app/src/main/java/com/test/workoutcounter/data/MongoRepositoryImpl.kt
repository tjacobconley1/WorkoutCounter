package com.test.workoutcounter.data

import com.test.workoutcounter.model.WorkoutInfo
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.mongodb.kbson.ObjectId

class MongoRepositoryImpl(val realm: Realm): MongoRepository {

    override fun getWorkoutData(): Flow<List<WorkoutInfo>> {
        return realm.query<WorkoutInfo>().asFlow().map { it.list }
    }

    override fun filerData(workoutName: String): Flow<List<WorkoutInfo>> {
        return realm.query<WorkoutInfo>(query = "workoutName CONTAINS[c] $0", workoutName).asFlow().map { it.list }
    }

    override suspend fun insertWorkout(workoutInfo: WorkoutInfo) {
        realm.write { copyToRealm(workoutInfo) }
    }

    override suspend fun updateWorkout(workoutInfo: WorkoutInfo) {
        realm.write {
            val queriedWorkoutInfo = query<WorkoutInfo>(query = "_id == $0", workoutInfo._id).first().find()
            queriedWorkoutInfo?.workoutName = workoutInfo.workoutName
        }
    }

    override suspend fun deleteWorkout(id: ObjectId) {
         realm.write {
             val workoutInfo = query<WorkoutInfo>(query = "_id == $0", id).first().find()
             try{
                 workoutInfo.let {
                     if (it != null) {
                         delete(it)
                     }
                 }
             }catch (e: Exception){
                 // log statement
             }
         }
    }

}