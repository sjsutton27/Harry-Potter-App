package com.example.classdemo3.data.repository

import com.example.classdemo3.data.model.HarryPotterResponse


interface HarryPotterRepository {
    suspend fun getCharacters(): HarryPotterResponse
}

