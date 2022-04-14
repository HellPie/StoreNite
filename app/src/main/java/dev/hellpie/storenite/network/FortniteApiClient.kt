package dev.hellpie.storenite.network

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.resources.Resources
import io.ktor.resources.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import timber.log.Timber

object FortniteApiClient {
    val client = HttpClient(OkHttp) {
        defaultRequest {
            url("https://fortnite-api.com/v2")
        }

        expectSuccess = true
        HttpResponseValidator {
            handleResponseExceptionWithRequest { exception, _ ->
                if (exception !is ClientRequestException)
                    return@handleResponseExceptionWithRequest
                val error: Response<Error> = exception.response.body()
                throw FortniteApiException(error.data.status, error.data.error)
            }
        }

        install(Resources)
        install(ContentNegotiation) {
            json()
        }

        install(Logging) {
            logger = object: Logger {
                override fun log(message: String) {
                    Timber.i(message)
                }
            }
        }
    }
}

@Serializable
@Resource("/cosmetics/br")
class Cosmetics(val language: String? = null) {
    @Serializable
    @Resource("{id}")
    class Id(val parent: Cosmetics = Cosmetics(), val id: String)

    @Serializable
    @Resource("new")
    class New(val parent: Cosmetics = Cosmetics())

    @Serializable
    @Resource("search")
    class Search(
        val parent: Cosmetics = Cosmetics(),
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
    ) {
        @Serializable
        @Resource("all")
        class All(val parent: Search)

        @Serializable
        @Resource("ids")
        class Ids(val parent: Search = Search(), val id: List<String>)
    }
}

@Serializable
data class Response<T>(
    val status: Int,
    val data: T,
)

@Serializable
data class Error(
    val status: Int,
    val error: String,
)

class FortniteApiException(val status: Int, val error: String) : Exception("Code ${status}: ${error}.")
