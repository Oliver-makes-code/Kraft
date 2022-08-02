package olivermakesco.de.kraft.network.packet.clientbound.login

import olivermakesco.de.kraft.network.packet.ClientBoundPacket
import olivermakesco.de.kraft.network.packet.PacketBuffer

class LoginSuccessPacket(buffer: PacketBuffer) : ClientBoundPacket {
    val uuid = buffer.readUuid()
    val name = buffer.readString()
}
