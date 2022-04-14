package dev.hellpie.storenite.models

import dev.hellpie.storenite.models.utils.UrlSerializer
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import java.net.URL

@Serializable
data class Cosmetics(
    val id: String,
    val type: LocalizedValue,

    val path: String,
    val displayAssetPath: String,
    val definitionPath: String,

    val name: String,
    val description: String,

    val showcaseVideo: String,
    val images: Images,
    val variants: List<Variant>,

    val introduction: Season,
    val added: Instant,
    val shopHistory: List<Instant>,

    val rarity: LocalizedValue,
    val set: DescriptiveValue,
    val series: Series,
    val dynamicPakId: String,

    val gameplayTags: List<String>,
    val metaTags: List<String>,
    val searchTags: List<String>,
) {
    @Transient val showcaseVideoUrl = URL("https", "youtu.be", showcaseVideo)

    @Serializable
    data class LocalizedValue(
        val value: String,
        val backendValue: String,
        val displayValue: String,
    )

    @Serializable
    data class DescriptiveValue(
        val value: String,
        val backendValue: String,
        val text: String,
    )

    @Serializable
    data class Series(
        val value: String,
        val backendValue: String,
        @Serializable(with = UrlSerializer::class) val image: URL,
    )

    @Serializable
    data class Season(
        val chapter: String,
        val season: String,
        val backendValue: String,
        val text: String,
    )

    @Serializable
    data class Images(
        @Serializable(with = UrlSerializer::class) val icon: URL,
        @Serializable(with = UrlSerializer::class) val smallIcon: URL,
        @Serializable(with = UrlSerializer::class) val featured: URL,
        val other: Map<String, @Serializable(with = UrlSerializer::class) URL>,
    )

    @Serializable
    data class Variant(
        val type: String,
        val channel: String,
        val options: List<Option>,
    ) {
        @Serializable
        data class Option(
            val tag: String,
            val name: String,
            @Serializable(with = UrlSerializer::class) val image: URL,
        )
    }
}

@Serializable
data class CosmeticsDiff(
    val hash: String,

    val build: String,
    val previousBuild: String,

    val date: Instant,
    val lastAddition: Instant,

    val items: List<Cosmetics>,
)

@Serializable
data class CosmeticsStoreSet(
    val hash: String,

    val date: Instant,

    @Serializable(with = UrlSerializer::class) val vbuckIcon: URL,

    val featured: Store,
    val daily: Store,

    val specialFeatured: Store,
    val specialDaily: Store,

    val votes: Store,
    val voteWinners: Store,
) {
    @Serializable
    data class Store(
        val name: String,
        val entries: List<Entry>
    ) {
        @Serializable
        data class Entry(
            val offerId: String,
            val devName: String,

            val regularPrice: Int,
            val finalPrice: Int,

            val banner: Banner,
            val bundle: Bundle,
            val tileSize: String,

            val giftable: Boolean,
            val refundable: Boolean,

            val sortPriority: Int,

            val displayAssetPath: String,
            val newDisplayAssetPath: String,
            val newDisplayAsset: Asset,

            val categories: List<String>,

            val sectionId: String,
            val section: Section,

            val items: List<Cosmetics>,
        ) {
            @Serializable
            data class Banner(
                val value: String,
                val backendValue: String,
                val intensity: String,
            )

            @Serializable
            data class Bundle(
                val name: String,
                val info: String,
                @Serializable(with = UrlSerializer::class) val image: URL,
            )

            @Serializable
            data class Asset(
                val id: String,
                val cosmeticId: String,
                val materialInstances: List<Material>
            ) {
                @Serializable
                data class Material(
                    val id: String,
                    val images: Map<String, @Serializable(with = UrlSerializer::class) URL>,
                    val colors: Map<String, String>,
                    val scalings: Map<String, Float>,
                    val flags: Map<String, Boolean>,
                )
            }

            @Serializable
            data class Section(
                val id: String,
                val index: Int,

                val name: String,
                val hidden: Boolean,

                val landingPriority: Int,

                val sortOffersByOwnership: Boolean,
                val showIneligibleOffers: Boolean,
                val showIneligibleOffersIfGiftable: Boolean,

                val showTimer: Boolean,
                val enableToastNotification: Boolean,
            )
        }
    }
}
