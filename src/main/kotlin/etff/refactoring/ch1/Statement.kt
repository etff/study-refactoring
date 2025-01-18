@file:Suppress("ktlint:standard:no-wildcard-imports")

package etff.refactoring.ch1

import java.text.NumberFormat
import java.util.Locale
import kotlin.math.floor

class Statement {
    fun statement(
        invoice: Invoice,
        plays: Plays,
    ): String {
        val result = StringBuilder("청구내역 (고객명: ${invoice.customer})\n")
        for (perf in invoice.performances) {
            // 청구 내역을 출력한다.
            result.append(
                "${playFor(perf, plays).name}: ${
                    usd(amountFor(perf, plays) / 100)
                } ${perf.audience}석\n",
            )
        }
        result.append(String.format("총액: %s\n", usd(totalAmount(invoice, plays) / 100.0)))
        result.append(String.format("적립 포인트: %d점", totalVolumeCredits(invoice, plays)))
        return result.toString()
    }

    private fun playFor(
        aPerformance: Performance,
        plays: Plays,
    ) = plays.playFor(aPerformance.playID)

    private fun totalAmount(
        invoice: Invoice,
        plays: Plays,
    ): Int {
        var result = 0
        for (perf in invoice.performances) {
            result += amountFor(perf, plays)
        }
        return result
    }

    private fun totalVolumeCredits(
        invoice: Invoice,
        plays: Plays,
    ): Int {
        var result = 0
        for (perf in invoice.performances) {
            result += volumeCreditsFor(perf, plays)
        }
        return result
    }

    private fun volumeCreditsFor(
        aPerformance: Performance,
        plays: Plays,
    ): Int {
        var result = 0
        result += (aPerformance.audience - 30).coerceAtLeast(0)
        if (playFor(aPerformance, plays).type == PlayType.COMEDY) {
            result += floor(aPerformance.audience.toDouble() / 5).toInt()
        }
        return result
    }

    val usd: (Number) -> String = { number ->
        NumberFormat
            .getCurrencyInstance(Locale.US)
            .apply {
                minimumFractionDigits = 2
            }.format(number)
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
}
