package olivermakesco.de.kraft.network.packet.clientbound.login

import olivermakesco.de.kraft.network.packet.ClientBoundPacket
import olivermakesco.de.kraft.network.packet.PacketBuffer

class LoginDisconnectPacket(buffer: PacketBuffer) : ClientBoundPacket {
    val reason = buffer.readString()
}
