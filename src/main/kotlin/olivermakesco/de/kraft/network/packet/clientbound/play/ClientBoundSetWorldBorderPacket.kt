package olivermakesco.de.kraft.network.packet.clientbound.play

import olivermakesco.de.kraft.client.KraftClient
import olivermakesco.de.kraft.network.packet.PacketBuffer
import olivermakesco.de.kraft.network.packet.clientbound.ClientBoundPacket

class ClientBoundSetWorldBorderPacket(buffer: PacketBuffer) : ClientBoundPacket {
    val x = buffer.readDouble()
    val z = buffer.readDouble()
    val oldDiameter = buffer.readDouble()
    val newDiameter = buffer.readDouble()
    val speed = buffer.readVarLong().value
    val portalTeleportBoundary = buffer.readVarInt().value
    val warningDistance = buffer.readVarInt().value
    val warningTime = buffer.readVarInt().value

    override fun handle(client: KraftClient) {
        TODO("Not yet implemented")
    }
}
