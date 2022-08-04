package olivermakesco.de.kraft.network.packet.clientbound.play

import olivermakesco.de.kraft.client.KraftClient
import olivermakesco.de.kraft.network.packet.PacketBuffer
import olivermakesco.de.kraft.network.packet.clientbound.ClientBoundPacket

class ClientBoundDisconnectPacket(buffer: PacketBuffer) : ClientBoundPacket {
    val reason = buffer.readChat()

    override fun handle(client: KraftClient) {
        TODO("Not yet implemented")
    }
}
