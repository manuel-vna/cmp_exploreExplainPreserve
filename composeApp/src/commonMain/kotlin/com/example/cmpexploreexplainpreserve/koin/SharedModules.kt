package com.example.cmpexploreexplainpreserve.koin

import com.example.cmpexploreexplainpreserve.room.ExampleDatabase
import com.example.cmpexploreexplainpreserve.room.ExampleDatabaseViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val sharedModule = module {
    viewModel { ExampleDatabaseViewModel(get<ExampleDatabase>().exampleDao()) }
}

expect val platformModule: Module