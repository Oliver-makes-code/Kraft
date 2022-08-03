package olivermakesco.de.kraft.network.packet.clientbound.login

import olivermakesco.de.kraft.client.KraftClient
import olivermakesco.de.kraft.network.packet.clientbound.ClientBoundPacket
import olivermakesco.de.kraft.network.packet.PacketBuffer

class EncryptionRequestPacket(buffer: PacketBuffer) : ClientBoundPacket {
    val serverId = buffer.readString()
    private val pubKeyLength = buffer.readInt()
    val publicKey = buffer.readByteArray(pubKeyLength)
    private val verifyTokenLength = buffer.readInt()
    val verifyToken = buffer.readByteArray(verifyTokenLength)

    override fun handle(client: KraftClient) {
        client.networkManager.setupEncryption(this)
    }
}
