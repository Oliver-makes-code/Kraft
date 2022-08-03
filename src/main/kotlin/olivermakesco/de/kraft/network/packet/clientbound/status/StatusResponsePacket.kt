package olivermakesco.de.kraft.network.packet.clientbound.status

import olivermakesco.de.kraft.client.KraftClient
import olivermakesco.de.kraft.network.packet.PacketBuffer
import olivermakesco.de.kraft.network.packet.clientbound.ClientBoundPacket

class StatusResponsePacket(buffer: PacketBuffer) : ClientBoundPacket {
    val json = buffer.readString()

    override fun handle(client: KraftClient) {
        println(json)
        client.networkManager.ping()
    }
}
