package olivermakesco.de.kraft.network.packet

import olivermakesco.de.kraft.network.PacketBuffer
import olivermakesco.de.kraft.network.ServerBoundPacket

class LoginStartPacket(private val username: String) : ServerBoundPacket {
    override fun write(buffer: PacketBuffer) {
        buffer += username
    }
}
