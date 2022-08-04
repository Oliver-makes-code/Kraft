package olivermakesco.de.kraft.network.packet.clientbound.play

import olivermakesco.de.kraft.client.KraftClient
import olivermakesco.de.kraft.network.packet.PacketBuffer
import olivermakesco.de.kraft.network.packet.clientbound.ClientBoundPacket

class ClientBoundPluginMessagePacket(buffer: PacketBuffer) : ClientBoundPacket {
    val channel = buffer.readIdentifier()
    val data = buffer.readByteArray()

    override fun handle(client: KraftClient) {
        TODO("Not yet implemented")
    }
}
