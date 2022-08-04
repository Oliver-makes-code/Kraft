package olivermakesco.de.kraft.network.packet.clientbound.play

import olivermakesco.de.kraft.client.KraftClient
import olivermakesco.de.kraft.network.packet.PacketBuffer
import olivermakesco.de.kraft.network.packet.clientbound.ClientBoundPacket

class ClientBoundSetCooldownPacket(buffer: PacketBuffer) : ClientBoundPacket {
    val itemId = buffer.readVarInt().value
    val ticks = buffer.readVarInt().value

    override fun handle(client: KraftClient) {
        TODO("Not yet implemented")
    }
}
