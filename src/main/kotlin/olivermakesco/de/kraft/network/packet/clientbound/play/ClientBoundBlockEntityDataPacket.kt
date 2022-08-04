package olivermakesco.de.kraft.network.packet.clientbound.play

import olivermakesco.de.kraft.client.KraftClient
import olivermakesco.de.kraft.network.packet.PacketBuffer
import olivermakesco.de.kraft.network.packet.clientbound.ClientBoundPacket

class ClientBoundBlockEntityDataPacket(buffer: PacketBuffer) : ClientBoundPacket {
    val location = buffer.readPosition()
    val type = buffer.readVarInt().value
    val data: Nothing = TODO("NBT is not yet implemented")

    override fun handle(client: KraftClient) {
        TODO("Not yet implemented")
    }
}
