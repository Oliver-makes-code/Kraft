package olivermakesco.de.kraft.network

import olivermakesco.de.kraft.network.packet.*
import olivermakesco.de.kraft.network.packet.clientbound.ClientBoundPacket
import olivermakesco.de.kraft.network.packet.serverbound.ServerBoundPacket
import olivermakesco.de.kraft.network.types.VarInt
import java.net.Socket
import java.util.zip.Deflater
import java.util.zip.Inflater

class Connection(addr: String, port: Int, private val manager: NetworkManager) {
    private val socket = Socket(addr, port)
    private val sendChannel = socket.getOutputStream()
    private val receiveChannel = socket.getInputStream()
    var compressionThreshold = 0

    fun sendPacket(packet: ServerBoundPacket) {
        sendChannel.write(encodePacket(packet))
    }

    fun readPacket(): ClientBoundPacket {
        val length = VarInt.fromInputStream(receiveChannel).value
        val contents = receiveChannel.readNBytes(length)

        return decodePacket(contents)
    }

    fun hasPacket(): Boolean {
        return receiveChannel.available() > 0
    }

    private fun encodePacket(packet: ServerBoundPacket): ByteArray {
        var contents = PacketBuffer()
        contents += VarInt(packet.id)
        contents += packet

        if (compressionThreshold > 0) { // Compression enabled
            if (contents.size >= compressionThreshold) { // Over compression threshold
                val compressedBuffer = compressPacket(contents)
                compressedBuffer.pushVarInt(contents.size)
                contents = compressedBuffer
            } else { // Under compression threshold
                contents.pushVarInt(0)
            }
        }

        contents.pushVarInt(contents.size)

        return contents.toByteArray()
    }

    private fun decodePacket(data: ByteArray): ClientBoundPacket {
        var contents = PacketBuffer(data)

        if (compressionThreshold > 0) {
            val uncompressedLength = contents.readVarInt().value // Get uncompressed length
            if (uncompressedLength >= compressionThreshold) {
                contents = decompressPacket(contents, uncompressedLength)
            }
        }

        return ClientBoundPacket.fromBuffer(contents, manager.networkState)
    }

    private fun compressPacket(packet: PacketBuffer): PacketBuffer {
        val output = ByteArray(packet.size) // Compressed packet shouldn't be larger than raw data
        val compressor = Deflater()
        compressor.setInput(packet.toByteArray())
        compressor.finish()
        val compressedSize = compressor.deflate(output)
        compressor.end()
        return PacketBuffer(output.sliceArray(0 until compressedSize))
    }

    private fun decompressPacket(packet: PacketBuffer, length: Int): PacketBuffer {
        val output = ByteArray(length)
        val decompressor = Inflater()
        decompressor.setInput(packet.toByteArray())
        decompressor.inflate(output)
        decompressor.end()
        return PacketBuffer(output)
    }
}
