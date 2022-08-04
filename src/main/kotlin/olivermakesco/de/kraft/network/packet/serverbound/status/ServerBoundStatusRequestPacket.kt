package olivermakesco.de.kraft.network.packet.serverbound.status

import olivermakesco.de.kraft.network.packet.PacketBuffer
import olivermakesco.de.kraft.network.packet.serverbound.ServerBoundPacket

class ServerBoundStatusRequestPacket : ServerBoundPacket {
    override val id = 0x00

    override fun write(buffer: PacketBuffer) {} // Empty payload
}
