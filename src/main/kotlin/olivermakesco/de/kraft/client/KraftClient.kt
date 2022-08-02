package olivermakesco.de.kraft.client

import olivermakesco.de.kraft.network.NetworkManager

class KraftClient {
    private val networkManager = NetworkManager()

    fun packetTest(host: String) {
        networkManager.connect(host)
    }
}
