package olivermakesco.de.kraft.client

import olivermakesco.de.kraft.network.NetworkManager
import olivermakesco.de.kraft.network.ServerAddress
import kotlin.concurrent.thread

class KraftClient {
    val version = 758 // 1.18.2
    val networkManager = NetworkManager(this)
    private val networkThread = thread(name="Networking") { NetworkManager.start(networkManager) }

    fun testConnection(address: String) {
        val server = ServerAddress.fromString(address)

        networkManager.getStatus(server)
        Thread.sleep(500)
        networkManager.connect(server)
    }
}
