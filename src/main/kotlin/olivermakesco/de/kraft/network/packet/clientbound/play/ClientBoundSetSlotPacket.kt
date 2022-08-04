package olivermakesco.de.kraft.network.packet.clientbound.play

import olivermakesco.de.kraft.client.KraftClient
import olivermakesco.de.kraft.network.packet.PacketBuffer
import olivermakesco.de.kraft.network.packet.clientbound.ClientBoundPacket

class ClientBoundSetSlotPacket(buffer: PacketBuffer) : ClientBoundPacket {
    val windowId = buffer.readByte()
    val stateId = buffer.readByte()
    val slot = buffer.readShort()
    val data: Nothing = TODO("Slot class needed")

    override fun handle(client: KraftClient) {
        TODO("Not yet implemented")
    }
}
