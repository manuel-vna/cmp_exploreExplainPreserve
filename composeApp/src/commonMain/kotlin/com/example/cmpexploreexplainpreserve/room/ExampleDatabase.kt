package com.example.cmpexploreexplainpreserve.room

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

@Database(
    entities = [ExampleData::class],
    version = 1,
)
@ConstructedBy(ExampleDatabaseConstructor::class)
abstract class ExampleDatabase : RoomDatabase() {
    abstract fun exampleDao(): ExampleDao
}

fun getRoomDatabase(
    builder: RoomDatabase.Builder<ExampleDatabase>
): ExampleDatabase {
    return builder
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect object ExampleDatabaseConstructor : RoomDatabaseConstructor<ExampleDatabase> {
    override fun initialize(): ExampleDatabase
}