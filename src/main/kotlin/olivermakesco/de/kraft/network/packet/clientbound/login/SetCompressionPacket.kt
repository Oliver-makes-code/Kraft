package olivermakesco.de.kraft.network.packet.clientbound.login

import olivermakesco.de.kraft.network.packet.ClientBoundPacket
import olivermakesco.de.kraft.network.packet.PacketBuffer

class SetCompressionPacket(buffer: PacketBuffer) : ClientBoundPacket {
    val threshold = buffer.readInt()
}
