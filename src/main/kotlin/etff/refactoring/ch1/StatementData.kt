package etff.refactoring.ch1

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
        val calculator =
            PerformanceCalculator.createPerformanceCalculator(performance, playFor(performance, plays = plays))
        _play = calculator.play
        _amount = calculator.amount()
        _volumeCredits = calculator.volumeCredits()
        _totalAmount = totalAmount()
        _totalVolumeCredits = totalVolumeCredits()
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
