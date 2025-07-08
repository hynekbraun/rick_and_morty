package com.hynekbraun.rickandmorty.shared.features.favoriteslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hynekbraun.rickandmorty.shared.network.Response
import com.hynekbraun.rickandmorty.shared.repository.CharactersRepository
import com.hynekbraun.rickandmorty.shared.repository.models.CharacterModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

public class FavoritesListViewModel(
    private val repository: CharactersRepository,
    private val componentFactory: FavoritesListComponentsFactory,
) : ViewModel() {

    private val _state: MutableStateFlow<FavoritesListViewState> = MutableStateFlow(FavoritesListViewState.Loading)
    public val state: StateFlow<FavoritesListViewState> = _state
        .onStart {
            getFavorites()
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, FavoritesListViewState.Loading)

    private fun getFavorites() {
        viewModelScope.launch {
            repository.getFavoriteCharacters().collectLatest {
                val newState = it.toState(componentFactory)
                _state.emit(newState)
            }
        }
    }

}

private fun Response<List<CharacterModel>>.toState(componentFactory: FavoritesListComponentsFactory): FavoritesListViewState {
    return when (this) {
        is Response.Error<List<CharacterModel>> -> FavoritesListViewState.Error
        is Response.Success<List<CharacterModel>> -> componentFactory.create(this.data)
    }
}
