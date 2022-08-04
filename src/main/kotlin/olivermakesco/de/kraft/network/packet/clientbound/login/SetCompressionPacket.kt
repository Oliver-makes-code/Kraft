package olivermakesco.de.kraft.network.packet.clientbound.login

import olivermakesco.de.kraft.client.KraftClient
import olivermakesco.de.kraft.network.packet.clientbound.ClientBoundPacket
import olivermakesco.de.kraft.network.packet.PacketBuffer

class SetCompressionPacket(buffer: PacketBuffer) : ClientBoundPacket {
    val threshold = buffer.readVarInt().value

    override fun handle(client: KraftClient) {
        client.networkManager.connection!!.compressionThreshold = threshold
    }
}
