package com.hynekbraun.rickandmorty.shared.repository

import com.hynekbraun.rickandmorty.shared.database.FavoriteCharacterEntity
import com.hynekbraun.rickandmorty.shared.database.FavoriteCharactersDao
import com.hynekbraun.rickandmorty.shared.network.Response
import com.hynekbraun.rickandmorty.shared.api.CharactersApi
import com.hynekbraun.rickandmorty.shared.api.models.CharacterDetailApiModel
import com.hynekbraun.rickandmorty.shared.api.models.CharactersApiModel
import com.hynekbraun.rickandmorty.shared.repository.models.CharacterDetailModel
import com.hynekbraun.rickandmorty.shared.repository.models.CharacterModel
import com.hynekbraun.rickandmorty.shared.repository.models.CharactersListModel
import dev.mokkery.answering.returns
import dev.mokkery.every
import dev.mokkery.everySuspend
import dev.mokkery.mock
import kotlin.test.Test
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CharactersRepositoryImplTest {

    private val NEXT_PAGE = "next_page"
    private val QUERY = "query"
    private val ID = 1
    private val NAME = "name"
    private val STATUS = "status"
    private val PHOTO_URL = "photo_url"
    private val SPECIES = "species"
    private val TYPE = "type"
    private val GENDER = "gender"
    private val ORIGIN = "origin"
    private val LOCATION = "location"

    private val apiMock = mock<CharactersApi>()
    private val favoritesDaoMock = mock<FavoriteCharactersDao>()
    private val testedRepository = CharactersRepositoryImpl(apiMock, favoritesDaoMock)

    private val apiModelMock = CharactersApiModel(
        info = CharactersApiModel.Info(nextPage = NEXT_PAGE),
        characters = listOf(
            CharactersApiModel.Results(
                id = ID,
                name = NAME,
                status = STATUS,
                photoUrl = PHOTO_URL,
            )
        )
    )

    private val apiDetailModelMock = CharacterDetailApiModel(
        id = ID.toString(),
        name = NAME,
        status = STATUS,
        image = PHOTO_URL,
        species = SPECIES,
        type = TYPE,
        gender = GENDER,
        origin = CharacterDetailApiModel.Origin(ORIGIN),
        location = CharacterDetailApiModel.Location(LOCATION),
    )

    private val domainDetailModelMock = CharacterDetailModel(
        id = ID.toString(),
        name = NAME,
        status = STATUS,
        photoUrl = PHOTO_URL,
        species = SPECIES,
        type = TYPE,
        gender = GENDER,
        origin = ORIGIN,
        location = LOCATION,
    )

    private fun domainModelMock(isFavorite: Boolean = false): CharactersListModel = CharactersListModel(
        characters = listOf(
            CharacterModel(
                id = ID.toString(),
                name = NAME,
                status = STATUS,
                photoUrl = PHOTO_URL,
                isFavorite = isFavorite,
            )
        ),
        nextPage = NEXT_PAGE,
    )

    @Test
    fun `getCharacters success`() = runTest {
        val favoriteIds = listOf(ID.toString(), "2")
        val favorites = favoriteIds.map { FavoriteCharacterEntity(it) }

        every { favoritesDaoMock.getAll() } returns flowOf(favorites)
        everySuspend { apiMock.getCharacters() } returns Response.Success(apiModelMock)

        val result = testedRepository.getCharacters().first()

        assertTrue(result is Response.Success)
        assertEquals(domainModelMock(true), result.data)
    }

    @Test
    fun `getCharacters error`() = runTest {
        every { favoritesDaoMock.getAll() } returns flowOf(emptyList())
        everySuspend { apiMock.getCharacters() } returns Response.Error(message = "error")

        val result = testedRepository.getCharacters().first()

        assertTrue(result is Response.Error)
    }

    @Test
    fun `getFavoriteCharacters success`() = runTest {
        val favoriteIds = listOf(ID.toString(), "2")
        val favorites = favoriteIds.map { FavoriteCharacterEntity(it) }
        every { favoritesDaoMock.getAll() } returns flowOf(favorites)

        everySuspend { apiMock.getCharactersByIds(favoriteIds) } returns Response.Success(apiModelMock.characters)

        val result = testedRepository.getFavoriteCharacters().first()

        assertTrue(result is Response.Success)
        assertEquals(domainModelMock(true).characters, result.data)
    }

    @Test
    fun `getFavoriteCharacters error`() = runTest {
        val favoriteIds = listOf(ID.toString(), "2")
        val favorites = favoriteIds.map { FavoriteCharacterEntity(it) }
        every { favoritesDaoMock.getAll() } returns flowOf(favorites)

        everySuspend { apiMock.getCharactersByIds(favoriteIds) } returns Response.Error("error")

        val result = testedRepository.getFavoriteCharacters().first()

        assertTrue(result is Response.Error)
    }

    @Test
    fun `getCharactersByPage success`() = runTest {
        everySuspend { apiMock.getCharactersByPage(NEXT_PAGE) } returns Response.Success(apiModelMock)

        val result = testedRepository.getCharactersByPage(NEXT_PAGE)

        assertTrue(result is Response.Success)
        assertEquals(domainModelMock(), result.data)
    }

    @Test
    fun `getCharactersByPage error`() = runTest {
        everySuspend { apiMock.getCharactersByPage(NEXT_PAGE) } returns Response.Error("error")

        val result = testedRepository.getCharactersByPage(NEXT_PAGE)

        assertTrue(result is Response.Error)
    }

    @Test
    fun `getCharactersByPageAndQuery success`() = runTest {
        val favoriteIds = listOf(ID.toString(), "2")
        val favorites = favoriteIds.map { FavoriteCharacterEntity(it) }

        every { favoritesDaoMock.getAll() } returns flowOf(favorites)
        everySuspend { apiMock.getCharactersByPageAndQuery(NEXT_PAGE, QUERY) } returns Response.Success(apiModelMock)

        val result = testedRepository.getCharactersByPageAndQuery(NEXT_PAGE, QUERY)

        assertTrue(result is Response.Success)
        assertEquals(domainModelMock(true), result.data)
    }

    @Test
    fun `getCharactersByPageAndQuery error`() = runTest {
        every { favoritesDaoMock.getAll() } returns flowOf(emptyList())
        everySuspend { apiMock.getCharactersByPageAndQuery(NEXT_PAGE, QUERY) } returns Response.Error(message = "error")
        val result = testedRepository.getCharactersByPageAndQuery(NEXT_PAGE, QUERY)
        assertTrue(result is Response.Error)
    }

    @Test
    fun `getCharacterById success`() = runTest {
        everySuspend { apiMock.getCharacterById(ID.toString()) } returns Response.Success(apiDetailModelMock)

        val result = testedRepository.getCharacterById(ID.toString())

        assertTrue(result is Response.Success)
        assertEquals(domainDetailModelMock, result.data)
    }

    @Test
    fun `getCharacterById error`() = runTest {
        everySuspend { apiMock.getCharacterById(ID.toString()) } returns Response.Error("error")

        val result = testedRepository.getCharacterById(ID.toString())

        assertTrue(result is Response.Error)
    }
}
