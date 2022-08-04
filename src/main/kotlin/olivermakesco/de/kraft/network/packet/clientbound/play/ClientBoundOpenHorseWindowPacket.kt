package olivermakesco.de.kraft.network.packet.clientbound.play

import olivermakesco.de.kraft.client.KraftClient
import olivermakesco.de.kraft.network.packet.PacketBuffer
import olivermakesco.de.kraft.network.packet.clientbound.ClientBoundPacket

class ClientBoundOpenHorseWindowPacket(buffer: PacketBuffer) : ClientBoundPacket {
    val windowId = buffer.readByte()
    val slotCount = buffer.readVarInt().value
    val entityId = buffer.readInt()

    override fun handle(client: KraftClient) {
        TODO("Not yet implemented")
    }
}
