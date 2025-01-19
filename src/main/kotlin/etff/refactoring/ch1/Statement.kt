@file:Suppress("ktlint:standard:no-wildcard-imports")

package etff.refactoring.ch1

import java.text.NumberFormat
import java.util.Locale

class Statement {
    fun htmlStatement(
        invoice: Invoice,
        plays: Plays,
    ): String =
        renderHtml(
            StatementData(
                invoice.customer,
                invoice.performances.map { it.copy() },
            ),
            plays,
        )

    private fun renderHtml(
        statementData: StatementData,
        plays: Plays,
    ): String {
        val result = StringBuilder("<h1>청구내역 (고객명: ${statementData.customer})</h1>\n")
        result.append("<table>\n")
        result.append("<tr><th>연극</th><th>좌석 수</th><th>금액</th></tr>")
        for (perf in statementData.performances) {
            statementData.enrichPerformance(perf, plays)
            result.append(
                "<tr><td>${statementData.play.name}</td><td>${perf.audience}석</td><td>${usd(
                    statementData.amount / 100,
                )}</td></tr>\n",
            )
        }
        result.append("</table>\n")
        result.append("<p>총액: <em>${usd(statementData.totalAmount / 100.0)}</em></p>\n")
        result.append("<p>적립 포인트: <em>${statementData.totalVolumeCredits}</em>점</p>\n")
        return result.toString()
    }

    fun statement(
        invoice: Invoice,
        plays: Plays,
    ): String {
        val statementData =
            StatementData(
                invoice.customer,
                invoice.performances.map { it.copy() },
            )
        return renderPlainText(statementData, plays)
    }

    private fun renderPlainText(
        statementData: StatementData,
        plays: Plays,
    ): String {
        val result = StringBuilder("청구내역 (고객명: ${statementData.customer})\n")
        for (perf in statementData.performances) {
            statementData.enrichPerformance(perf, plays)
            // 청구 내역을 출력한다.
            result.append(
                "${statementData.play.name}: ${
                    usd(statementData.amount / 100)
                } ${perf.audience}석\n",
            )
        }
        result.append(String.format("총액: %s\n", usd(statementData.totalAmount / 100.0)))
        result.append(String.format("적립 포인트: %d점", statementData.totalVolumeCredits))
        return result.toString()
    }

    val usd: (Number) -> String = { number ->
        NumberFormat
            .getCurrencyInstance(Locale.US)
            .apply {
                minimumFractionDigits = 2
            }.format(number)
    }
}
