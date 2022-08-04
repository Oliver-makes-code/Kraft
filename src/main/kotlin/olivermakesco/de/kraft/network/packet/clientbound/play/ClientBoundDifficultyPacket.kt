package olivermakesco.de.kraft.network.packet.clientbound.play

import olivermakesco.de.kraft.client.KraftClient
import olivermakesco.de.kraft.network.packet.PacketBuffer
import olivermakesco.de.kraft.network.packet.clientbound.ClientBoundPacket

class ClientBoundDifficultyPacket(buffer: PacketBuffer) : ClientBoundPacket {
    val difficulty = buffer.readByte()
    val locked = buffer.readBoolean()

    override fun handle(client: KraftClient) {
        TODO("Not yet implemented")
    }
}
