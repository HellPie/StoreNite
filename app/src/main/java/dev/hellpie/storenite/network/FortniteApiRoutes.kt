package dev.hellpie.storenite.network

import androidx.annotation.Discouraged
import io.ktor.resources.*
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

internal object FortniteApiRoutes {
    @Discouraged(message = "Data models not implemented.")
    @Serializable
    @Resource("/aes")
    class Aes(val keyFormat: AesKeyFormat? = null)

    @Discouraged(message = "Data models not implemented.")
    @Serializable
    @Resource("/banners")
    class Banners(val language: String? = null) {
        @Discouraged(message = "Data models not implemented.")
        @Serializable
        @Resource("/colors")
        class Colors()
    }

    @Serializable
    @Resource("/cosmetics/br")
    class Cosmetics(val language: String? = null) {
        @Serializable
        @Resource("/new")
        class New(val language: String? = null)

        @Serializable
        @Resource("/{id}")
        class ById(val id: String, val language: String? = null)

        @Serializable
        @Resource("/search")
        class Search(
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
        ) {
            @Serializable
            @Resource("/all")
            class All(val parent: Search)

            @Serializable
            @Resource("/ids")
            class ByIds(val ids: List<String>, val language: String? = null)
        }
    }

    @Discouraged(message = "Data models not implemented.")
    @Serializable
    @Resource("/creatorcode")
    class Creator(val name: String)

    @Discouraged(message = "Data models not implemented.")
    @Serializable
    @Resource("/map")
    class Map(val language: String? = null)

    @Discouraged(message = "Data models not implemented.")
    @Serializable
    @Resource("/news")
    class News(val language: String? = null) {
        @Discouraged(message = "Data models not implemented.")
        @Serializable
        @Resource("/br")
        class BattleRoyale(val language: String? = null)

        @Discouraged(message = "Data models not implemented.")
        @Serializable
        @Resource("/stw")
        class SaveTheWorld(val language: String? = null)

        @Discouraged(message = "Data models not implemented.")
        @Serializable
        @Resource("/creative")
        class Creative(val language: String? = null)
    }

    @Discouraged(message = "Data models not implemented.")
    @Serializable
    @Resource("/playlists")
    class Playlists(val language: String? = null) {
        @Discouraged(message = "Data models not implemented.")
        @Serializable
        @Resource("/{id}")
        class ById(val id: String, val language: String? = null)
    }

    @Serializable
    @Resource("/shop/br")
    class Shop(val language: String? = null) {
        @Serializable
        @Resource("/combined")
        class Combined(val language: String? = null)
    }

    @Discouraged(message = "Data models not implemented.")
    @Serializable
    @Resource("/stats/br/v2")
    abstract class Stats(
        val name: String,
        val accountType: StatsAccountType? = null,
        val timeWindow: StatsTimeWindow? = null,
        val image: StatsImage? = null
    ) {
        @Discouraged(message = "Data models not implemented.")
        @Serializable
        @Resource("/stats/br/v2")
        abstract class ById(
            val timeWindow: StatsTimeWindow? = null,
            val image: StatsImage? = null
        )
    }
}

@Serializable
internal enum class AesKeyFormat {
    @SerialName("hex") HEX,
    @SerialName("base64") BASE64,
}

@Serializable
internal enum class StatsAccountType {
    @SerialName("epic") EPIC_GAMES,
    @SerialName("psn") PLAYSTATION,
    @SerialName("xbl") XBOX,
}

@Serializable
internal enum class StatsTimeWindow {
    @SerialName("season") SEASON,
    @SerialName("lifetime") LIFETIME,
}

@Serializable
internal enum class StatsImage {
    @SerialName("all") ALL,
    @SerialName("keyboardMouse") KEYBOARD_MOUSE,
    @SerialName("gamepad") GAMEPAD,
    @SerialName("touch") TOUCH,
    @SerialName("none") NONE,
}
