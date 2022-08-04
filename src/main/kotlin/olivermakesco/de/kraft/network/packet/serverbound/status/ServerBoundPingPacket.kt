package olivermakesco.de.kraft.network.packet.serverbound.status

import olivermakesco.de.kraft.network.packet.PacketBuffer
import olivermakesco.de.kraft.network.packet.serverbound.ServerBoundPacket

class ServerBoundPingPacket : ServerBoundPacket {
    override val id = 0x01

    override fun write(buffer: PacketBuffer) {
        buffer += System.currentTimeMillis()
    }
}
