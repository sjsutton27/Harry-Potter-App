package com.example.classdemo3.di

import com.example.classdemo3.data.HarryPotterApi
import com.example.classdemo3.data.repository.HarryPotterRepository
import com.example.classdemo3.data.repository.HarryPotterRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class RepositoryModule{
        @Binds
        @Singleton
        abstract  fun bindHarryPotterRepository(
            harryPotterRepositoryImpl: HarryPotterRepositoryImpl,
        ): HarryPotterRepository
    }
