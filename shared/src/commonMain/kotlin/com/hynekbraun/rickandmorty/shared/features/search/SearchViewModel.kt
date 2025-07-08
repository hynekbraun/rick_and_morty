package com.hynekbraun.rickandmorty.shared.features.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hynekbraun.rickandmorty.shared.network.Response
import com.hynekbraun.rickandmorty.shared.repository.CharactersRepository
import com.hynekbraun.rickandmorty.shared.repository.models.CharactersListModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

public class SearchViewModel(
    private val charactersRepository: CharactersRepository,
    private val componentFactory: SearchComponentsFactory,
) : ViewModel() {

    private var nextPage: String? = null

    private val _state: MutableStateFlow<SearchViewState> = MutableStateFlow(SearchViewState.Initial)
    public val state: StateFlow<SearchViewState> = _state.asStateFlow()

    private val _queryState: MutableStateFlow<String> = MutableStateFlow<String>("")
    public val queryState: StateFlow<String> = _queryState
        .onStart {
            listenToQuery()
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), "")

    private fun listenToQuery() {
        @OptIn(kotlinx.coroutines.FlowPreview::class)
        _queryState
            .debounce(500L)
            .distinctUntilChanged()
            .onEach { query ->
                if (query.isNotBlank() && query.length >= 2) {
                    nextPage = null
                    search(query)
                } else {
                    nextPage = null
                    _state.emit(SearchViewState.Initial)
                }
            }
            .launchIn(viewModelScope)
    }

    private suspend fun search(query: String) {
        val characters = charactersRepository.getCharactersByPageAndQuery(page = nextPage, query = query)
        val newState = characters.toState(componentFactory)

        (characters as? Response.Success<CharactersListModel>)?.let {
            nextPage = it.data.nextPage
        }

        _state.emit(newState)
    }

    public fun changeQuery(query: String) {
        _queryState.value = query
    }

    public fun clearQuery() {
        nextPage = null
        _queryState.value = ""
        _state.value = (SearchViewState.Initial)
    }

    public fun getNextPage() {
        viewModelScope.launch {
            nextPage?.let { page ->
                val currentQuery = _queryState.value
                val characters = charactersRepository.getCharactersByPageAndQuery(page = page, query = currentQuery)
                val newState = characters.toState(componentFactory)

                (characters as? Response.Success<CharactersListModel>)?.let {
                    nextPage = it.data.nextPage
                }

                (state.value as? SearchViewState.Data)?.let { safeState ->
                    (newState as? SearchViewState.Data)?.let { safeNewData ->
                        _state.emit(
                            safeState.copy(
                                characters = safeState.characters.plus(safeNewData.characters),
                                nextPage = safeNewData.nextPage,
                            ),
                        )
                    }
                } ?: _state.emit(newState)
            } ?: (state.value as? SearchViewState.Data)?.let { safeState ->
                _state.emit(safeState.copy(nextPage = null))
            }
        }
    }
}

private fun Response<CharactersListModel>.toState(componentFactory: SearchComponentsFactory): SearchViewState {
    return when (this) {
        is Response.Error<CharactersListModel> -> SearchViewState.Initial
        is Response.Success<CharactersListModel> -> componentFactory.create(
            this.data.characters,
            this.data.nextPage != null
        )
    }
}
