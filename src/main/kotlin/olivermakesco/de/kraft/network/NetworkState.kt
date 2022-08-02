package olivermakesco.de.kraft.network

import olivermakesco.de.kraft.network.packet.*

enum class NetworkState(val id: Byte, private val types: PacketTypes) {
    HANDSHAKING(-1, PacketTypes()
        .addDirection(PacketDirection.ServerBound)
            .addPacket(HandshakePacket::class.java)
        .build()
    ),
    PLAY(0, PacketTypes()),
    STATUS(1, PacketTypes()),
    LOGIN(2, PacketTypes()
        .addDirection(PacketDirection.ServerBound)
            .addPacket(LoginStartPacket::class.java)
        .addDirection(PacketDirection.ClientBound)
            .addPacket(LoginDisconnectPacket::class.java)
            .addPacket(EncryptionRequestPacket::class.java)
            .addPacket(LoginSuccessPacket::class.java)
        .build()
    );

    fun getPacketId(direction: PacketDirection, packet: Packet): Byte {
        return this.types.getDirection(direction).getByPacket(packet).toByte()
    }

    fun getPacketById(direction: PacketDirection, id: Byte): Class<Packet> {
        return this.types.getDirection(direction).getById(id.toInt())
    }

    private class PacketTypes {
        private val packetTypes = HashMap<PacketDirection, DirectionalPacketTypes>()

        fun getDirection(direction: PacketDirection): DirectionalPacketTypes {
            return packetTypes[direction] ?: throw RuntimeException("Packet direction $direction could not be found.")
        }

        fun addDirection(direction: PacketDirection): DirectionalPacketTypes {
            val directionList = DirectionalPacketTypes(this)
            packetTypes[direction] = directionList
            return directionList
        }

        class DirectionalPacketTypes(val parent: PacketTypes) {
            val packetTypes = ArrayList<Class<*>>()

            fun <T : Packet> addPacket(packet: Class<T>): DirectionalPacketTypes {
                packetTypes.add(packet)
                return this
            }

            fun getById(id: Int): Class<Packet> {
                return packetTypes[id] as Class<Packet>
            }

            fun <T : Packet> getByPacket(packet: T): Int {
                return packetTypes.indexOf(packet.javaClass)
            }

            fun addDirection(direction: PacketDirection): DirectionalPacketTypes {
                return parent.addDirection(direction)
            }

            fun build(): PacketTypes {
                return parent
            }
        }
    }
}
