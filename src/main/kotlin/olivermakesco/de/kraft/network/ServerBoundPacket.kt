package olivermakesco.de.kraft.network

interface ServerBoundPacket : Packet {
    fun write(buffer: PacketBuffer)
}
