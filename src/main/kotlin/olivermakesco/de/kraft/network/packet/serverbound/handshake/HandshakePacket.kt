package olivermakesco.de.kraft.network.packet.serverbound.handshake

import olivermakesco.de.kraft.network.NetworkState
import olivermakesco.de.kraft.network.packet.PacketBuffer
import olivermakesco.de.kraft.network.packet.serverbound.ServerBoundPacket

class HandshakePacket(private val version: Int, private val addr: String, private val port: Int, private val nextState: NetworkState) : ServerBoundPacket {
    override val id = 0

    override fun write(buffer: PacketBuffer) {
        buffer += version
        buffer += addr
        buffer += port.toShort()
        buffer += nextState.id
    }
}
