package olivermakesco.de.kraft.network.packet.clientbound.login

import olivermakesco.de.kraft.client.KraftClient
import olivermakesco.de.kraft.network.packet.clientbound.ClientBoundPacket
import olivermakesco.de.kraft.network.packet.PacketBuffer
import olivermakesco.de.kraft.util.logger

class ClientBoundLoginDisconnectPacket(buffer: PacketBuffer) : ClientBoundPacket {
    val reason = buffer.readString()

    override fun handle(client: KraftClient) {
        logger.info("Disconnected: $reason")
    }
}
