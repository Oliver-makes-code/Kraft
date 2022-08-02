package olivermakesco.de.kraft.network

import olivermakesco.de.kraft.network.packet.*
import java.net.Socket

class Connection(addr: String, port: Int, private val manager: NetworkManager) {
    private val socket = Socket(addr, port)
    private val sendChannel = socket.getOutputStream()
    private val receiveChannel = socket.getInputStream()

    fun sendPacket(packet: ServerBoundPacket) {
        sendChannel.write(encodePacket(packet))
    }

    fun readPacket(): ClientBoundPacket {
        val length = receiveChannel.read()
        val contents = receiveChannel.readNBytes(length)

        return decodePacket(contents)
    }

    private fun encodePacket(packet: ServerBoundPacket): ByteArray {
        val tempBuffer = PacketBuffer()
        tempBuffer += PacketRegistry.getId(manager.networkState, packet)
        packet.write(tempBuffer)

        val buffer = PacketBuffer()
        buffer += tempBuffer.size
        buffer += tempBuffer

        return buffer.toByteArray()
    }

    private fun decodePacket(data: ByteArray): ClientBoundPacket {
        val buffer = PacketBuffer(data)
        val packetId = buffer.readInt()

        return PacketRegistry.getClientBoundPacket(manager.networkState, packetId)
    }
}
