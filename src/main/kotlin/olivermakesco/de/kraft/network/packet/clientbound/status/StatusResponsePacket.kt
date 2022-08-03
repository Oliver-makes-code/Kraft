package olivermakesco.de.kraft.network.packet.clientbound.status

import olivermakesco.de.kraft.network.packet.PacketBuffer
import olivermakesco.de.kraft.network.packet.clientbound.ClientBoundPacket

class StatusResponsePacket(buffer: PacketBuffer) : ClientBoundPacket {
    val json = buffer.readString()
}
