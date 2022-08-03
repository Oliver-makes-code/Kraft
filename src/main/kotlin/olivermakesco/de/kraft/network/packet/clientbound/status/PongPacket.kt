package olivermakesco.de.kraft.network.packet.clientbound.status

import olivermakesco.de.kraft.network.packet.PacketBuffer
import olivermakesco.de.kraft.network.packet.clientbound.ClientBoundPacket

class PongPacket(buffer: PacketBuffer) : ClientBoundPacket {
    val payload = buffer.readLong()
}
