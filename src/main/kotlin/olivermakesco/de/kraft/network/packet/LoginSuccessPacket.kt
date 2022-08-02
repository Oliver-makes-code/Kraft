package olivermakesco.de.kraft.network.packet

import olivermakesco.de.kraft.network.Packet
import olivermakesco.de.kraft.network.PacketBuffer

class LoginSuccessPacket(buffer: PacketBuffer) : Packet {
    val uuid = buffer.readUuid()
    val name = buffer.readString()
}
