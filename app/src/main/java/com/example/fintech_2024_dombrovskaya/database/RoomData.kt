package com.example.fintech_2024_dombrovskaya.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FilmEntity::class],
    version = 1, exportSchema = false)
abstract class RoomData: RoomDatabase() {

    abstract fun filmsDao(): FilmsDao

    companion object {
        fun getDatabase(context: Context) = Room.databaseBuilder(
            context,
            RoomData::class.java,
            "room_data"
        ).allowMainThreadQueries().build()
    }
}