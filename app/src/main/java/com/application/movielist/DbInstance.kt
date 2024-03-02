package com.application.movielist

import android.content.Context
import androidx.room.Room
import com.application.movielist.data.local.AppDatabase

class DbInstance {
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        
        @Synchronized
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build().also { INSTANCE = it }
            }
        }
    }
}