package olivermakesco.de.kraft

import olivermakesco.de.kraft.client.KraftClient
import java.net.ServerSocket

fun main(args: Array<String>) {
    Thread{ testServer() }.start()

    val client = KraftClient()
    client.packetTest("localhost")
}

fun testServer() {
    val server = ServerSocket(25565)
    val connection = server.accept()

    while (true) {
        if (connection.getInputStream().available() > 1) {
            println(connection.getInputStream().read())
        }
    }
}
