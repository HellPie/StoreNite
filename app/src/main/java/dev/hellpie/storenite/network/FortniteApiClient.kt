package dev.hellpie.storenite.network

import android.util.Log
import dev.hellpie.storenite.models.Cosmetics
import dev.hellpie.storenite.models.CosmeticsDiff
import dev.hellpie.storenite.models.CosmeticsStoreSet
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.resources.*
import io.ktor.http.ContentType.Application.Json
import io.ktor.serialization.kotlinx.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.datetime.Instant
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import timber.log.Timber

interface FortniteApiService {
    suspend fun getAllCosmetics(language: String? = null): List<Cosmetics>
    suspend fun getNewCosmetics(language: String? = null): CosmeticsDiff?

    suspend fun getCosmeticById(id: String, language: String? = null): Cosmetics?
    suspend fun getCosmeticsByIds(ids: List<String>, language: String? = null): List<Cosmetics>

    suspend fun getCosmeticBy(query: CosmeticsSearchQueries): Cosmetics?
    suspend fun getCosmeticsBy(query: CosmeticsSearchQueries): List<Cosmetics>

    suspend fun getShop(language: String? = null): CosmeticsStoreSet?
    suspend fun getCombinedShop(language: String? = null): CosmeticsStoreSet?

    data class CosmeticsSearchQueries(
        val language: String? = null,
        val searchLanguage: String? = null,
        val matchMethod: String? = null,
        val id: String? = null,
        val name: String? = null,
        val description: String? = null,
        val type: String? = null,
        val displayType: String? = null,
        val backendType: String? = null,
        val rarity: String? = null,
        val displayRarity: String? = null,
        val backendRarity: String? = null,
        val hasSeries: Boolean? = null,
        val series: String? = null,
        val backendSeries: String? = null,
        val hasSet: Boolean? = null,
        val set: String? = null,
        val setText: String? = null,
        val backendSet: String? = null,
        val hasIntroduction: Boolean? = null,
        val backendIntroduction: Int? = null,
        val introductionChapter: String? = null,
        val introductionSeason: String? = null,
        val hasFeaturedImage: Boolean? = null,
        val hasVariants: Boolean? = null,
        val hasGameplayTags: Boolean? = null,
        val gameplayTag: String? = null,
        val hasMetaTags: Boolean? = null,
        val metaTag: String? = null,
        val hasDynamicPakId: Boolean? = null,
        val dynamicPakId: String? = null,
        val added: Instant? = null,
        val addedSince: Instant? = null,
        val unseenFor: Int? = null,
        val lastAppearance: Instant? = null,
    )

    companion object {

        @OptIn(ExperimentalSerializationApi::class)
        fun create(): FortniteApiService {
            return FortniteApiServiceImpl(
                HttpClient(OkHttp) {
                    install(Logging) {
                        level = LogLevel.INFO
                        logger = object : Logger {
                            override fun log(message: String) {
                                Timber.d(message)
                            }
                        }
                    }

                    install(ContentNegotiation) {
                        serialization(Json, Json(DefaultJson) {
                            isLenient = true
                            ignoreUnknownKeys = true
                            explicitNulls = false
                        })
                    }

                    install(HttpTimeout) {
                        requestTimeoutMillis = 15_000L
                        connectTimeoutMillis = 15_000L
                        socketTimeoutMillis = 15_000L
                    }

                    install(Resources)

                    defaultRequest {
                        url("https://fortnite-api.com/v2/")
                    }

                    expectSuccess = true
                    HttpResponseValidator {
                        handleResponseExceptionWithRequest { exception, _ ->
                            if (exception !is ClientRequestException)
                                return@handleResponseExceptionWithRequest
                            val response: ErrorResponse = exception.response.body()
                            throw FortniteApiException(
                                response.status,
                                response.error ?: "No error message specified"
                            )
                        }
                    }
                }
            )
        }
    }
}

private class FortniteApiServiceImpl(private val client: HttpClient) : FortniteApiService {
    override suspend fun getAllCosmetics(language: String?): List<Cosmetics> =
        try {
            val response: CosmeticsListResponse = client.get(FortniteApiRoutes.Cosmetics(language)).body()
            response.data ?: emptyList()
        } catch (exception: FortniteApiException) {
            Timber.w(exception, "Fortnite API failure.")
            emptyList()
        } catch (exception: Exception) {
            Timber.w(exception)
            emptyList()
        }

    override suspend fun getNewCosmetics(language: String?): CosmeticsDiff? =
        try {
            val response: CosmeticsDiffResponse = client.get(FortniteApiRoutes.Cosmetics.New(language)).body()
            response.data
        } catch (exception: FortniteApiException) {
            Timber.w(exception, "Fortnite API failure.")
            null
        } catch (exception: Exception) {
            Timber.w(exception)
            null
        }

    override suspend fun getCosmeticById(id: String, language: String?): Cosmetics? =
        try {
            val response: CosmeticsResponse = client.get(FortniteApiRoutes.Cosmetics.ById(id, language)).body()
            response.data
        } catch (exception: FortniteApiException) {
            Timber.w(exception, "Fortnite API failure.")
            null
        } catch (exception: Exception) {
            Timber.w(exception)
            null
        }

