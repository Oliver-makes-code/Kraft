package olivermakesco.de.kraft.network.packet

import olivermakesco.de.kraft.network.NetworkState
import olivermakesco.de.kraft.network.PacketBuffer
import olivermakesco.de.kraft.network.ServerBoundPacket

class HandshakePacket(private val version: Int, private val addr: String, private val port: Int, private val nextState: NetworkState): ServerBoundPacket {
    override fun write(buffer: PacketBuffer) {
        buffer += version
        buffer += addr
        buffer += port.toShort()
        buffer += nextState.id
    }
}
