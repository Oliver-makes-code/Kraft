package olivermakesco.de.kraft

import olivermakesco.de.kraft.client.KraftClient
import olivermakesco.de.kraft.network.types.VarInt
import java.net.ServerSocket

fun main(args: Array<String>) {
    val client = KraftClient(758) // 1.18.2
    client.packetTest("localhost")
}
