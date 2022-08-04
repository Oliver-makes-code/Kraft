package olivermakesco.de.kraft.network.packet.clientbound.play

import olivermakesco.de.kraft.client.KraftClient
import olivermakesco.de.kraft.network.packet.PacketBuffer
import olivermakesco.de.kraft.network.packet.clientbound.ClientBoundPacket

class ClientBoundBlockBreakAnimationPacket(buffer: PacketBuffer) : ClientBoundPacket {
    val entityId = buffer.readVarInt().value
    val location = buffer.readPosition()
    val destroyStage = buffer.readByte()

    override fun handle(client: KraftClient) {
        TODO("Not yet implemented")
    }
}
