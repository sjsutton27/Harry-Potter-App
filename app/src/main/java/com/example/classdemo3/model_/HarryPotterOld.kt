package com.example.classdemo3.model_
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class HarryPotterOld(
    val id: Int,
    val name: String,
    @JsonProperty("alternate_names")
    val alternateNames: List<String>,
    val species: String,
    val gender: String,
    val house: String,
    val dateOfBirth: String,
    val yearOfBirth: Long,
    val wizard: Boolean,
    val ancestry: String,
    val eyeColour: String,
    val hairColour:String,
    val wand: String,
    val patronus: String,
    val hogwartsStudent: Boolean,
    val hogwartsStaff: Boolean,
    val actor: String,
    @JsonProperty("alternate_actors")
    val alternateActors: List<Any ?>,
    val alive: Boolean,
    val image: String
)

