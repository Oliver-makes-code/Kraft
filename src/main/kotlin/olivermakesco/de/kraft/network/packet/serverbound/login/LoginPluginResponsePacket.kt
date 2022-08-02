package olivermakesco.de.kraft.network.packet.serverbound.login

import olivermakesco.de.kraft.network.packet.PacketBuffer
import olivermakesco.de.kraft.network.packet.ServerBoundPacket

class LoginPluginResponsePacket(private val messageId: Int, private val successful: Boolean) : ServerBoundPacket {
    lateinit var data: ByteArray

    constructor(messageId: Int, successful: Boolean, data: ByteArray): this(messageId, successful) {
        this.data = data
    }

    override fun write(buffer: PacketBuffer) {
        buffer += messageId
        buffer += successful
        buffer += data
    }
}