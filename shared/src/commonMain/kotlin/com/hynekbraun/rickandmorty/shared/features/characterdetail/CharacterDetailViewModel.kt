package com.hynekbraun.rickandmorty.shared.features.characterdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hynekbraun.rickandmorty.shared.components.components.ActionBarComponentModel
import com.hynekbraun.rickandmorty.shared.network.Response
import com.hynekbraun.rickandmorty.shared.repository.CharactersRepository
import com.hynekbraun.rickandmorty.shared.repository.FavoriteCharactersRepository
import com.hynekbraun.rickandmorty.shared.repository.models.CharacterDetailModel
import com.hynekbraun.rickandmorty.shared.resources.DrawableResources
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

public class CharacterDetailViewModel(
    private val charactersRepository: CharactersRepository,
    private val favoritesRepository: FavoriteCharactersRepository,
    private val characterId: String,
    private val componentFactory: CharacterDetailComponentsFactory,
    private val drawableResources: DrawableResources,
) : ViewModel() {

    private val _actionBarState: MutableStateFlow<ActionBarComponentModel> =
        MutableStateFlow(
            ActionBarComponentModel(
                title = null,
                leadingIcon = drawableResources.actionBarBackChevron,
                trailingIcon = null,
            )
        )
    public val actionBarState: StateFlow<ActionBarComponentModel> = _actionBarState.asStateFlow()

    private val _state: MutableStateFlow<CharacterDetailViewState> =
        MutableStateFlow(CharacterDetailViewState.Loading)
    public val state: StateFlow<CharacterDetailViewState> = _state
        .onStart {
            getCharacterDetail()
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, CharacterDetailViewState.Loading)

    private fun getCharacterDetail() {
        viewModelScope.launch {
            async {
                val isFavorite = favoritesRepository.getFavoriteCharacterById(characterId)
                _actionBarState.emit(
                    actionBarState.value.copy(
                        trailingIcon = if (isFavorite == null) {
                            drawableResources.actionBarFavoritesEmpty
                        } else {
                            drawableResources.actionBarFavoritesFilled
                        }
                    )
                )
            }.await()
            async {
                val newStateResponse = charactersRepository.getCharacterById(characterId)
                val newState = newStateResponse.toState(componentFactory)
                _state.emit(newState)
                (newState as? CharacterDetailViewState.Data)?.let {
                    _actionBarState.emit(actionBarState.value.copy(title = it.header.name))
                }
            }.await()
        }
    }

    public fun toggleFavorite() {
        viewModelScope.launch {
            favoritesRepository.toggle(
                characterId,
                isFavorite = actionBarState.value.trailingIcon == drawableResources.actionBarFavoritesFilled,
            )
            val isFavorite = favoritesRepository.getFavoriteCharacterById(characterId)
            _actionBarState.emit(
                actionBarState.value.copy(
                    trailingIcon = if (isFavorite == null) {
                        drawableResources.actionBarFavoritesEmpty
                    } else {
                        drawableResources.actionBarFavoritesFilled
                    }
                )
            )
        }
    }
}

private fun Response<CharacterDetailModel>.toState(
    componentFactory: CharacterDetailComponentsFactory,
): CharacterDetailViewState {
    return when (this) {
        is Response.Error<CharacterDetailModel> -> CharacterDetailViewState.Error
        is Response.Success<CharacterDetailModel> -> componentFactory.create(this.data)
    }
}
