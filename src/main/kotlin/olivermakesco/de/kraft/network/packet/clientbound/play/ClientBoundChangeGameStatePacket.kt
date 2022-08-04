package olivermakesco.de.kraft.network.packet.clientbound.play

import olivermakesco.de.kraft.client.KraftClient
import olivermakesco.de.kraft.network.packet.PacketBuffer
import olivermakesco.de.kraft.network.packet.clientbound.ClientBoundPacket

class ClientBoundChangeGameStatePacket(buffer: PacketBuffer) : ClientBoundPacket {
    val reason = buffer.readByte()
    val value = buffer.readFloat()

    override fun handle(client: KraftClient) {
        TODO("Not yet implemented")
    }
}
