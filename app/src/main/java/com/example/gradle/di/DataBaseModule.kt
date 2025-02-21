package org.lotka.android.base.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.dparseh.android.video.data.entity.dao.ItemDao
import ir.dparseh.android.video.data.entity.database.AppDatabase

import ir.dparseh.android.video.data.entity.datastore.UserPreferences
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_prefs")

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {



//    @Provides
//    @Singleton
//    fun provideDatabase(context: Context): AppDatabase {
//        return Room.databaseBuilder(
//            context.applicationContext,
//            AppDatabase::class.java,
//            "app_database"
//        ) // Add migration here
//            .fallbackToDestructiveMigration() // Use this ONLY if you don't need old data
//            .build()
//    }


//    @Provides
//    fun provideItemDao(database: AppDatabase): ItemDao {
//        return database.itemDao()
//    }

//    @Provides
//    @Singleton
//    fun provideDataStore(context: Context): DataStore<Preferences> {
//        return context.dataStore
//    }
//
//    @Provides
//    @Singleton
//    fun provideUserPreferences(dataStore: DataStore<Preferences>): UserPreferences {
//        return UserPreferences(dataStore)
//    }

}
