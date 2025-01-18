package etff.refactoring.ch1

import kotlinx.serialization.Serializable

@Serializable
data class Performance(
    val playID: String,
    val audience: Int,
)

@Serializable
data class Invoice(
    val customer: String,
    val performances: List<Performance>,
)
