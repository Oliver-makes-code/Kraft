package olivermakesco.de.kraft.network.packet.clientbound.play

import olivermakesco.de.kraft.client.KraftClient
import olivermakesco.de.kraft.network.packet.PacketBuffer
import olivermakesco.de.kraft.network.packet.clientbound.ClientBoundPacket

class ClientBoundPlaySoundEffectPacket(buffer: PacketBuffer) : ClientBoundPacket {
    val name = buffer.readIdentifier()
    val category = buffer.readVarInt().value
    val x = buffer.readInt()
    val y = buffer.readInt()
    val z = buffer.readInt()
    val volume = buffer.readInt()
    val pitch = buffer.readFloat()
    val seed = buffer.readLong()

    override fun handle(client: KraftClient) {
        TODO("Not yet implemented")
    }
}
