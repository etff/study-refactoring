package etff.refactoring.ch1

fun main(args: Array<String>) {
    val jsonLoader = JsonLoader()
    val invoices: List<Invoice> = jsonLoader.load("src/main/resources/invoice.json")
    val statement = Statement()
    println(statement.statement(invoices[0]))
}
