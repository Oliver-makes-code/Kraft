package olivermakesco.de.kraft.network.packet.serverbound.login

import olivermakesco.de.kraft.network.packet.PacketBuffer
import olivermakesco.de.kraft.network.packet.serverbound.ServerBoundPacket

class LoginStartPacket(private val username: String) : ServerBoundPacket {
    override val id = 0x00
    override fun write(buffer: PacketBuffer) {
        buffer += username
    }
}
