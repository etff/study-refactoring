package etff.refactoring.ch1

import etff.refactoring.ch1.Plays.playFor
import kotlin.math.floor

class Statement {
    fun statement(invoice: Invoice): String {
        var totalAmount = 0
        var volumeCredit = 0
        val result = StringBuilder("청구내역 (고객명: ${invoice.customer})\n")
        for (perf in invoice.performances) {
            // 포인트를 적립한다.
            volumeCredit += (perf.audience - 30).coerceAtLeast(0)

            // 희극 관객 5명마다 추가 포인트를 제공한다.
            if (playFor(perf.playID).type == PlayType.COMEDY) {
                volumeCredit += floor(perf.audience.toDouble() / 5).toInt()
            }

            // 청구 내역을 출력한다.
            result.append(
                "${playFor(perf.playID).name}: $${
                    amountFor(perf) / 100
                } ${perf.audience}석\n",
            )
            totalAmount += amountFor(perf)
        }

        result.append(String.format("총액: $%d\n", totalAmount / 100))
        result.append(String.format("적립 포인트: %d점", volumeCredit))
        return result.toString()
    }

    private fun amountFor(aPerformance: Performance): Int {
        var result = 0

        when (playFor(aPerformance.playID).type) {
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
