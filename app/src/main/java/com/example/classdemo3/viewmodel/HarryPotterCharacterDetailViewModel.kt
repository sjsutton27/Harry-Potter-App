
package com.example.classdemo3.viewmodel
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.classdemo3.data.model.HarryPotterResponse
import com.example.classdemo3.data.repository.HarryPotterRepository
import com.example.classdemo3.model_.HarryPotterCharactersItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HarryPotterCharacterDetailViewModel @Inject constructor(
    private val harryPotterRepository: HarryPotterRepository
) : ViewModel() {
    private val _harryPotterCharacters = MutableStateFlow<HarryPotterCharacterEvent>(HarryPotterCharacterEvent.Loading)
    val harryPotterCharacters: StateFlow<HarryPotterCharacterEvent> = _harryPotterCharacters

    fun fetchById(id: String) {
        viewModelScope.launch {
            when (val result = harryPotterRepository.getCharacters()) {
                HarryPotterResponse.Error -> _harryPotterCharacters.value = HarryPotterCharacterEvent.Failure
                is HarryPotterResponse.Success ->_harryPotterCharacters.value = HarryPotterCharacterEvent.Success(result.harryPotterCharacters?.filter { it.id == id })
            }
        }
    }

    sealed class HarryPotterCharacterEvent {
        data class Success(val harryPotterCharacters: List<HarryPotterCharactersItem>?)
            : HarryPotterCharacterEvent()
        object Failure : HarryPotterCharacterEvent()
        object Loading : HarryPotterCharacterEvent()
    }
}

