package com.test.workoutcounter.screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.workoutcounter.data.MongoRepository
import com.test.workoutcounter.model.WorkoutInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.mongodb.kbson.BsonObjectId
import javax.inject.Inject

@HiltViewModel
class NewWorkoutViewModel @Inject constructor(
    private val repository: MongoRepository
): ViewModel() {
    var workoutName = mutableStateOf("")
    var objectId = mutableStateOf("")
    var filtered = mutableStateOf(false)
    var data = mutableStateOf(emptyList<WorkoutInfo>())

    init {
        viewModelScope.launch {
            repository.getWorkoutData().collect{
                data.value = it
            }
        }
    }

    fun updateWorkoutName(workoutName: String){
        this.workoutName.value = workoutName
    }

    fun updateObjectId(id: String){
        this.objectId.value = id
    }

    fun insertWorkoutInfo(){
        viewModelScope.launch(Dispatchers.IO) {
            if (workoutName.value.isNotEmpty()) {
                repository.insertWorkout(workoutInfo = WorkoutInfo().apply {
                    workoutName = this@NewWorkoutViewModel.workoutName.value
                })
            }
        }
    }

    fun updateWorkoutInfo(){
        viewModelScope.launch(Dispatchers.IO) {
            if(objectId.value.isNotEmpty()){
                repository.updateWorkout(workoutInfo = WorkoutInfo().apply {
                    _id = org.mongodb.kbson.ObjectId(hexString = this@NewWorkoutViewModel.objectId.value)
                    workoutName = this@NewWorkoutViewModel.workoutName.value
                })
            }
        }
    }

    fun deleteWorkoutInfo(){
        viewModelScope.launch(Dispatchers.IO) {
            if (objectId.value.isNotEmpty()){
                repository.deleteWorkout(id = org.mongodb.kbson.ObjectId(hexString = objectId.value))
            }
        }
    }

    fun filterData(){
        viewModelScope.launch(Dispatchers.IO) {
            if(filtered.value){
                repository.getWorkoutData().collect(){
                    filtered.value = false
                    workoutName.value = ""
                    data.value = it
                }
            }else{
                repository.filerData(workoutName = workoutName.value).collect{
                    filtered.value = true
                    data.value = it
                }
            }
        }
    }






}