package com.example.cmpexploreexplainpreserve.room

import androidx.room.Room
import androidx.room.RoomDatabase
import java.io.File

fun getExampleDatabase(): RoomDatabase.Builder<ExampleDatabase> {
    val dbFile = File(System.getProperty("java.io.tmpdir"), "Example.db")
    return Room.databaseBuilder<ExampleDatabase>(
        name = dbFile.absolutePath,
    )
}