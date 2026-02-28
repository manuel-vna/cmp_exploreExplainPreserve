package com.example.cmpexploreexplainpreserve.room

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

fun getExampleDatabase(context: Context): RoomDatabase.Builder<ExampleDatabase> {
    val dbFile = context.getDatabasePath("example.db")
    return Room.databaseBuilder<ExampleDatabase>(
        context = context.applicationContext,
        name = dbFile.absolutePath
    )

}