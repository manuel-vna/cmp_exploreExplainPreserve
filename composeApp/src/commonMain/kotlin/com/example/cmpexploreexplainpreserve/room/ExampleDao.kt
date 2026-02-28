package com.example.cmpexploreexplainpreserve.room

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface ExampleDao {
    @Insert
    suspend fun insert(exampleData: ExampleData)


}