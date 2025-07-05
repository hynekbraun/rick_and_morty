package com.hynekbraun.rickandmorty.shared.features.characterdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hynekbraun.rickandmorty.shared.network.Response
import com.hynekbraun.rickandmorty.shared.repository.CharactersRepository
import com.hynekbraun.rickandmorty.shared.repository.models.CharacterDetailModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

public class CharacterDetailViewModel(
    private val repository: CharactersRepository,
    private val characterId: String,
    private val componentFactory: CharacterDetailComponentsFactory,
) : ViewModel() {

    private val _state: MutableStateFlow<CharacterDetailViewState> = MutableStateFlow(CharacterDetailViewState.Loading)
    public val state: StateFlow<CharacterDetailViewState> = _state
        .onStart {
            getCharacterDetail()
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, CharacterDetailViewState.Loading)

    private fun getCharacterDetail() {
        viewModelScope.launch {
            val newState = repository.getCharacterById(characterId)
            _state.emit(newState.toState(componentFactory))
        }
    }
}

private fun Response<CharacterDetailModel>.toState(componentFactory: CharacterDetailComponentsFactory): CharacterDetailViewState {
    return when (this) {
        is Response.Error<CharacterDetailModel> -> CharacterDetailViewState.Error
        is Response.Success<CharacterDetailModel> -> componentFactory.create(this.data)
    }
}
