package olivermakesco.de.kraft.client

import olivermakesco.de.kraft.network.NetworkManager
import olivermakesco.de.kraft.network.types.ServerAddress

class KraftClient() {
    val version = 758 // 1.18.2
    private val networkManager = NetworkManager(this)
    private val networkThread = Thread { NetworkManager.start(networkManager) }

    init {
        networkThread.start()
    }

    fun testConnection() {
        val server = ServerAddress("localhost")

        networkManager.getStatus(server)
        networkManager.connect(server)
    }
}
