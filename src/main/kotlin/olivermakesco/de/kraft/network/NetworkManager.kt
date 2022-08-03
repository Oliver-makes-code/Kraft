package olivermakesco.de.kraft.network

import olivermakesco.de.kraft.client.KraftClient
import olivermakesco.de.kraft.network.packet.serverbound.handshake.HandshakePacket
import olivermakesco.de.kraft.network.packet.serverbound.login.LoginStartPacket
import olivermakesco.de.kraft.network.packet.clientbound.login.LoginSuccessPacket
import olivermakesco.de.kraft.network.packet.clientbound.status.PongPacket
import olivermakesco.de.kraft.network.packet.clientbound.status.StatusResponsePacket
import olivermakesco.de.kraft.network.packet.serverbound.status.PingPacket
import olivermakesco.de.kraft.network.packet.serverbound.status.StatusRequestPacket
import olivermakesco.de.kraft.network.types.ServerAddress

class NetworkManager(private val client: KraftClient) {
    private var connection: Connection? = null
    lateinit var networkState: NetworkState

    fun connect(server: ServerAddress) {
        handshake(server.host, server.port, NetworkState.LOGIN)

        networkState = NetworkState.LOGIN
        connection!!.sendPacket(LoginStartPacket("Test"))

        val response = connection!!.readPacket()

        if (response is LoginSuccessPacket) {
            println("Successfully logged in as ${response.name}.")
        }
    }

    fun getStatus(server: ServerAddress) {
        handshake(server.host, server.port, NetworkState.STATUS)

        networkState = NetworkState.STATUS
        connection!!.sendPacket(StatusRequestPacket())

        val response = connection!!.readPacket()

        if (response is StatusResponsePacket) {
            println(response.json)
            ping()
        }
    }

    private fun ping() {
        connection!!.sendPacket(PingPacket(System.currentTimeMillis()))

        val response = connection!!.readPacket()

        if (response is PongPacket) {
            println("Pong!")
        }
    }

    private fun handshake(host: String, port: Int, nextState: NetworkState) {
        networkState = NetworkState.HANDSHAKING
        connection = Connection(host, port, this)
        connection!!.sendPacket(HandshakePacket(client.version, host, port, nextState))
    }

    fun isConnected(): Boolean {
        return connection != null
    }

    companion object {
        fun start(manager: NetworkManager) {
            while (true) {
                if (manager.isConnected() && manager.connection!!.hasPacket()) {
                    println(manager.connection!!.readPacket().javaClass.simpleName)
                }

                Thread.sleep(100)
            }
        }
    }
}
