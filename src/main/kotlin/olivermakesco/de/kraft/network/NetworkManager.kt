package olivermakesco.de.kraft.network

import olivermakesco.de.kraft.client.KraftClient
import olivermakesco.de.kraft.network.packet.clientbound.login.EncryptionRequestPacket
import olivermakesco.de.kraft.network.packet.serverbound.handshake.HandshakePacket
import olivermakesco.de.kraft.network.packet.serverbound.login.LoginStartPacket
import olivermakesco.de.kraft.network.packet.clientbound.login.LoginSuccessPacket
import olivermakesco.de.kraft.network.packet.clientbound.login.SetCompressionPacket
import olivermakesco.de.kraft.network.packet.clientbound.status.PongPacket
import olivermakesco.de.kraft.network.packet.clientbound.status.StatusResponsePacket
import olivermakesco.de.kraft.network.packet.serverbound.status.PingPacket
import olivermakesco.de.kraft.network.packet.serverbound.status.StatusRequestPacket
import olivermakesco.de.kraft.util.Logger

class NetworkManager(private val client: KraftClient) {
    var connection: Connection? = null
    lateinit var networkState: NetworkState

    fun connect(server: ServerAddress) {
        handshake(server.host, server.port, NetworkState.LOGIN)

        networkState = NetworkState.LOGIN
        connection!!.sendPacket(LoginStartPacket("Test"))
    }

    fun getStatus(server: ServerAddress) {
        handshake(server.host, server.port, NetworkState.STATUS)

        networkState = NetworkState.STATUS
        connection!!.sendPacket(StatusRequestPacket())
    }

    fun ping() {
        if (isConnected()) {
            connection!!.sendPacket(PingPacket())
        }
    }

    private fun handshake(host: String, port: Int, nextState: NetworkState) {
        networkState = NetworkState.HANDSHAKING
        connection = Connection(host, port, this)
        connection!!.sendPacket(HandshakePacket(client.version, host, port, nextState))
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
                    packet.handle(manager.client)

                    Logger.debug("Packet received: ${packet.javaClass.simpleName}")
                }

                Thread.sleep(100)
            }
        }
    }
}
