package etff.refactoring.ch1

import kotlin.math.floor

data class StatementData(
    val customer: String,
    val performances: List<Performance>,
) {
    private lateinit var _play: Play
    private var _amount = 0
    private var _volumeCredits = 0
    private var _totalAmount = 0
    private var _totalVolumeCredits = 0

    private fun playFor(
        aPerformance: Performance,
        plays: Plays,
    ) = plays.playFor(aPerformance.playID)

    fun enrichPerformance(
        performance: Performance,
        plays: Plays,
    ) {
        _play = playFor(performance, plays)
        _amount = amountFor(performance, plays)
        _volumeCredits = volumeCreditsFor(performance)
        _totalAmount = totalAmount()
        _totalVolumeCredits = totalVolumeCredits()
    }

    private fun amountFor(
        aPerformance: Performance,
        plays: Plays,
    ): Int {
        var result = 0

        when (playFor(aPerformance, plays).type) {
            PlayType.TRAGEDY -> {
                result = 40000
                if (aPerformance.audience > 30) {
                    result += 1000 * (aPerformance.audience - 30)
                }
            }

            PlayType.COMEDY -> {
                result = 30000
                if (aPerformance.audience > 20) {
                    result += 10000 + 500 * (aPerformance.audience - 20)
                }
                result += 300 * aPerformance.audience
            }

            else -> throw Exception("알 수 없는 장르")
        }
        return result
    }

    private fun volumeCreditsFor(aPerformance: Performance): Int {
        var result = 0
        result += (aPerformance.audience - 30).coerceAtLeast(0)
        if (play.type == PlayType.COMEDY) {
            result += floor(aPerformance.audience.toDouble() / 5).toInt()
        }
        return result
    }

    private fun totalVolumeCredits(): Int {
        var result = 0
        for (perf in performances) {
            result += volumeCredits
        }
        return result
    }

    private fun totalAmount(): Int {
        var result = 0
        for (perf in performances) {
            result += amount
        }
        return result
    }

    val play: Play
        get() = _play

    val amount: Int
        get() = _amount

    val volumeCredits: Int
        get() = _volumeCredits

    val totalAmount: Int
        get() = _totalAmount

    val totalVolumeCredits: Int
        get() = _totalVolumeCredits
}
