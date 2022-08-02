package olivermakesco.de.kraft.network.packet

import olivermakesco.de.kraft.network.NetworkState
import olivermakesco.de.kraft.network.packet.clientbound.ClientBoundPacket
import olivermakesco.de.kraft.network.packet.clientbound.login.*
import olivermakesco.de.kraft.network.packet.serverbound.ServerBoundPacket
import olivermakesco.de.kraft.network.packet.serverbound.handshake.HandshakePacket
import olivermakesco.de.kraft.network.packet.serverbound.login.*
import java.lang.reflect.Constructor

object PacketRegistry {
    private var serverBoundPackets = HashMap<NetworkState, PacketSet<ServerBoundPacket>>()
    private var clientBoundPackets = HashMap<NetworkState, PacketSet<ClientBoundPacket>>()

    fun getId(state: NetworkState, packet: Packet): Int {
        return when (packet) {
            is ServerBoundPacket -> serverBoundPackets[state]?.indexOf(packet::class.java)
            is ClientBoundPacket -> clientBoundPackets[state]?.indexOf(packet::class.java)
            else -> null
        } ?: throw RuntimeException("Packet id could not be found for packet $packet.")
    }

    fun getClientBoundPacket(state: NetworkState, id: Int): Constructor<out ClientBoundPacket> {
        val clazz = clientBoundPackets[state]?.get(id)
        return clazz?.getDeclaredConstructor(PacketBuffer::class.java)
            ?: throw RuntimeException("$state packet with id $id could not be found.")
    }

    private fun registerHandshakePackets() {
        val serverBound = PacketSet<ServerBoundPacket>()
        serverBound += HandshakePacket::class.java

        serverBoundPackets[NetworkState.HANDSHAKING] = serverBound
    }

    private fun registerLoginPackets() {
        val serverBound = PacketSet<ServerBoundPacket>()
        serverBound += LoginStartPacket::class.java
        serverBound += EncryptionResponsePacket::class.java
        serverBound += LoginPluginResponsePacket::class.java

        val clientBound =  PacketSet<ClientBoundPacket>()
        clientBound += LoginDisconnectPacket::class.java
        clientBound += EncryptionRequestPacket::class.java
        clientBound += LoginSuccessPacket::class.java
        clientBound += SetCompressionPacket::class.java
        clientBound += LoginPluginRequestPacket::class.java

        serverBoundPackets[NetworkState.LOGIN] = serverBound
        clientBoundPackets[NetworkState.LOGIN] = clientBound
    }

    init {
        registerHandshakePackets()
        registerLoginPackets()
    }

    private class PacketSet<T : Packet>: ArrayList<Class<out T>>()
}
