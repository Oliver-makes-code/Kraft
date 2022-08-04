package olivermakesco.de.kraft.network.packet.clientbound.play

import olivermakesco.de.kraft.client.KraftClient
import olivermakesco.de.kraft.network.packet.PacketBuffer
import olivermakesco.de.kraft.network.packet.clientbound.ClientBoundPacket

class ClientBoundExplosionPacket(buffer: PacketBuffer) : ClientBoundPacket {
    val x = buffer.readFloat()
    val y = buffer.readFloat()
    val z = buffer.readFloat()
    val strength = buffer.readFloat()
    private val blockCount = buffer.readVarInt().value
    val blocks = ArrayList<ExplodedBlock>(blockCount)
    val xVelocity = buffer.readFloat()
    val yVelocity = buffer.readFloat()
    val zVelocity = buffer.readFloat()


    init {
        for (i in 0 until blockCount) {
            blocks[i] = ExplodedBlock(buffer.readByte(), buffer.readByte(), buffer.readByte())
        }
    }

    override fun handle(client: KraftClient) {
        TODO("Not yet implemented")
    }

    data class ExplodedBlock(val xOffset: Byte, val yOffset: Byte, val zOffset: Byte)
}
