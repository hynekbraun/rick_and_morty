package com.hynekbraun.rickandmorty.shared.features.characterslist

import app.cash.turbine.test
import com.hynekbraun.rickandmorty.shared.components.components.CardCharacterComponentModel
import com.hynekbraun.rickandmorty.shared.components.components.NextPageComponentModel
import com.hynekbraun.rickandmorty.shared.network.Response
import com.hynekbraun.rickandmorty.shared.repository.CharactersRepository
import com.hynekbraun.rickandmorty.shared.repository.models.CharacterModel
import com.hynekbraun.rickandmorty.shared.repository.models.CharactersListModel
import dev.mokkery.answering.returns
import dev.mokkery.every
import dev.mokkery.everySuspend
import dev.mokkery.mock
import dev.mokkery.verify
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class CharactersListViewModelTest {

    private val repository = mock<CharactersRepository>()
    private val componentFactory = mock<CharactersListComponentsFactory>()
    private val testedViewModel = CharactersListViewModel(repository, componentFactory)

    private val testDispatcher = UnconfinedTestDispatcher()

    private val characterDomainMock =
        CharacterModel(photoUrl = "", id = "1", name = "", status = "", isFavorite = false)
    private val characterDomainMock2 =
        CharacterModel(photoUrl = "", id = "2", name = "", status = "", isFavorite = false)

    private val characterComponentMock =
        CardCharacterComponentModel(photoUrl = "", name = "", status = "", showStar = false, id = "1")
    private val characterComponentMock2 =
        CardCharacterComponentModel(photoUrl = "", name = "", status = "", showStar = false, id = "2")

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial state is loading`() = runTest {
        everySuspend { repository.getCharacters() } returns flowOf()

        assertEquals(CharactersListViewState.Loading, testedViewModel.state.value)
    }

    @Test
    fun `getCharacters success updates state and nextPage`() = runTest {
        val characters = listOf(characterDomainMock)
        val charactersListModel = CharactersListModel(
            characters = characters,
            nextPage = "https://rickandmortyapi.com/api/character?page=2"
        )
        val expectedDataState = CharactersListViewState.Data(
            characters = listOf(characterComponentMock),
            nextPage = NextPageComponentModel,
        )

        everySuspend { repository.getCharacters() } returns flowOf(Response.Success(charactersListModel))
        every { componentFactory.create(characters, true) } returns expectedDataState

        testedViewModel.state.test {
            assertEquals(expectedDataState, awaitItem())
        }

        verify { componentFactory.create(characters, true) }
    }

    @Test
    fun `getCharacters error updates state to error`() = runTest {
        val errorResponse = Response.Error<CharactersListModel>("Network error")

        everySuspend { repository.getCharacters() } returns flowOf(errorResponse)

        testedViewModel.state.test {
            assertEquals(CharactersListViewState.Error, awaitItem())
        }
    }

    @Test
    fun `getCharacters success with null nextPage`() = runTest {
        val characters = listOf(characterDomainMock)
        val charactersListModel = CharactersListModel(
            characters = characters,
            nextPage = null
        )
        val expectedDataState = CharactersListViewState.Data(
            characters = listOf(characterComponentMock),
            nextPage = null
        )

        everySuspend { repository.getCharacters() } returns flowOf(Response.Success(charactersListModel))
        every { componentFactory.create(characters, false) } returns expectedDataState

        testedViewModel.state.test {
            assertEquals(expectedDataState, awaitItem())
        }
    }

    @Test
    fun `getNextPage success appends characters to existing list`() = runTest {
        val initialCharacters = listOf(characterDomainMock)
        val newCharacters = listOf(characterDomainMock2)

        val initialModel = CharactersListModel(
            characters = initialCharacters,
            nextPage = "https://rickandmortyapi.com/api/character?page=2"
        )
        val nextPageModel = CharactersListModel(
            characters = newCharacters,
            nextPage = "https://rickandmortyapi.com/api/character?page=3"
        )

        val initialDataState = CharactersListViewState.Data(
            characters = listOf(characterComponentMock),
            nextPage = NextPageComponentModel
        )
        val newDataState = CharactersListViewState.Data(
            characters = listOf(characterComponentMock2),
            nextPage = NextPageComponentModel
        )
        val allCharactersComponents = initialDataState.characters + newDataState.characters

        everySuspend { repository.getCharacters() } returns flowOf(Response.Success(initialModel))
        every { componentFactory.create(initialCharacters, true) } returns initialDataState
        every { componentFactory.create(newCharacters, true) } returns newDataState
        everySuspend {
            repository.getCharactersByPage("https://rickandmortyapi.com/api/character?page=2")
        } returns Response.Success(nextPageModel)

        testedViewModel.state.test {
            assertEquals(initialDataState, awaitItem())

            testedViewModel.getNextPage()

            val finalState = awaitItem() as CharactersListViewState.Data
            assertEquals(allCharactersComponents, finalState.characters)
            assertEquals(NextPageComponentModel, finalState.nextPage)
        }
    }

    @Test
    fun `getNextPage when nextPage is null updates nextPage to null`() = runTest {

        val initialCharacters = listOf(characterDomainMock)
        val initialModel = CharactersListModel(
            characters = initialCharacters,
            nextPage = null
        )
        val initialDataState = CharactersListViewState.Data(
            characters = listOf(characterComponentMock),
            nextPage = null
        )

        everySuspend { repository.getCharacters() } returns flowOf(Response.Success(initialModel))
        every { componentFactory.create(initialCharacters, false) } returns initialDataState

        testedViewModel.state.test {
            assertEquals(initialDataState, awaitItem())

            testedViewModel.getNextPage()

            expectNoEvents()
        }
    }
}
