package olivermakesco.de.kraft.network.packet.clientbound.play

import olivermakesco.de.kraft.client.KraftClient
import olivermakesco.de.kraft.network.packet.PacketBuffer
import olivermakesco.de.kraft.network.packet.clientbound.ClientBoundPacket

class ClientBoundSpawnExperienceOrbPacket(buffer: PacketBuffer) : ClientBoundPacket {
    val entityId = buffer.readVarInt().value
    val x = buffer.readDouble()
    val y = buffer.readDouble()
    val z = buffer.readDouble()
    val count = buffer.readShort()

    override fun handle(client: KraftClient) {
        TODO("Not yet implemented")
    }
}
