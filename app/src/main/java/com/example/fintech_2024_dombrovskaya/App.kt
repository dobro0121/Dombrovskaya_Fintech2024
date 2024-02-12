package com.example.fintech_2024_dombrovskaya

import android.app.Application
import com.example.fintech_2024_dombrovskaya.database.RoomData
import com.example.fintech_2024_dombrovskaya.repository.FragmentRepository

class App: Application() {

    val database by lazy { RoomData.getDatabase(this) }

    val popularRepository by lazy { FragmentRepository(database.filmsDao()) }
}