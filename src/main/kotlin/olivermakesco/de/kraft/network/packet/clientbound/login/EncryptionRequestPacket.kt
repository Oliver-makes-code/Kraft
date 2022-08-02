package olivermakesco.de.kraft.network.packet.clientbound.login

import olivermakesco.de.kraft.network.packet.clientbound.ClientBoundPacket
import olivermakesco.de.kraft.network.packet.PacketBuffer

class EncryptionRequestPacket(buffer: PacketBuffer) : ClientBoundPacket {
    val serverId = buffer.readString()
    val pubKeyLength = buffer.readInt()
    val puKey = buffer.readByteArray(pubKeyLength)
    val verifyTokenLength = buffer.readInt()
    val verifyToken = buffer.readByteArray(verifyTokenLength)
}
