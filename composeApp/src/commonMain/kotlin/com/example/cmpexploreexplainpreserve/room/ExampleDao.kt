package com.example.cmpexploreexplainpreserve.room

import androidx.room.Insert

interface ExampleDao {
    @Insert
    suspend fun insert(exampleData: ExampleData)


}