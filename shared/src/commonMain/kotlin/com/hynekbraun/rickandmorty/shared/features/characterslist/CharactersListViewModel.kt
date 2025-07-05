package com.hynekbraun.rickandmorty.shared.features.characterslist

import com.hynekbraun.rickandmorty.shared.repository.CharactersRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hynekbraun.rickandmorty.shared.network.Response
import com.hynekbraun.rickandmorty.shared.repository.models.CharacterModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

public class CharactersListViewModel(
    private val repository: CharactersRepository,
    private val componentFactory: CharactersListComponentsFactory,
) : ViewModel() {

    private val _state: MutableStateFlow<CharactersListViewState> = MutableStateFlow(CharactersListViewState.Loading)
    public val state: StateFlow<CharactersListViewState> = _state
        .onStart {
            getCharacters()
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, CharactersListViewState.Loading)

    private fun getCharacters() {
        viewModelScope.launch {
            val newState = repository.getCharacters().toState(componentFactory)
            _state.emit(newState)
        }
    }
}

private fun Response<List<CharacterModel>>.toState(componentFactory: CharactersListComponentsFactory): CharactersListViewState {
    return when (this) {
        is Response.Error<List<CharacterModel>> -> CharactersListViewState.Error
        is Response.Success<List<CharacterModel>> -> CharactersListViewState.Data(componentFactory.create(this.data))
    }
}
