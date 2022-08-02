package olivermakesco.de.kraft.network

class NetworkManager {
    private lateinit var networkState: NetworkState
    private lateinit var connection: ClientConnection

    fun connect(host: String) {
        val hostParts = parseAddress(host)

        handshake(hostParts.first, hostParts.second, NetworkState.LOGIN)
        loginStart(false) // TODO: encryption
    }

    // TODO: Actually do this at some point
    fun status(host: String) {
        val hostParts = parseAddress(host)

        handshake(hostParts.first, hostParts.second, NetworkState.STATUS)
    }

    private fun handshake(addr: String, port: Int, nextState: NetworkState) {
        networkState = NetworkState.HANDSHAKING
        connection = ClientConnection(addr, port)

        val packetData = Packet.Data()
        packetData += 758 // Protocol version
        packetData += addr
        packetData += port
        packetData += nextState.ordinal

        val handshakePacket = Packet(0x00, packetData)
        connection.sendPacket(handshakePacket)

        networkState = nextState
    }

    private fun loginStart(encrypted: Boolean) {
        val packetData = Packet.Data()
        packetData += "Test"
        packetData += encrypted

        val loginStartPacket = Packet(0x00, packetData)
        connection.sendPacket(loginStartPacket)
    }

    private fun parseAddress(addr: String): Pair<String, Int> {
        val addrSections = addr.split(':')
        val host = addrSections[0]
        val port = if (addrSections.size > 1) Integer.valueOf(addrSections[1]) else 25565

        return Pair(host, port)
    }
}
