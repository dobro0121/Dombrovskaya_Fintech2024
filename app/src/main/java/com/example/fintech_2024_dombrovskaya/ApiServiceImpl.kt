package com.example.fintech_2024_dombrovskaya

import com.example.fintech_2024_dombrovskaya.models.Description
import com.example.fintech_2024_dombrovskaya.models.Film
import com.example.fintech_2024_dombrovskaya.models.ResponseFilms
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.util.appendIfNameAbsent
import io.ktor.http.isSuccess


class ApiServiceImpl(
    private val client: HttpClient
) : ApiService {

    override suspend fun getPopularFilms(): List<Film> {
        try {
            val response: HttpResponse = client.get {
                url(ApiRoutes.BASE_URL + ApiRoutes.POPULAR_FILMS + "?type=" + ApiRoutes.COLLECTION)
                headers.appendIfNameAbsent("X-API-KEY", ApiRoutes.API_KEY)
            }
            if (response.status.isSuccess()) {
                val responseData = response.body<ResponseFilms<Film>>()
                return responseData.films
            } else {
                throw Exception("Request failed with status: ${response.status.value}")
            }
        } catch (ex: RedirectResponseException) {
            throw Exception("Redirect error: ${ex.response.status.description}")
        } catch (ex: ClientRequestException) {
            throw Exception("Client request error: ${ex.response.status.description}")
        } catch (ex: ServerResponseException) {
            throw Exception("Server response error: ${ex.response.status.description}")
        }
    }

    override suspend fun getDescriptionOfFilm(id: Int): Description {
        try {
            val response: HttpResponse = client.get {
                url(ApiRoutes.BASE_URL + ApiRoutes.DESCRIPTION + id.toString())
                headers.appendIfNameAbsent("X-API-KEY", ApiRoutes.API_KEY)
            }
            if (response.status.isSuccess()) {
                return response.body()
            } else {
                throw Exception("Request failed with status: ${response.status.value}")
            }
        } catch (ex: RedirectResponseException) {
            throw Exception("Redirect error: ${ex.response.status.description}")
        } catch (ex: ClientRequestException) {
            throw Exception("Client request error: ${ex.response.status.description}")
        } catch (ex: ServerResponseException) {
            throw Exception("Server response error: ${ex.response.status.description}")
        }
    }
}
