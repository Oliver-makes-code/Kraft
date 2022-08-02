package olivermakesco.de.kraft

import olivermakesco.de.kraft.client.KraftClient

fun main(args: Array<String>) {
    val client = KraftClient(758) // 1.18.2
    client.packetTest("localhost")
}
