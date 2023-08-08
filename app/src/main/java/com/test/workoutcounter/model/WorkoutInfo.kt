package com.test.workoutcounter.model

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.notifications.InitialResults
import io.realm.kotlin.notifications.ResultsChange
import io.realm.kotlin.notifications.UpdatedResults
import io.realm.kotlin.query.RealmQuery
import io.realm.kotlin.query.RealmResults
import io.realm.kotlin.types.RealmInstant
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.Ignore
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class WorkoutInfo : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId.invoke()
    var workoutName: String = ""
    var numberOfRepsPerSet: Int = 0
    var numberOfSetsCompleted: Int = 0
    @Ignore
    var timestamp: RealmInstant = RealmInstant.now()
}
