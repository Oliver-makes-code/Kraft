package olivermakesco.de.kraft.network.packet.clientbound.login

import olivermakesco.de.kraft.client.KraftClient
import olivermakesco.de.kraft.network.packet.clientbound.ClientBoundPacket
import olivermakesco.de.kraft.network.packet.PacketBuffer

class ClientBoundLoginPluginRequestPacket(buffer: PacketBuffer) : ClientBoundPacket {
    val messageId = buffer.readVarInt().value
    var channel = buffer.readIdentifier()
    var data = buffer.readByteArray()

    override fun handle(client: KraftClient) {
        TODO()
    }
}
