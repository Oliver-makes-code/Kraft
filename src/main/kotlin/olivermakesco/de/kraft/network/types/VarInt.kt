package olivermakesco.de.kraft.network.types

import olivermakesco.de.kraft.network.packet.PacketBuffer
import java.io.InputStream

class VarInt(var value: Int) {
    fun toByteArray(): ByteArray {
        val bytes = ArrayList<Byte>()

        while (value and SEGMENT_BITS.inv() != 0) {
            bytes.add(((value and SEGMENT_BITS) or CONTINUE_BIT).toByte())

            value = value shr 7
        }

        bytes.add(value.toByte())

        return bytes.toByteArray()
    }

    companion object {
        private const val SEGMENT_BITS = 0b01111111
        private const val CONTINUE_BIT = 0b10000000

        fun fromPacketBuffer(packetBuffer: PacketBuffer): VarInt {
            var value = 0
            var position = 0
            do {
                val byte = packetBuffer.pop()
                value = value or ((byte.toInt() and SEGMENT_BITS) shl position)

                if (byte.toInt() and CONTINUE_BIT == 0) break

                position += 7

                if (position >= 32) throw RuntimeException("VarInt is too big")
            } while (true)

            return VarInt(value)
        }

        fun fromInputStream(inputStream: InputStream): VarInt {
            var value = 0
            var position = 0

            do {
                val byte = inputStream.read()
                value = value or ((byte and SEGMENT_BITS) shl position)

                if (byte and CONTINUE_BIT == 0) break

                position += 7

                if (position >= 32) throw RuntimeException("VarInt is too big")
            } while (byte and CONTINUE_BIT != 0)

            return VarInt(value)
        }
    }
}
