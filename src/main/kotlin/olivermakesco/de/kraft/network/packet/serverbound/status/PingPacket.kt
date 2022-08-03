package olivermakesco.de.kraft.network.packet.serverbound.status

import olivermakesco.de.kraft.network.packet.PacketBuffer
import olivermakesco.de.kraft.network.packet.serverbound.ServerBoundPacket

class PingPacket(private val payload: Long) : ServerBoundPacket {
    override val id = 0x01

    override fun write(buffer: PacketBuffer) {
        buffer += payload
    }
}
