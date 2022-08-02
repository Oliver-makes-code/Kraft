package olivermakesco.de.kraft.network

import java.net.Socket

class ClientConnection(private val addr: String, private val port: Int, private val manager: NetworkManager) {
    private val socket = Socket(addr, this.port)
    private val sendChannel = socket.getOutputStream()
    private val receiveChannel = socket.getInputStream()

    fun sendPacket(packet: ServerBoundPacket) {
        sendChannel.write(encodePacket(packet))
    }

    fun readPacket(): Packet {
        val length = receiveChannel.read()
        val contents = receiveChannel.readNBytes(length)

        return decodePacket(contents)
    }

    private fun encodePacket(packet: ServerBoundPacket): ByteArray {
        val tempBuffer = PacketBuffer()
        tempBuffer += manager.networkState.getPacketId(PacketDirection.ServerBound, packet)
        packet.write(tempBuffer)

        val buffer = PacketBuffer()
        buffer += tempBuffer.size
        buffer += tempBuffer

        return buffer.toByteArray()
    }

    private fun decodePacket(data: ByteArray): Packet {
        val buffer = PacketBuffer(data)
        val packetId = buffer.readVarInt().value

        return manager.networkState.getPacketById(PacketDirection.ClientBound, packetId.toByte()).getDeclaredConstructor(PacketBuffer::class.java).newInstance(buffer)
    }
}
