package com.hynekbraun.rickandmorty.shared.features.characterslist

import com.hynekbraun.rickandmorty.shared.repository.CharactersRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hynekbraun.rickandmorty.shared.network.Response
import com.hynekbraun.rickandmorty.shared.repository.models.CharactersListModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

public class CharactersListViewModel(
    private val repository: CharactersRepository,
    private val componentFactory: CharactersListComponentsFactory,
) : ViewModel() {

    private var nextPage: String? = null

    private val _state: MutableStateFlow<CharactersListViewState> = MutableStateFlow(CharactersListViewState.Loading)
    public val state: StateFlow<CharactersListViewState> = _state
        .onStart {
            getCharacters()
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, CharactersListViewState.Loading)

    private fun getCharacters() {
        viewModelScope.launch {
            repository.getCharacters().collectLatest {
                val newState = it.toState(componentFactory)
                (it as? Response.Success<CharactersListModel>)?.let {
                    nextPage = it.data.nextPage
                }
                _state.emit(newState)
            }
        }
    }

    public fun getNextPage() {
        viewModelScope.launch {
            nextPage?.let {
                val characters = repository.getCharactersByPage(it)
                val newState = characters.toState(componentFactory)
                (characters as? Response.Success<CharactersListModel>)?.let {
                    nextPage = it.data.nextPage
                }
                (state.value as? CharactersListViewState.Data)?.let { safeState ->
                    (newState as? CharactersListViewState.Data)?.let { safeNewData ->
                        _state.emit(
                            safeState.copy(
                                characters = safeState.characters.plus(safeNewData.characters),
                                nextPage = safeNewData.nextPage
                            )
                        )
                    }
                } ?: _state.emit(newState)
            } ?: (state.value as? CharactersListViewState.Data)?.let { safeState ->
                _state.emit(safeState.copy(nextPage = null))
            }
        }
    }
}

private fun Response<CharactersListModel>.toState(componentFactory: CharactersListComponentsFactory): CharactersListViewState {
    return when (this) {
        is Response.Error<CharactersListModel> -> CharactersListViewState.Error
        is Response.Success<CharactersListModel> -> componentFactory.create(
            this.data.characters,
            this.data.nextPage != null
        )
    }
}
