package olivermakesco.de.kraft.network.packet.clientbound.play

import olivermakesco.de.kraft.client.KraftClient
import olivermakesco.de.kraft.network.packet.PacketBuffer
import olivermakesco.de.kraft.network.packet.clientbound.ClientBoundPacket

class ClientBoundKeepAlivePacket(buffer: PacketBuffer) : ClientBoundPacket {
    val payload = buffer.readLong()

    override fun handle(client: KraftClient) {
        TODO("Not yet implemented")
    }
}
