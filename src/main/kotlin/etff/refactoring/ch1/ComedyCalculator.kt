package etff.refactoring.ch1

import kotlin.math.floor

class ComedyCalculator(
    performance: Performance,
    play: Play,
) : PerformanceCalculator(performance, play) {
    override fun amount(): Int {
        var result = 30000
        if (performance.audience > 20) {
            result += 10000 + 500 * (performance.audience - 20)
        }
        result += 300 * performance.audience
        return result
    }

    override fun volumeCredits(): Int = maxOf(performance.audience - 30, 0) + floor(performance.audience / 5.0).toInt()
}
