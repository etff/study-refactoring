package etff.refactoring.ch1

import kotlin.math.floor

class Statement {
    fun statement(
        invoice: Invoice,
        plays: Plays,
    ): String {
        var totalAmount = 0
        var volumeCredit = 0
        val result = StringBuilder("청구내역 (고객명: ${invoice.customer})\n")
        for (performance in invoice.performances) {
            val play: Play? = plays.get(performance)
            var thisAmount = 0

            when (play?.type) {
                PlayType.TRAGEDY -> {
                    thisAmount = 40000
                    if (performance.audience > 30) {
                        thisAmount += 1000 * (performance.audience - 30)
                    }
                }

                PlayType.COMEDY -> {
                    thisAmount = 30000
                    if (performance.audience > 20) {
                        thisAmount += 10000 + 500 * (performance.audience - 20)
                    }
                    thisAmount += 300 * performance.audience
                }

                else -> throw Exception("알 수 없는 장르")
            }
            // 포인트를 적립한다.
            volumeCredit += (performance.audience - 30).coerceAtLeast(0)

            // 희극 관객 5명마다 추가 포인트를 제공한다.
            if (play.type == PlayType.COMEDY) {
                volumeCredit += floor(performance.audience.toDouble() / 5).toInt()
            }

            // 청구 내역을 출력한다.
            result.append(
                java.lang.String.format(
                    "%s: $%d %d석\n",
                    play.name,
                    thisAmount / 100,
                    performance.audience,
                ),
            )
            totalAmount += thisAmount
        }

        result.append(String.format("총액: $%d\n", totalAmount / 100))
        result.append(String.format("적립 포인트: %d점", volumeCredit))
        return result.toString()
    }
}
