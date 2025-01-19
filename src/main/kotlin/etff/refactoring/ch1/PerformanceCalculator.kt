package etff.refactoring.ch1

open class PerformanceCalculator(
    val performance: Performance,
    val play: Play,
) {
    open fun amount(): Int = throw Exception("서브클래스에서 처리하도록 설계되었습니다.")

    open fun volumeCredits(): Int = maxOf(performance.audience - 30, 0)

    companion object {
        fun createPerformanceCalculator(
            aPerformance: Performance,
            play: Play,
        ): PerformanceCalculator =
            when (play.type) {
                PlayType.TRAGEDY -> TragedyCalculator(aPerformance, play)
                PlayType.COMEDY -> ComedyCalculator(aPerformance, play)
            }
    }
}
