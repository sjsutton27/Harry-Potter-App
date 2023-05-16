package com.example.classdemo3.data.repository

import android.util.Log
import com.example.classdemo3.data.HarryPotterApi
import com.example.classdemo3.data.model.HarryPotterResponse


import javax.inject.Inject

class HarryPotterRepositoryImpl @Inject constructor(
    private val harryPotterApi: HarryPotterApi
): HarryPotterRepository{
    override suspend fun getCharacters(): HarryPotterResponse {
        val result = harryPotterApi.getCharacters()
        return if (result.isSuccessful){
            HarryPotterResponse.Success(result.body()?: emptyList())
        }else{
                HarryPotterResponse.Error
        }
    }
}