package olivermakesco.de.kraft.network.packet.clientbound.play

import olivermakesco.de.kraft.client.KraftClient
import olivermakesco.de.kraft.network.packet.PacketBuffer
import olivermakesco.de.kraft.network.packet.clientbound.ClientBoundPacket

class ClientBoundUnloadChunkPacket(buffer: PacketBuffer) : ClientBoundPacket {
    val x = buffer.readInt()
    val z = buffer.readInt()

    override fun handle(client: KraftClient) {
        TODO("Not yet implemented")
    }
}
