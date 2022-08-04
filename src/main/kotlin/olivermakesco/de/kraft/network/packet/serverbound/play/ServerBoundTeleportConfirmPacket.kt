package olivermakesco.de.kraft.network.packet.serverbound.play

import olivermakesco.de.kraft.network.packet.PacketBuffer
import olivermakesco.de.kraft.network.packet.serverbound.ServerBoundPacket
import olivermakesco.de.kraft.network.types.VarInt

class ServerBoundTeleportConfirmPacket(val teleportId: Int) : ServerBoundPacket {
    override val id = 0x00

    override fun write(buffer: PacketBuffer) {
        buffer += VarInt(teleportId)
    }
}