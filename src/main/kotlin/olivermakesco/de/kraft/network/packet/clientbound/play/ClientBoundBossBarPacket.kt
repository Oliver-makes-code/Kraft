package olivermakesco.de.kraft.network.packet.clientbound.play

import olivermakesco.de.kraft.client.KraftClient
import olivermakesco.de.kraft.network.packet.PacketBuffer
import olivermakesco.de.kraft.network.packet.clientbound.ClientBoundPacket

class ClientBoundBossBarPacket(buffer: PacketBuffer) : ClientBoundPacket {
    val uuid = buffer.readUuid()
    val action = buffer.readVarInt().value
    val data: Nothing = TODO("Need to figure out action data storage")

    override fun handle(client: KraftClient) {
        TODO("Not yet implemented")
    }

    enum class BossBarAction {
        Add,
        Remove,
        UpdateHealth,
        UpdateTitle,
        UpdateStyle,
        UpdateFlags
    }
}
