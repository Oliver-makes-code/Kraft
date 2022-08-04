package olivermakesco.de.kraft.network.packet.clientbound.login

import olivermakesco.de.kraft.client.KraftClient
import olivermakesco.de.kraft.network.packet.clientbound.ClientBoundPacket
import olivermakesco.de.kraft.network.packet.PacketBuffer

class ClientBoundEncryptionRequestPacket(buffer: PacketBuffer) : ClientBoundPacket {
    val serverId = buffer.readString()
    private val pubKeyLength = buffer.readVarInt().value
    val publicKey = buffer.readByteArray(pubKeyLength)
    private val verifyTokenLength = buffer.readVarInt().value
    val verifyToken = buffer.readByteArray(verifyTokenLength)

    override fun handle(client: KraftClient) {
        error("Kraft doesn't support online-mode servers yet")
    }
}
