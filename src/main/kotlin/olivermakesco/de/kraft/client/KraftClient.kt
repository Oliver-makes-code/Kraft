package olivermakesco.de.kraft.client

import olivermakesco.de.kraft.network.NetworkManager
import olivermakesco.de.kraft.network.ServerAddress

class KraftClient() {
    val version = 758 // 1.18.2
    val networkManager = NetworkManager(this)
    private val networkThread = Thread { NetworkManager.start(networkManager) }

    init {
        networkThread.start()
    }

    fun testConnection(address: String) {
        val server = ServerAddress.fromString(address)

        networkManager.getStatus(server)
        Thread.sleep(500)
        networkManager.connect(server)
    }
}
