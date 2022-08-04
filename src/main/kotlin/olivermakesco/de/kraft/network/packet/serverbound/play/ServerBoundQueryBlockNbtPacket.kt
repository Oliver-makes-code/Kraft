package olivermakesco.de.kraft.network.packet.serverbound.play

import olivermakesco.de.kraft.network.packet.PacketBuffer
import olivermakesco.de.kraft.network.packet.serverbound.ServerBoundPacket

class ServerBoundQueryBlockNbtPacket(val transactionId: Int) : ServerBoundPacket {
    override val id = 0x01

    override fun write(buffer: PacketBuffer) {

    }
}