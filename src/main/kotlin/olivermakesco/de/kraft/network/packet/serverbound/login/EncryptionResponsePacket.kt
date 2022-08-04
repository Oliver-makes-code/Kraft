package olivermakesco.de.kraft.network.packet.serverbound.login

import olivermakesco.de.kraft.network.packet.PacketBuffer
import olivermakesco.de.kraft.network.packet.serverbound.ServerBoundPacket
import olivermakesco.de.kraft.network.types.VarInt

class EncryptionResponsePacket(private val sharedSecret: ByteArray, private val verifyToken: ByteArray) : ServerBoundPacket {
    override val id = 0x01

    override fun write(buffer: PacketBuffer) {
        buffer += VarInt(sharedSecret.size)
        buffer += sharedSecret
        buffer += VarInt(verifyToken.size)
        buffer += verifyToken
    }
}
