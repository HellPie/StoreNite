package dev.hellpie.storenite.models.utils

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.net.URL

@OptIn(ExperimentalSerializationApi::class)
@Serializer(forClass = URL::class)
object UrlSerializer : KSerializer<URL> {
    override val descriptor = PrimitiveSerialDescriptor("URI", PrimitiveKind.STRING)
    override fun deserialize(decoder: Decoder) = URL(decoder.decodeString())
    override fun serialize(encoder: Encoder, value: URL) = encoder.encodeString(value.toString())
}
