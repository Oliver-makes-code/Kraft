package olivermakesco.de.kraft.network.packet.serverbound.login

import olivermakesco.de.kraft.network.packet.PacketBuffer
import olivermakesco.de.kraft.network.packet.serverbound.ServerBoundPacket

class EncryptionResponsePacket(private val sharedSecret: ByteArray, private val verifyToken: ByteArray) : ServerBoundPacket {
    override val id = 0x01

    override fun write(buffer: PacketBuffer) {
        buffer += sharedSecret.size
        buffer += sharedSecret
        buffer += verifyToken.size
        buffer += verifyToken
    }
}
