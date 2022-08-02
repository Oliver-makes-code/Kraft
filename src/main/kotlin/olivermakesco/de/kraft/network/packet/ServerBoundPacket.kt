package olivermakesco.de.kraft.network.packet

interface ServerBoundPacket : Packet {
    fun write(buffer: PacketBuffer)
}
