package com.example.classdemo3.data.model
import com.example.classdemo3.model_.HarryPotterCharactersItem

sealed class HarryPotterResponse{
    data class Success(val harryPotterCharacters: List<HarryPotterCharactersItem>?) : HarryPotterResponse()
    object Error : HarryPotterResponse()
}
