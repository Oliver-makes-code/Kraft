package olivermakesco.de.kraft.network.packet.clientbound.play

import olivermakesco.de.kraft.client.KraftClient
import olivermakesco.de.kraft.network.packet.PacketBuffer
import olivermakesco.de.kraft.network.packet.clientbound.ClientBoundPacket

class ClientBoundSpawnEntityPacket(val buffer: PacketBuffer) : ClientBoundPacket {
    val entityId = buffer.readVarInt().value
    val uuid = buffer.readUuid()
    val type = buffer.readVarInt().value
    val x = buffer.readDouble()
    val y = buffer.readDouble()
    val z = buffer.readDouble()
    val pitch = buffer.readAngle()
    val yaw = buffer.readAngle()
    val headYaw = buffer.readAngle()
    val data = buffer.readInt()
    val velocityX = buffer.readShort()
    val velocityY = buffer.readShort()
    val velocityZ = buffer.readShort()

    override fun handle(client: KraftClient) {
        TODO("Not yet implemented")
    }
}
