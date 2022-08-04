package olivermakesco.de.kraft.network.packet.serverbound.handshake

import olivermakesco.de.kraft.network.NetworkState
import olivermakesco.de.kraft.network.packet.PacketBuffer
import olivermakesco.de.kraft.network.packet.serverbound.ServerBoundPacket
import olivermakesco.de.kraft.network.types.VarInt

class ServerBoundHandshakePacket(private val version: Int, private val addr: String, private val port: Int, private val nextState: NetworkState) : ServerBoundPacket {
    override val id = 0x00

    override fun write(buffer: PacketBuffer) {
        buffer += VarInt(version)
        buffer += addr
        buffer += port.toShort()
        buffer += VarInt(nextState.id)
    }
}
