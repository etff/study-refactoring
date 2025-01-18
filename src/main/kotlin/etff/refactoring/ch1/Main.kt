package etff.refactoring.ch1

fun main(args: Array<String>) {
    val jsonLoader = JsonLoader()
    val invoices: List<Invoice> = jsonLoader.load("src/main/resources/invoice.json")
    val plays = Plays(jsonLoader.load<Map<String, Play>>("src/main/resources/plays.json"))

    val statement = Statement()
    println(statement.statement(invoices[0], plays))
}
