package com.hynekbraun.rickandmorty.shared.api

import com.hynekbraun.rickandmorty.shared.network.NetworkExecutor
import com.hynekbraun.rickandmorty.shared.network.Response
import com.hynekbraun.rickandmorty.shared.api.CharactersApiImpl
import com.hynekbraun.rickandmorty.shared.api.models.CharacterDetailApiModel
import com.hynekbraun.rickandmorty.shared.api.models.CharactersApiModel
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.mock
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.coroutines.test.runTest

internal class CharactersApiImplTest {

    private val apiModelMock = CharactersApiModel(
        info = CharactersApiModel.Info(nextPage = null),
        characters = listOf(
            CharactersApiModel.Results(
                id = 1,
                name = "",
                status = "",
                photoUrl = "",
            )
        )
    )

    private val apiDetailModelMock = CharacterDetailApiModel(
        id = "1",
        name = "",
        status = "",
        image = "",
        species = "",
        type = "",
        gender = "",
        origin = CharacterDetailApiModel.Origin(""),
        location = CharacterDetailApiModel.Location(""),
    )

    private val networkExecutorMock: NetworkExecutor = mock()

    private val testedApi: CharactersApiImpl = CharactersApiImpl(networkExecutorMock)

    @Test
    fun `getCharacters returns success response`() = runTest {

        everySuspend {
            networkExecutorMock.get(CharactersApiImpl.CHARACTERS_URL, CharactersApiModel::class)
        } returns Response.Success(apiModelMock)

        val result = testedApi.getCharacters()

        assertTrue(result is Response.Success)
        assertEquals(apiModelMock, result.data)
    }

    @Test
    fun `getCharacters returns error response`() = runTest {

        everySuspend {
            networkExecutorMock.get(CharactersApiImpl.CHARACTERS_URL, CharactersApiModel::class)
        } returns Response.Error(message = "error")

        val result = testedApi.getCharacters()

        assertTrue(result is Response.Error)
    }

    @Test
    fun `getCharactersByIds returns success response`() = runTest {
        val ids = listOf("1")
        val url = "${CharactersApiImpl.BASE_URL}${CharactersApiImpl.DELIMITER}${ids.joinToString()}"

        everySuspend {
            networkExecutorMock.getList(url, CharactersApiModel.Results::class)
        } returns Response.Success(apiModelMock.characters)

        val result = testedApi.getCharactersByIds(ids)

        assertTrue(result is Response.Success)
        assertEquals(apiModelMock.characters, result.data)
    }

    @Test
    fun `getCharactersByIds returns error response`() = runTest {
        val ids = listOf("1")
        val url = "${CharactersApiImpl.BASE_URL}${CharactersApiImpl.DELIMITER}${ids.joinToString()}"

        everySuspend {
            networkExecutorMock.getList(url, CharactersApiModel.Results::class)
        } returns Response.Error(message = "error")

        val result = testedApi.getCharactersByIds(ids)

        assertTrue(result is Response.Error)
    }

    @Test
    fun `getCharactersByPageAndQuery with url null returns success`() = runTest {
        val query = "Rick"
        val expectedUrl = "${CharactersApiImpl.CHARACTERS_URL}${CharactersApiImpl.NAME_FILTER}$query"

        everySuspend {
            networkExecutorMock.get(expectedUrl, CharactersApiModel::class)
        } returns Response.Success(apiModelMock)

        val result = testedApi.getCharactersByPageAndQuery(null, query)

        assertTrue(result is Response.Success)
        assertEquals(apiModelMock, result.data)
    }

    @Test
    fun `getCharactersByPageAndQuery with url null returns error`() = runTest {
        val query = "Rick"
        val expectedUrl = "${CharactersApiImpl.CHARACTERS_URL}${CharactersApiImpl.NAME_FILTER}$query"

        everySuspend {
            networkExecutorMock.get(expectedUrl, CharactersApiModel::class)
        } returns Response.Error("error")

        val result = testedApi.getCharactersByPageAndQuery(null, query)

        assertTrue(result is Response.Error)
    }

    @Test
    fun `getCharactersByPageAndQuery with non-null url returns success`() = runTest {
        val url = "https://rickandmortyapi.com/api/character?page=2"
        val query = "Morty"
        val expectedUrl = "$url${CharactersApiImpl.NAME_FILTER}$query"

        everySuspend {
            networkExecutorMock.get(expectedUrl, CharactersApiModel::class)
        } returns Response.Success(apiModelMock)

        val result = testedApi.getCharactersByPageAndQuery(url, query)

        assertTrue(result is Response.Success)
        assertEquals(apiModelMock, result.data)
    }

    @Test
    fun `getCharactersByPageAndQuery with non-null url returns error`() = runTest {
        val url = "https://rickandmortyapi.com/api/character?page=2"
        val query = "Morty"
        val expectedUrl = "$url${CharactersApiImpl.NAME_FILTER}$query"

        everySuspend {
            networkExecutorMock.get(expectedUrl, CharactersApiModel::class)
        } returns Response.Error("error")

        val result = testedApi.getCharactersByPageAndQuery(url, query)

        assertTrue(result is Response.Error)
    }

    @Test
    fun `getCharacterById returns success response`() = runTest {
        val id = "1"
        val url = "${CharactersApiImpl.BASE_URL}${CharactersApiImpl.DELIMITER}$id"
        everySuspend {
            networkExecutorMock.get(url, CharacterDetailApiModel::class)
        } returns Response.Success(apiDetailModelMock)

        val result = testedApi.getCharacterById(id)

        assertTrue(result is Response.Success)
        assertEquals(apiDetailModelMock, result.data)
    }

    @Test
    fun `getCharacterById returns error response`() = runTest {
        val id = "1"
        val url = "${CharactersApiImpl.BASE_URL}${CharactersApiImpl.DELIMITER}$id"
        everySuspend { networkExecutorMock.get(url, CharacterDetailApiModel::class) } returns Response.Error("error")

        val result = testedApi.getCharacterById(id)

        assertTrue(result is Response.Error)
    }
}
