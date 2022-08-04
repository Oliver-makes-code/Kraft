package olivermakesco.de.kraft.network.packet.clientbound.play

import olivermakesco.de.kraft.client.KraftClient
import olivermakesco.de.kraft.network.packet.PacketBuffer
import olivermakesco.de.kraft.network.packet.clientbound.ClientBoundPacket

class ClientBoundWindowItemsPacket(buffer: PacketBuffer) : ClientBoundPacket {
    val windowId = buffer.readByte()
    val stateId = buffer.readVarInt().value
    val count = buffer.readVarInt().value
    val slotData: Nothing = TODO("Slot class")
    val carriedItem: Nothing = TODO("Slot class 2: electric boogaloo")

    override fun handle(client: KraftClient) {
        TODO("Not yet implemented")
    }
}
