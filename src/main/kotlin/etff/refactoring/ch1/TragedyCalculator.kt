package etff.refactoring.ch1

class TragedyCalculator(
    performance: Performance,
    play: Play,
) : PerformanceCalculator(performance, play) {
    override fun amount(): Int {
        var result = 40000
        if (performance.audience > 30) {
            result += 1000 * (performance.audience - 30)
        }
        return result
    }

    override fun volumeCredits(): Int = maxOf(performance.audience - 30, 0)
}
