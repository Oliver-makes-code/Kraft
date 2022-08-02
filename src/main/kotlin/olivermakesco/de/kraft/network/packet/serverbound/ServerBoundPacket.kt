package olivermakesco.de.kraft.network.packet.serverbound

import olivermakesco.de.kraft.network.packet.Packet
import olivermakesco.de.kraft.network.packet.PacketBuffer

interface ServerBoundPacket : Packet {
    fun write(buffer: PacketBuffer)
}
