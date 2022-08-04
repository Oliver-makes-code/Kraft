package olivermakesco.de.kraft.network

import olivermakesco.de.kraft.client.KraftClient
import olivermakesco.de.kraft.network.packet.clientbound.login.EncryptionRequestPacket
import olivermakesco.de.kraft.network.packet.serverbound.handshake.ServerBoundHandshakePacket
import olivermakesco.de.kraft.network.packet.serverbound.login.ServerBoundLoginStartPacket
import olivermakesco.de.kraft.network.packet.serverbound.status.ServerBoundPingPacket
import olivermakesco.de.kraft.network.packet.serverbound.status.ServerBoundStatusRequestPacket
import olivermakesco.de.kraft.util.logger

class NetworkManager(private val client: KraftClient) {
    var connection: Connection? = null
    lateinit var networkState: NetworkState

    fun connect(server: ServerAddress) {
        handshake(server.host, server.port, NetworkState.LOGIN)

        networkState = NetworkState.LOGIN
        connection!!.sendPacket(ServerBoundLoginStartPacket("Test"))
    }

    fun getStatus(server: ServerAddress) {
        handshake(server.host, server.port, NetworkState.STATUS)

        networkState = NetworkState.STATUS
        connection!!.sendPacket(ServerBoundStatusRequestPacket())
    }

    fun ping() {
        if (isConnected()) {
            connection!!.sendPacket(ServerBoundPingPacket())
        }
    }

    private fun handshake(host: String, port: Int, nextState: NetworkState) {
        networkState = NetworkState.HANDSHAKING
        connection = Connection(host, port, this)
        connection!!.sendPacket(ServerBoundHandshakePacket(client.version, host, port, nextState))
    }

    fun setupEncryption(requestPacket: EncryptionRequestPacket) {
    }

    fun isConnected(): Boolean {
        return connection != null
    }

    companion object {
        fun start(manager: NetworkManager) {
            while (true) {
                if (manager.isConnected() && manager.connection!!.hasPacket()) {
                    val packet = manager.connection!!.readPacket()
                    logger.debug(manager.networkState.name)
                    packet.handle(manager.client)

                    logger.debug("Packet received: ${packet.javaClass.simpleName}")
                }

                Thread.sleep(100)
            }
        }
    }
}
