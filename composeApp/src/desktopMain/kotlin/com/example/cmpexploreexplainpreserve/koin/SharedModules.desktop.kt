package com.example.cmpexploreexplainpreserve.koin

import com.example.cmpexploreexplainpreserve.room.getExampleDatabase
import com.example.cmpexploreexplainpreserve.room.getRoomDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
        val builder = getExampleDatabase()
        getRoomDatabase(builder)
    }