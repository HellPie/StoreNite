package dev.hellpie.storenite.network

import dev.hellpie.storenite.models.Cosmetics
import dev.hellpie.storenite.models.CosmeticsDiff
import dev.hellpie.storenite.models.CosmeticsStoreSet
import kotlinx.serialization.Serializable

@Serializable
internal class FortniteApiResponse<T>(
    val status: Int,
    val error: String? = null,
    val data: T? = null,
)

internal typealias CosmeticsResponse = FortniteApiResponse<Cosmetics>
internal typealias CosmeticsListResponse = FortniteApiResponse<List<Cosmetics>>
internal typealias CosmeticsDiffResponse = FortniteApiResponse<CosmeticsDiff>

internal typealias ShopResponse = FortniteApiResponse<CosmeticsStoreSet>

internal typealias ErrorResponse = FortniteApiResponse<Unit>
