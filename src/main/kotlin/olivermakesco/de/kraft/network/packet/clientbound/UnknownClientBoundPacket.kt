package olivermakesco.de.kraft.network.packet.clientbound

import olivermakesco.de.kraft.client.KraftClient
import olivermakesco.de.kraft.network.packet.PacketBuffer
import olivermakesco.de.kraft.util.logger

class UnknownClientBoundPacket(val buffer: PacketBuffer, val id: Int) : ClientBoundPacket {
    override fun handle(client: KraftClient) {
        logger.warn("Client received unknown packet! ID: $id")
    }
}