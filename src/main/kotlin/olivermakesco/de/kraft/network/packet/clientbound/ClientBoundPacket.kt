package olivermakesco.de.kraft.network.packet.clientbound

import olivermakesco.de.kraft.client.KraftClient
import olivermakesco.de.kraft.network.NetworkState
import olivermakesco.de.kraft.network.packet.ClientBoundPacketRegistry
import olivermakesco.de.kraft.network.packet.Packet
import olivermakesco.de.kraft.network.packet.PacketBuffer

interface ClientBoundPacket: Packet {
    fun handle(client: KraftClient)

    companion object {
        fun fromBuffer(buffer: PacketBuffer, state: NetworkState): ClientBoundPacket {
            val id = buffer.readInt()
            val action = ClientBoundPacketRegistry.INSTANCE[id]
                ?: return UnknownClientBoundPacket(buffer, id)
            return try {
                buffer.action(state)
            } catch (error: NotImplementedError) {
                UnknownClientBoundPacket(buffer, id)
            }
        }
    }
}
