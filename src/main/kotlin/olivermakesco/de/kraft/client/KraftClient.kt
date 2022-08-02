package olivermakesco.de.kraft.client

import olivermakesco.de.kraft.network.NetworkManager

class KraftClient(val version: Int) {
    private val networkManager = NetworkManager(this)

    fun packetTest(host: String) {
        networkManager.connect(host)
    }
}
