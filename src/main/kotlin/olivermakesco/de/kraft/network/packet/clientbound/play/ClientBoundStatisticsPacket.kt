package olivermakesco.de.kraft.network.packet.clientbound.play

import olivermakesco.de.kraft.client.KraftClient
import olivermakesco.de.kraft.network.packet.PacketBuffer
import olivermakesco.de.kraft.network.packet.clientbound.ClientBoundPacket

class ClientBoundStatisticsPacket(buffer: PacketBuffer) : ClientBoundPacket {
    val count = buffer.readVarInt().value
    val statistics: Nothing = TODO("You try understanding the spec")

    override fun handle(client: KraftClient) {
        TODO("Not yet implemented")
    }
}
