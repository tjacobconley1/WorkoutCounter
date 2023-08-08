package com.test.workoutcounter.di

import com.test.workoutcounter.data.MongoRepository
import com.test.workoutcounter.data.MongoRepositoryImpl
import com.test.workoutcounter.model.WorkoutInfo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object RealmDatabaseModule {

    @Singleton
    @Provides
    fun provideRealm(): Realm {
        val configuration = RealmConfiguration.Builder(
            schema = setOf(
                WorkoutInfo::class
            )
        ).compactOnLaunch().build()

        return Realm.open(configuration)
    }

    @Singleton
    @Provides
    fun provideMongoRepository(realm: Realm): MongoRepository{
        return MongoRepositoryImpl(realm = realm)
    }

}

