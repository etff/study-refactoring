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
    private val items: Map<String, Play>,
) {
    fun playFor(playID: String) = items[playID] ?: throw Exception("연극을 찾을 수 없습니다.")
}
