package olivermakesco.de.kraft

import olivermakesco.de.kraft.client.KraftClient

fun main(args: Array<String>) {
    val client = KraftClient()

    client.testConnection("localhost")
}
