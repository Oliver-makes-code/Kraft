package olivermakesco.de.kraft.network.packet.serverbound.login

import olivermakesco.de.kraft.network.packet.PacketBuffer
import olivermakesco.de.kraft.network.packet.serverbound.ServerBoundPacket
import olivermakesco.de.kraft.network.types.VarInt

class ServerBoundLoginPluginResponsePacket(private val messageId: Int, private val successful: Boolean) : ServerBoundPacket {
    override val id = 0x02

    lateinit var data: ByteArray

    constructor(messageId: Int, successful: Boolean, data: ByteArray): this(messageId, successful) {
        this.data = data
    }

    override fun write(buffer: PacketBuffer) {
        buffer += VarInt(messageId)
        buffer += successful
        buffer += data
    }
}
