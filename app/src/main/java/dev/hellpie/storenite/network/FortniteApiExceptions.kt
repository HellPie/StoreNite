package dev.hellpie.storenite.network

internal class FortniteApiException(val status: Int, val error: String) : Exception("Error $status: $error")
