package etff.refactoring.ch1

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class PlayType {
    @SerialName("tragedy")
    TRAGEDY,

    @SerialName("comedy")
    COMEDY,
}

@Serializable
data class Play(
    val name: String,
    val type: PlayType,
)

class Plays(
    private val playMap: Map<String, Play>,
) {
    fun get(performance: Performance): Play? = playMap[performance.playID]
}