    override suspend fun getCosmeticsByIds(ids: List<String>, language: String?): List<Cosmetics> =
        try {
            val response: CosmeticsListResponse = client.get(FortniteApiRoutes.Cosmetics.Search.ByIds(ids, language)).body()
            response.data ?: emptyList()
        } catch (exception: FortniteApiException) {
            Timber.w(exception, "Fortnite API failure.")
            emptyList()
        } catch (exception: Exception) {
            Timber.w(exception)
            emptyList()
        }

    override suspend fun getCosmeticBy(query: FortniteApiService.CosmeticsSearchQueries): Cosmetics? {
        return try {
            val response: CosmeticsResponse = client.get(FortniteApiRoutes.Cosmetics.Search(
                language = query.language,
                searchLanguage = query.searchLanguage,
                matchMethod = query.matchMethod,
                id = query.id,
                name = query.name,
                description = query.description,
                type = query.type,
                displayType = query.displayType,
                backendType = query.backendType,
                rarity = query.rarity,
                displayRarity = query.displayRarity,
                backendRarity = query.backendRarity,
                hasSeries = query.hasSeries,
                series = query.series,
                backendSeries = query.backendSeries,
                hasSet = query.hasSet,
                set = query.set,
                setText = query.setText,
                backendSet = query.backendSet,
                hasIntroduction = query.hasIntroduction,
                backendIntroduction = query.backendIntroduction,
                introductionChapter = query.introductionChapter,
                introductionSeason = query.introductionSeason,
                hasFeaturedImage = query.hasFeaturedImage,
                hasVariants = query.hasVariants,
                hasGameplayTags = query.hasGameplayTags,
                gameplayTag = query.gameplayTag,
                hasMetaTags = query.hasMetaTags,
                metaTag = query.metaTag,
                hasDynamicPakId = query.hasDynamicPakId,
                dynamicPakId = query.dynamicPakId,
                added = query.added,
                addedSince = query.addedSince,
                unseenFor = query.unseenFor,
                lastAppearance = query.lastAppearance,
            )).body()
            response.data
        } catch (exception: FortniteApiException) {
            Timber.w(exception, "Fortnite API failure.")
            null
        } catch (exception: Exception) {
            Timber.w(exception)
            null
        }
    }

    override suspend fun getCosmeticsBy(query: FortniteApiService.CosmeticsSearchQueries): List<Cosmetics> {
        return try {
            val response: CosmeticsListResponse = client.get(FortniteApiRoutes.Cosmetics.Search.All(FortniteApiRoutes.Cosmetics.Search(
                language = query.language,
                searchLanguage = query.searchLanguage,
                matchMethod = query.matchMethod,
                id = query.id,
                name = query.name,
                description = query.description,
                type = query.type,
                displayType = query.displayType,
                backendType = query.backendType,
                rarity = query.rarity,
                displayRarity = query.displayRarity,
                backendRarity = query.backendRarity,
                hasSeries = query.hasSeries,
                series = query.series,
                backendSeries = query.backendSeries,
                hasSet = query.hasSet,
                set = query.set,
                setText = query.setText,
                backendSet = query.backendSet,
                hasIntroduction = query.hasIntroduction,
                backendIntroduction = query.backendIntroduction,
                introductionChapter = query.introductionChapter,
                introductionSeason = query.introductionSeason,
                hasFeaturedImage = query.hasFeaturedImage,
                hasVariants = query.hasVariants,
                hasGameplayTags = query.hasGameplayTags,
                gameplayTag = query.gameplayTag,
                hasMetaTags = query.hasMetaTags,
                metaTag = query.metaTag,
                hasDynamicPakId = query.hasDynamicPakId,
                dynamicPakId = query.dynamicPakId,
                added = query.added,
                addedSince = query.addedSince,
                unseenFor = query.unseenFor,
                lastAppearance = query.lastAppearance,
            ))).body()
            response.data ?: emptyList()
        } catch (exception: FortniteApiException) {
            Timber.w(exception, "Fortnite API failure.")
            emptyList()
        } catch (exception: Exception) {
            Timber.w(exception)
            emptyList()
        }
    }

    override suspend fun getShop(language: String?): CosmeticsStoreSet? =
        try {
            val response: ShopResponse = client.get(FortniteApiRoutes.Shop(language)).body()
            response.data
        } catch (exception: FortniteApiException) {
            Timber.w(exception, "Fortnite API failure.")
            null
        } catch (exception: Exception) {
            Timber.w(exception)
            null
        }

    override suspend fun getCombinedShop(language: String?): CosmeticsStoreSet? =
        try {
            val response: ShopResponse = client.get(FortniteApiRoutes.Shop.Combined(language)).body()
            response.data
        } catch (exception: FortniteApiException) {
            Timber.w(exception, "Fortnite API failure.")
            null
        } catch (exception: Exception) {
            Timber.w(exception)
            null
        }
}
