package olivermakesco.de.kraft.network.packet.clientbound.play

import olivermakesco.de.kraft.client.KraftClient
import olivermakesco.de.kraft.network.packet.PacketBuffer
import olivermakesco.de.kraft.network.packet.clientbound.ClientBoundPacket

class ClientBoundClearTitlesPacket(buffer: PacketBuffer) : ClientBoundPacket {
    val reset = buffer.readBoolean()

    override fun handle(client: KraftClient) {
        TODO("Not yet implemented")
    }
}
