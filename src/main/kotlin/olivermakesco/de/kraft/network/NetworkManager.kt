package olivermakesco.de.kraft.network

import olivermakesco.de.kraft.client.KraftClient
import olivermakesco.de.kraft.network.packet.serverbound.handshake.HandshakePacket
import olivermakesco.de.kraft.network.packet.serverbound.login.LoginStartPacket
import olivermakesco.de.kraft.network.packet.clientbound.login.LoginSuccessPacket

class NetworkManager(private val client: KraftClient) {
    private lateinit var connection: Connection
    lateinit var networkState: NetworkState

    fun connect(host: String) {
        val (addr, port) = parseAddress(host)

        networkState = NetworkState.HANDSHAKING
        connection = Connection(addr, port, this)
        connection.sendPacket(HandshakePacket(client.version, addr, port, NetworkState.LOGIN))

        networkState = NetworkState.LOGIN
        connection.sendPacket(LoginStartPacket("Test"))

        val response = connection.readPacket()

        if (response is LoginSuccessPacket) {
            println("Successfully logged in as ${response.name}.")
        }
    }

    private fun parseAddress(addr: String): Pair<String, Int> {
        val addrSections = addr.split(':')
        val host = addrSections[0]
        val port = if (addrSections.size > 1) Integer.valueOf(addrSections[1]) else 25565

        return Pair(host, port)
    }
}
