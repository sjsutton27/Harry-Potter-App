package com.example.classdemo3.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.classdemo3.data.model.HarryPotterResponse
import com.example.classdemo3.data.repository.HarryPotterRepository
import com.example.classdemo3.model_.HarryPotterCharactersItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HarryPotterViewModel @Inject constructor(
    private val harryPotterRepository :HarryPotterRepository
    )
    : ViewModel(){

    private val _harryPotterCharacters = MutableStateFlow<HarryPotterEvent>(HarryPotterEvent.Loading)
    val harryPotterCharacters: StateFlow<HarryPotterEvent> = _harryPotterCharacters

    fun fillData(){
        viewModelScope.launch{
            when (val result = harryPotterRepository.getCharacters()){
                HarryPotterResponse.Error -> _harryPotterCharacters.value = HarryPotterEvent.Failure
                is HarryPotterResponse.Success -> _harryPotterCharacters.value = HarryPotterEvent.Success(result.harryPotterCharacters!!)
            }
        }
    }

    sealed class HarryPotterEvent{
        data class Success(val harryPotterCharacters: List<HarryPotterCharactersItem>)
            : HarryPotterEvent()
        object Failure: HarryPotterEvent()
        object Loading: HarryPotterEvent()
    }
}
