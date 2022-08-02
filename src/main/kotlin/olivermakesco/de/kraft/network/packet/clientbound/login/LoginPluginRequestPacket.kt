package olivermakesco.de.kraft.network.packet.clientbound.login

import olivermakesco.de.kraft.network.packet.ClientBoundPacket
import olivermakesco.de.kraft.network.packet.PacketBuffer

class LoginPluginRequestPacket(buffer: PacketBuffer) : ClientBoundPacket {
    val messageId = buffer.readInt()
    var channel = buffer.readString()
    var data = buffer.readByteArray()
}