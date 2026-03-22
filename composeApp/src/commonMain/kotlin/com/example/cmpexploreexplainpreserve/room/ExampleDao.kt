package com.example.cmpexploreexplainpreserve.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ExampleDao {
    @Insert
    suspend fun insert(exampleData: ExampleData)

    @Query("SELECT * FROM exampleTable")
    fun getAll(): Flow<List<ExampleData>>
}